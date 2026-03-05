package org.cupula.resource;

import java.util.List;

import org.cupula.dto.pessoa.CreatePessoaRequest;
import org.cupula.dto.pessoa.PessoaResponseDTO;
import org.cupula.dto.pessoa.UpdatePessoaRequest;
import org.cupula.service.PessoaService;

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

@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

    @Inject
    PessoaService pessoaService;

    @GET
    @RolesAllowed({"User", "Admin"})
    public List<PessoaResponseDTO> listPessoas() {
        return pessoaService.listPessoas();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response getPessoaById(@PathParam("id") Long id) {
        PessoaResponseDTO pessoa = pessoaService.findPessoaById(id);
        if (pessoa == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(pessoa).build();
    }

    @GET
    @Path("/cpf/{cpf}")
    @RolesAllowed({"User", "Admin"})
    public Response getPessoaByCpf(@PathParam("cpf") String cpf) {
        PessoaResponseDTO pessoa = pessoaService.findPessoaByCpf(cpf);
        if (pessoa == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(pessoa).build();
    }

    @POST
    @RolesAllowed({"Admin"})
    public Response createPessoa(CreatePessoaRequest request) {
        try {
            PessoaResponseDTO pessoa = pessoaService.createPessoa(request);
            return Response.status(Response.Status.CREATED).entity(pessoa).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response updatePessoa(@PathParam("id") Long id, UpdatePessoaRequest request) {
        try {
            PessoaResponseDTO pessoa = pessoaService.updatePessoa(id, request);
            if (pessoa == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(pessoa).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response deletePessoa(@PathParam("id") Long id) {
        boolean deleted = pessoaService.deletePessoa(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
