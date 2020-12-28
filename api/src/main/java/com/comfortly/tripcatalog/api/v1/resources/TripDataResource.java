package com.comfortly.tripcatalog.api.v1.resources;

import com.comfortly.tripcatalog.lib.TripData;
import com.comfortly.tripcatalog.services.beans.TripDataBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

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
    public Response getTripData(@HeaderParam("UserId") String userId) {

        if (userId == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Missing UserId header").build();
        }
        List<TripData> tripData = tripDataBean.getTripDataByUser(userId);

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
    public Response createTripData(@HeaderParam("UserId") String userId, TripData tripData) {

        if (userId == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Missing UserId header").build();
        } else {
            tripData.setUserId(userId);
        }

        if ((tripData.getStartLocationLat() == null || tripData.getStartLocationLng() == null || tripData.getStartTime() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            tripData = tripDataBean.createTripData(tripData);
        }

        return Response.status(Response.Status.OK).entity(tripData).build();

    }

//    @DELETE
//    @Path("{tripDataId}")
//    public Response deleteTripData(@PathParam("tripDataId") Integer tripDataId) {
//
//        boolean deleted = tripDataBean.deleteTripData(tripDataId);
//
//        if (deleted) {
//            return Response.status(Response.Status.NO_CONTENT).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
}
