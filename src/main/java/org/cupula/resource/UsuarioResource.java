package org.cupula.resource;

import java.util.List;

import org.cupula.dto.auth.CreateUsuarioRequest;
import org.cupula.dto.auth.UpdateUsuarioRequest;
import org.cupula.dto.responses.usuario.UsuarioResponseDTO;
import org.cupula.dto.usuario.request.AdicionarAmigoRequest;
import org.cupula.dto.usuario.response.AmizadeResponse;
import org.cupula.model.auth.Usuario;
import org.cupula.repository.auth.UsuarioRepository;
import org.cupula.service.AmizadeService;
import org.cupula.service.UsuarioService;
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

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed(UsuarioResource.ADMIN_ROLE)
public class UsuarioResource {

    static final String ADMIN_ROLE = "Admin";

    @Inject
    UsuarioService usuarioService;

    @Inject
    AmizadeService amizadeService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    JsonWebToken jwt;

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

    // ==================== ENDPOINTS DE AMIZADE ====================

    @POST
    @Path("/amigos")
    @RolesAllowed({"User", "Admin"})
    public Response adicionarAmigo(AdicionarAmigoRequest request) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            AmizadeResponse response = amizadeService.adicionarAmigo(usuario, request.emailAmigo());
            return Response.status(Status.CREATED).entity(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao adicionar amigo: " + e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/amigos")
    @RolesAllowed({"User", "Admin"})
    public Response listarAmigos() {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            List<AmizadeResponse> amigos = amizadeService.listarAmigos(usuario);
            return Response.ok(amigos).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao listar amigos: " + e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/amigos/pendentes")
    @RolesAllowed({"User", "Admin"})
    public Response listarSolicitacoesPendentes() {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            List<AmizadeResponse> pendentes = amizadeService.listarSolicitacoesPendentes(usuario);
            return Response.ok(pendentes).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao listar solicitacoes pendentes: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/amigos/{id}/aceitar")
    @RolesAllowed({"User", "Admin"})
    public Response aceitarSolicitacao(@PathParam("id") Long amizadeId) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            AmizadeResponse response = amizadeService.aceitarSolicitacao(usuario, amizadeId);
            
            if (response == null) {
                return Response.status(Status.NOT_FOUND)
                    .entity("Solicitacao nao encontrada")
                    .build();
            }

            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao aceitar solicitacao: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/amigos/{id}/recusar")
    @RolesAllowed({"User", "Admin"})
    public Response recusarSolicitacao(@PathParam("id") Long amizadeId) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            AmizadeResponse response = amizadeService.recusarSolicitacao(usuario, amizadeId);
            
            if (response == null) {
                return Response.status(Status.NOT_FOUND)
                    .entity("Solicitacao nao encontrada")
                    .build();
            }

            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao recusar solicitacao: " + e.getMessage())
                .build();
        }
    }
}
