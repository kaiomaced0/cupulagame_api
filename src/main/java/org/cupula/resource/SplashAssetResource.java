package org.cupula.resource;

import java.util.List;

import org.cupula.dto.splashasset.request.CreateSplashAssetRequest;
import org.cupula.dto.splashasset.request.UpdateSplashAssetRequest;
import org.cupula.dto.splashasset.response.SplashAssetResponse;
import org.cupula.service.SplashAssetService;
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

@Path("/splash-assets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SplashAssetResource {

    static final String ADMIN_ROLE = "Admin";

    @Inject
    SplashAssetService splashAssetService;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed(ADMIN_ROLE)
    public Response list() {
        List<SplashAssetResponse> response = splashAssetService.listAll();
        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed(ADMIN_ROLE)
    public Response findById(@PathParam("id") Long id) {
        SplashAssetResponse response = splashAssetService.findById(id);
        if (response == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(response).build();
    }

    @POST
    @RolesAllowed(ADMIN_ROLE)
    public Response create(CreateSplashAssetRequest request) {
        try {
            SplashAssetResponse response = splashAssetService.create(request);
            return Response.status(Status.CREATED).entity(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed(ADMIN_ROLE)
    public Response update(@PathParam("id") Long id, UpdateSplashAssetRequest request) {
        try {
            SplashAssetResponse response = splashAssetService.update(id, request);
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
        if (splashAssetService.delete(id)) {
            return Response.status(Status.NO_CONTENT).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/resolve/{usuarioId}")
    @RolesAllowed(ADMIN_ROLE)
    public Response resolveByUsuarioId(@PathParam("usuarioId") Long usuarioId) {
        SplashAssetResponse response = splashAssetService.resolveByUsuarioId(usuarioId, null);
        if (response == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(response).build();
    }

    @GET
    @Path("/me")
    @RolesAllowed({"User", "Admin"})
    public Response resolveByLoggedUser() {
        String nickName = jwt.getSubject();
        Long playerIdToken = getPlayerIdFromToken();

        SplashAssetResponse response = splashAssetService.resolveByLoggedUser(nickName, playerIdToken);
        if (response == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(response).build();
    }

    private Long getPlayerIdFromToken() {
        Object claim = jwt.getClaim("playerId");
        if (claim == null) {
            return null;
        }

        if (claim instanceof Number number) {
            return number.longValue();
        }

        if (claim instanceof String text) {
            try {
                return Long.valueOf(text);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }
}
