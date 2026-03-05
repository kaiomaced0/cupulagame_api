package org.cupula.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.cupula.dto.colormaterial.request.AtualizarColorMaterialRequest;
import org.cupula.dto.colormaterial.request.CriarColorMaterialRequest;
import org.cupula.dto.colormaterial.response.ColorMaterialResponse;
import org.cupula.model.structures.view.ColorMaterial;
import org.cupula.model.structures.view.Texture;
import org.cupula.repository.structures.view.ColorMaterialRepository;
import org.cupula.repository.structures.view.TextureRepository;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Path("/colormaterials")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ColorMaterialResource {

    @Inject
    ColorMaterialRepository colorMaterialRepository;

    @Inject
    TextureRepository textureRepository;

    /**
     * Lista todos os color materials
     */
    @GET
    @RolesAllowed({"ADMIN"})
    public Response listarTodos() {
        List<ColorMaterial> colorMaterials = colorMaterialRepository.listAll();
        List<ColorMaterialResponse> response = colorMaterials.stream()
                .map(ColorMaterialResponse::from)
                .collect(Collectors.toList());
        return Response.ok(response).build();
    }

    /**
     * Busca um color material por ID
     */
    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response buscarPorId(@PathParam("id") Long id) {
        ColorMaterial colorMaterial = colorMaterialRepository.findById(id);
        if (colorMaterial == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("ColorMaterial não encontrado").build();
        }
        return Response.ok(ColorMaterialResponse.from(colorMaterial)).build();
    }

    /**
     * Cria um novo color material
     */
    @POST
    @RolesAllowed({"ADMIN"})
    @Transactional
    public Response criar(CriarColorMaterialRequest request) {
        ColorMaterial colorMaterial = new ColorMaterial();
        colorMaterial.setName(request.name());
        colorMaterial.setHexCode(request.hexCode());
        colorMaterial.setTipo(request.tipo());

        // Associar texture se fornecido
        if (request.textureId() != null) {
            Texture texture = textureRepository.findById(request.textureId());
            if (texture == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Texture não encontrada com ID: " + request.textureId()).build();
            }
            colorMaterial.setTexture(texture);
        }

        colorMaterialRepository.persist(colorMaterial);
        return Response.status(Response.Status.CREATED)
                .entity(ColorMaterialResponse.from(colorMaterial)).build();
    }

    /**
     * Atualiza um color material
     */
    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    @Transactional
    public Response atualizar(@PathParam("id") Long id, AtualizarColorMaterialRequest request) {
        ColorMaterial colorMaterial = colorMaterialRepository.findById(id);
        if (colorMaterial == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("ColorMaterial não encontrado").build();
        }

        colorMaterial.setName(request.name());
        colorMaterial.setHexCode(request.hexCode());
        colorMaterial.setTipo(request.tipo());

        // Atualizar texture
        if (request.textureId() != null) {
            Texture texture = textureRepository.findById(request.textureId());
            if (texture == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Texture não encontrada com ID: " + request.textureId()).build();
            }
            colorMaterial.setTexture(texture);
        } else {
            colorMaterial.setTexture(null);
        }

        colorMaterialRepository.persist(colorMaterial);
        return Response.ok(ColorMaterialResponse.from(colorMaterial)).build();
    }

    /**
     * Deleta um color material
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        ColorMaterial colorMaterial = colorMaterialRepository.findById(id);
        if (colorMaterial == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("ColorMaterial não encontrado").build();
        }

        colorMaterialRepository.delete(colorMaterial);
        return Response.noContent().build();
    }
}
