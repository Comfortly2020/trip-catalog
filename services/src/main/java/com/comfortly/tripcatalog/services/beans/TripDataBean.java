package com.comfortly.tripcatalog.services.beans;

import com.comfortly.tripcatalog.lib.TripData;
import com.comfortly.tripcatalog.models.converters.TripDataConverter;
import com.comfortly.tripcatalog.models.entities.TripDataEntity;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RequestScoped
public class TripDataBean {

    private Logger log = Logger.getLogger(TripDataBean.class.getName());

    @Inject
    private EntityManager emTrip;

    public List<TripData> getTripData() {

        TypedQuery<TripDataEntity> query = emTrip.createNamedQuery(
                "TripDataEntity.getAll", TripDataEntity.class);

        List<TripDataEntity> resultList = query.getResultList();

        return resultList.stream().map(TripDataConverter::toDto).collect(Collectors.toList());

    }

    public List<TripData> getTripDataFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(emTrip, TripDataEntity.class, queryParameters).stream()
                .map(TripDataConverter::toDto).collect(Collectors.toList());
    }

    public List<TripData> getTripDataByUser(String userId) {

        // TODO odkomentiraj po Mejniku 1
//        TypedQuery<TripDataEntity> query = emTrip.createQuery("SELECT t FROM TripDataEntity t WHERE t.userId = :user", TripDataEntity.class);
//        query.setParameter("user", userId);

        // TODO ostrani po Mejniku 1
        TypedQuery<TripDataEntity> query = emTrip.createQuery("SELECT t FROM TripDataEntity t", TripDataEntity.class);

        return query.getResultList().stream()
                .map(TripDataConverter::toDto).collect(Collectors.toList());
    }

    public TripData getTripData(Integer id) {

        TripDataEntity tripDataEntity = emTrip.find(TripDataEntity.class, id);

        if (tripDataEntity == null) {
            throw new NotFoundException();
        }

        TripData tripData = TripDataConverter.toDto(tripDataEntity);

        return tripData;
    }

    public TripData createTripData(TripData tripData) {

        TripDataEntity tripDataEntity = TripDataConverter.toEntity(tripData);

        try {
            beginTx();
            emTrip.persist(tripDataEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        if (tripDataEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return TripDataConverter.toDto(tripDataEntity);
    }

    public TripData putTripData(Integer id, TripData tripData) {

        TripDataEntity c = emTrip.find(TripDataEntity.class, id);

        if (c == null) {
            return null;
        }

        TripDataEntity updatedTripDataEntity = TripDataConverter.toEntity(tripData);

        try {
            beginTx();
            updatedTripDataEntity.setId(c.getId());
            updatedTripDataEntity = emTrip.merge(updatedTripDataEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return TripDataConverter.toDto(updatedTripDataEntity);
    }

    public boolean deleteTripData(Integer id) {

        TripDataEntity tripData = emTrip.find(TripDataEntity.class, id);

        if (tripData != null) {
            try {
                beginTx();
                emTrip.remove(tripData);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!emTrip.getTransaction().isActive()) {
            emTrip.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (emTrip.getTransaction().isActive()) {
            emTrip.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (emTrip.getTransaction().isActive()) {
            emTrip.getTransaction().rollback();
        }
    }
}
