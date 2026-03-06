package org.cupula.resource;

import java.util.List;

import org.cupula.dto.items.CreateItemTipoRequest;
import org.cupula.dto.items.ItemTipoResponse;
import org.cupula.dto.items.UpdateItemTipoRequest;
import org.cupula.model.items.ItemTipo;
import org.cupula.service.ItemTipoService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/admin/item-tipos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class ItemTipoResource {

    @Inject
    ItemTipoService itemTipoService;

    @GET
    public Response listarTodos() {
        List<ItemTipo> itemTipos = itemTipoService.listarTodos();
        List<ItemTipoResponse> response = itemTipos.stream()
            .map(ItemTipoResponse::fromEntity)
            .toList();
        return Response.ok(response).build();
    }

    @GET
    @Path("/ativos")
    public Response listarAtivos() {
        List<ItemTipo> itemTipos = itemTipoService.listarAtivos();
        List<ItemTipoResponse> response = itemTipos.stream()
            .map(ItemTipoResponse::fromEntity)
            .toList();
        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        ItemTipo itemTipo = itemTipoService.buscarPorId(id);
        return Response.ok(ItemTipoResponse.fromEntity(itemTipo)).build();
    }

    @POST
    public Response criar(CreateItemTipoRequest request) {
        ItemTipo itemTipo = itemTipoService.criar(request);
        return Response.status(Response.Status.CREATED)
            .entity(ItemTipoResponse.fromEntity(itemTipo))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, UpdateItemTipoRequest request) {
        ItemTipo itemTipo = itemTipoService.atualizar(id, request);
        return Response.ok(ItemTipoResponse.fromEntity(itemTipo)).build();
    }

    @PATCH
    @Path("/{id}/inativar")
    public Response inativar(@PathParam("id") Long id) {
        itemTipoService.inativar(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}/ativar")
    public Response ativar(@PathParam("id") Long id) {
        itemTipoService.ativar(id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        itemTipoService.deletar(id);
        return Response.noContent().build();
    }
}
