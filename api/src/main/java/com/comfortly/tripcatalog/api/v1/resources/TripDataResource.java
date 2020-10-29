package com.comfortly.tripcatalog.api.v1.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

import com.comfortly.tripcatalog.lib.TripData;
import com.comfortly.tripcatalog.services.beans.TripDataBean;

@ApplicationScoped
@Path("/trips")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TripDataResource {

    private Logger log = Logger.getLogger(TripDataResource.class.getName());

    @Inject
    private TripDataBean tripDataBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getTripData() {

        List<TripData> tripData = tripDataBean.getTripDataFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(tripData).build();
    }

    @GET
    @Path("/{tripDataId}")
    public Response getTripData(@PathParam("tripDataId") Integer tripDataId) {

        TripData tripData = tripDataBean.getTripData(tripDataId);

        if (tripData == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(tripData).build();
    }

    @POST
    public Response createTripData(TripData tripData) {

        if ((tripData.getStartLocationLat() == null || tripData.getStartLocationLng() == null || tripData.getStartTime() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            tripData = tripDataBean.createTripData(tripData);
        }

        return Response.status(Response.Status.OK).entity(tripData).build();

    }

    @PUT
    @Path("{tripDataId}")
    public Response putTripData(@PathParam("tripDataId") Integer tripDataId,
                                TripData tripData) {

        tripData = tripDataBean.putTripData(tripDataId, tripData);

        if (tripData == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();

    }

    @DELETE
    @Path("{tripDataId}")
    public Response deleteTripData(@PathParam("tripDataId") Integer tripDataId) {

        boolean deleted = tripDataBean.deleteTripData(tripDataId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
