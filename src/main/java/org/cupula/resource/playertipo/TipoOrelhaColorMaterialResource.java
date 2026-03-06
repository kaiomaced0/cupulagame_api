package org.cupula.resource.playertipo;

import org.cupula.model.entities.baseview.orelha.TipoOrelhaColorMaterial;
import org.cupula.service.playertipo.TipoOrelhaColorMaterialService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/admin/tipo-orelha-color-material")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoOrelhaColorMaterialResource {

    @Inject
    TipoOrelhaColorMaterialService service;

    @GET
    @RolesAllowed("Admin")
    public Response listarTodos() {
        List<TipoOrelhaColorMaterial> lista = service.listarTodos();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response buscarPorId(@PathParam("id") Long id) {
        TipoOrelhaColorMaterial entity = service.buscarPorId(id);
        return Response.ok(entity).build();
    }

    @POST
    @RolesAllowed("Admin")
    public Response criar(TipoOrelhaColorMaterial entity) {
        TipoOrelhaColorMaterial criado = service.criar(entity);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Admin")
    public Response atualizar(@PathParam("id") Long id, TipoOrelhaColorMaterial dados) {
        TipoOrelhaColorMaterial atualizado = service.atualizar(id, dados);
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
