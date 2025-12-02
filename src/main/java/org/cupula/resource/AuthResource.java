package org.cupula.resource;

import org.cupula.dto.auth.AuthResponse;
import org.cupula.dto.auth.CreateUsuarioRequest;
import org.cupula.dto.auth.LoginRequest;
import org.cupula.dto.auth.ProviderLoginRequest;
import org.cupula.dto.responses.usuario.UsuarioResponseDTO;
import org.cupula.service.AuthService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        AuthResponse response = authService.login(request);
        if (response == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        }
        return Response.ok(response).build();
    }

    @POST
    @Path("/provider")
    public Response loginWithProvider(ProviderLoginRequest request) {
        AuthResponse response = authService.loginWithProvider(request);
        if (response == null) {
            return Response.status(Status.UNAUTHORIZED).build();
        }
        return Response.ok(response).build();
    }

    @POST
    @Path("/usuarios")
    public Response createUsuario(CreateUsuarioRequest request) {
        UsuarioResponseDTO response = authService.createUsuario(request);
        if (response == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        return Response.status(Status.CREATED).entity(response).build();
    }
}
