package org.cupula.resource;

import java.util.List;

import org.cupula.dto.structures.json.JsonStructure;
import org.cupula.dto.structures.json.JsonStructureUnit;
import org.cupula.dto.structures.request.AtualizarStructureRequest;
import org.cupula.dto.structures.request.CriarStructureRequest;
import org.cupula.dto.structures.response.StructureResponse;
import org.cupula.dto.structures.response.StructureUnitResponse;
import org.cupula.model.auth.Usuario;
import org.cupula.repository.auth.UsuarioRepository;
import org.cupula.service.StructureService;
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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/structures")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"User", "Admin"})
public class StructureResource {

    @Inject
    StructureService structureService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    JsonWebToken jwt;

    @GET
    public Response listar() {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            List<StructureResponse> structures = structureService.listarPorUsuarioComHierarquia(usuario.getId());
            return Response.ok(structures).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao listar structures: " + e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            StructureResponse structure = structureService.buscarPorIdComHierarquia(id, usuario.getId());
            if (structure == null) {
                return Response.status(Status.NOT_FOUND)
                    .entity("Structure nao encontrada")
                    .build();
            }

            return Response.ok(structure).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao buscar structure: " + e.getMessage())
                .build();
        }
    }

    @POST
    public Response criar(CriarStructureRequest request) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            StructureResponse structure = structureService.criar(usuario, request);
            return Response.status(Status.CREATED).entity(structure).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao criar structure: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, AtualizarStructureRequest request) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            StructureResponse structure = structureService.atualizar(id, usuario.getId(), request);
            if (structure == null) {
                return Response.status(Status.NOT_FOUND)
                    .entity("Structure nao encontrada")
                    .build();
            }

            return Response.ok(structure).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao atualizar structure: " + e.getMessage())
                .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            boolean deletado = structureService.deletar(id, usuario.getId());
            if (!deletado) {
                return Response.status(Status.NOT_FOUND)
                    .entity("Structure nao encontrada")
                    .build();
            }

            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao deletar structure: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/{id}/favorita")
    public Response gerenciarFavorita(@PathParam("id") Long structureId, @QueryParam("acao") Integer acao) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            if (acao == null) {
                return Response.status(Status.BAD_REQUEST)
                    .entity("Parametro 'acao' e obrigatorio (0=remover, 1=adicionar)")
                    .build();
            }

            structureService.adicionarOuRemoverStructureFavorita(usuario, structureId, acao);
            
            String mensagem = acao == 1 ? "Structure adicionada as favoritas" : "Structure removida das favoritas";
            return Response.ok().entity(mensagem).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao gerenciar favorita: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/units/{id}/favorita")
    public Response gerenciarUnitFavorita(@PathParam("id") Long structureUnitId, @QueryParam("acao") Integer acao) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            if (acao == null) {
                return Response.status(Status.BAD_REQUEST)
                    .entity("Parametro 'acao' e obrigatorio (0=remover, 1=adicionar)")
                    .build();
            }

            structureService.adicionarOuRemoverStructureUnitFavorita(usuario, structureUnitId, acao);
            
            String mensagem = acao == 1 ? "StructureUnit adicionada as favoritas" : "StructureUnit removida das favoritas";
            return Response.ok().entity(mensagem).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao gerenciar favorita: " + e.getMessage())
                .build();
        }
    }

    // ==================== ENDPOINTS DE IMPORT/EXPORT ====================

    @GET
    @Path("/{id}/export")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exportStructure(@PathParam("id") Long id) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            JsonStructure jsonStructure = structureService.exportStructure(id, usuario.getId());
            return Response.ok(jsonStructure).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao exportar structure: " + e.getMessage())
                .build();
        }
    }

    @POST
    @Path("/import")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importStructure(JsonStructure jsonStructure) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            StructureResponse structure = structureService.importStructure(usuario, jsonStructure);
            return Response.status(Status.CREATED).entity(structure).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao importar structure: " + e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/units/{id}/export")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exportStructureUnit(@PathParam("id") Long id) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            JsonStructureUnit jsonUnit = structureService.exportStructureUnit(id, usuario.getId());
            return Response.ok(jsonUnit).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao exportar structure unit: " + e.getMessage())
                .build();
        }
    }

    @POST
    @Path("/units/import")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importStructureUnit(JsonStructureUnit jsonUnit) {
        try {
            String nickName = jwt.getSubject();
            Usuario usuario = usuarioRepository.findByNickName(nickName);
            
            if (usuario == null) {
                return Response.status(Status.UNAUTHORIZED)
                    .entity("Usuario nao encontrado")
                    .build();
            }

            StructureUnitResponse unit = structureService.importStructureUnit(usuario, jsonUnit);
            return Response.status(Status.CREATED).entity(unit).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao importar structure unit: " + e.getMessage())
                .build();
        }
    }
}
