package org.cupula.resource;

import java.util.List;

import org.cupula.dto.backgroundasset.request.AssignBackgroundAssetRequest;
import org.cupula.dto.backgroundasset.request.CreateBackgroundAssetRequest;
import org.cupula.dto.backgroundasset.request.UpdateBackgroundAssetRequest;
import org.cupula.dto.backgroundasset.response.BackgroundAssetResponse;
import org.cupula.service.BackgroundAssetService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/background-assets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BackgroundAssetResource {

    static final String ADMIN_ROLE = "Admin";

    @Inject
    BackgroundAssetService backgroundAssetService;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed(ADMIN_ROLE)
    public Response list() {
        List<BackgroundAssetResponse> response = backgroundAssetService.listAll();
        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed(ADMIN_ROLE)
    public Response findById(@PathParam("id") Long id) {
        BackgroundAssetResponse response = backgroundAssetService.findById(id);
        if (response == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(response).build();
    }

    @POST
    @RolesAllowed(ADMIN_ROLE)
    public Response create(CreateBackgroundAssetRequest request) {
        try {
            BackgroundAssetResponse response = backgroundAssetService.create(request);
            return Response.status(Status.CREATED).entity(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed(ADMIN_ROLE)
    public Response update(@PathParam("id") Long id, UpdateBackgroundAssetRequest request) {
        try {
            BackgroundAssetResponse response = backgroundAssetService.update(id, request);
            if (response == null) {
                return Response.status(Status.NOT_FOUND).build();
            }
            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed(ADMIN_ROLE)
    public Response delete(@PathParam("id") Long id) {
        if (backgroundAssetService.delete(id)) {
            return Response.status(Status.NO_CONTENT).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/me")
    @RolesAllowed({"User", "Admin"})
    public Response getLoggedUserBackgroundAsset() {
        String nickName = jwt.getSubject();
        BackgroundAssetResponse response = backgroundAssetService.getBackgroundAssetByLoggedUser(nickName);
        if (response == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(response).build();
    }

    @PUT
    @Path("/assign")
    @RolesAllowed(ADMIN_ROLE)
    public Response assignToUser(AssignBackgroundAssetRequest request) {
        try {
            BackgroundAssetResponse response = backgroundAssetService.assignBackgroundAssetToUser(request);
            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
