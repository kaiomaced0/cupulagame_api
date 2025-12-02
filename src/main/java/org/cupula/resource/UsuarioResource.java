package org.cupula.resource;

import org.cupula.dto.auth.CreateUsuarioRequest;
import org.cupula.dto.auth.UpdateUsuarioRequest;
import org.cupula.dto.responses.usuario.UsuarioResponseDTO;
import org.cupula.service.UsuarioService;

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

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed(UsuarioResource.ADMIN_ROLE)
public class UsuarioResource {

    static final String ADMIN_ROLE = "Admin";

    @Inject
    UsuarioService usuarioService;

    @GET
    public Response list() {
        return Response.ok(usuarioService.listUsuarios()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        UsuarioResponseDTO response = usuarioService.findUsuarioById(id);
        if (response == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(response).build();
    }

    @POST
    public Response create(CreateUsuarioRequest request) {
        try {
            UsuarioResponseDTO response = usuarioService.createUsuario(request);
            return Response.status(Status.CREATED).entity(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UpdateUsuarioRequest request) {
        try {
            UsuarioResponseDTO response = usuarioService.updateUsuario(id, request);
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
    public Response delete(@PathParam("id") Long id) {
        if (usuarioService.deleteUsuario(id)) {
            return Response.status(Status.NO_CONTENT).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }
}
