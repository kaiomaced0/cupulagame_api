package org.cupula.resource.playertipo;

import org.cupula.model.entities.baseview.orelha.PlayerTipoBaseOrelha;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.service.playertipo.PlayerTipoBaseOrelhaService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/admin/player-tipo-base-orelha")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerTipoBaseOrelhaResource {

    @Inject
    PlayerTipoBaseOrelhaService service;

    @GET
    @RolesAllowed("Admin")
    public Response listarTodos(@QueryParam("raca") PlayerRaca raca) {
        List<PlayerTipoBaseOrelha> lista;
        if (raca != null) {
            lista = service.listarPorRaca(raca);
        } else {
            lista = service.listarTodos();
        }
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response buscarPorId(@PathParam("id") Long id) {
        PlayerTipoBaseOrelha entity = service.buscarPorId(id);
        return Response.ok(entity).build();
    }

    @POST
    @RolesAllowed("Admin")
    public Response criar(PlayerTipoBaseOrelha entity) {
        PlayerTipoBaseOrelha criado = service.criar(entity);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response atualizar(@PathParam("id") Long id, PlayerTipoBaseOrelha dados) {
        PlayerTipoBaseOrelha atualizado = service.atualizar(id, dados);
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response deletar(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}
