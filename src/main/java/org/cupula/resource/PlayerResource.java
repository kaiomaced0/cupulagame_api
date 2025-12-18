package org.cupula.resource;

import java.util.List;

import org.cupula.dto.player.request.AtualizarPosicaoPlayerRequest;
import org.cupula.dto.player.request.AlterarNickNameRequest;
import org.cupula.dto.player.request.CriarPlayerRequest;
import org.cupula.dto.player.response.PlayerResponse;
import org.cupula.model.auth.Usuario;
import org.cupula.repository.auth.UsuarioRepository;
import org.cupula.service.PlayerService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/players")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    @Inject
    PlayerService playerService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    org.cupula.service.TokenJwtService tokenJwtService;

    @Inject
    org.cupula.repository.player.PlayerRepository playerRepository;

    @Inject
    JsonWebToken jwt;

    @POST
    @PermitAll
    public Response criarPlayer(CriarPlayerRequest request) {
        try {
            // Cria player sem associar a usuário
            PlayerResponse response = playerService.criarPlayer(null, request);
            return Response.status(Status.CREATED).entity(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao criar player: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/{id}/nickname")
    @PermitAll
    public Response alterarNickName(@PathParam("id") Long id, AlterarNickNameRequest request) {
        try {
            PlayerResponse response = playerService.alterarNickName(id, request.nickName(), request.tag());
            return Response.ok(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao alterar nickname: " + e.getMessage())
                .build();
        }
    }

    @GET
    @PermitAll
    public Response listarPlayers() {
        try {
            List<PlayerResponse> players = playerService.listarTodosPlayers();
            return Response.ok(players).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao listar players: " + e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response buscarPlayer(@PathParam("id") Long id) {
        try {
            PlayerResponse player = playerService.buscarPorId(id);
            
            if (player == null) {
                return Response.status(Status.NOT_FOUND)
                    .entity("Player nao encontrado")
                    .build();
            }

            return Response.ok(player).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao buscar player: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/{id}/posicao")
    @PermitAll
    public Response atualizarPosicao(@PathParam("id") Long id, AtualizarPosicaoPlayerRequest request) {
        try {
            playerService.atualizarPosicao(id, request.x(), request.y(), request.z());
            return Response.ok().entity("Posicao atualizada com sucesso").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
        } catch (IllegalStateException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao atualizar posicao: " + e.getMessage())
                .build();
        }
    }

    /**
     * Gera um token de sessão "como" o player selecionado. O usuário precisa
     * estar logado e o player deve pertencer a ele.
     */
    @POST
    @Path("/{id}/login")
    @RolesAllowed({"User", "Admin"})
    public Response loginAsPlayer(@PathParam("id") Long id) {
        try {
            String nickName = jwt.getSubject();
            org.cupula.model.auth.Usuario usuario = usuarioRepository.findByNickName(nickName);

            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            org.cupula.model.entities.player.Player player = playerRepository.findById(id);
            if (player == null) {
                return Response.status(Status.NOT_FOUND).entity("Player nao encontrado").build();
            }

            if (player.getUsuario() == null || !player.getUsuario().getId().equals(usuario.getId())) {
                return Response.status(Status.FORBIDDEN).entity("Player nao pertence ao usuario autenticado").build();
            }

            String token = tokenJwtService.generateJwt(usuario, player);

            org.cupula.dto.auth.PlayerLoginResponse resp = new org.cupula.dto.auth.PlayerLoginResponse(player.getId(), token);

            return Response.ok(resp).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao logar como player: " + e.getMessage())
                .build();
        }
    }
}
