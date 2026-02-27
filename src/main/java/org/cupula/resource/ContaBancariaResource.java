package org.cupula.resource;

import org.cupula.dto.banco.response.DadosBasicosContaResponse;
import org.cupula.dto.banco.response.ValidarContaResponse;
import org.cupula.service.ContaBancariaService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contas-bancarias")
@Produces(MediaType.APPLICATION_JSON)
public class ContaBancariaResource {

    @Inject
    ContaBancariaService contaBancariaService;

    @Inject
    JsonWebToken jwt;

    /**
     * Retorna dados básicos da conta do player logado (saldo, número da conta, últimas 3 transações)
     */
    @GET
    @Path("/me")
    @RolesAllowed({"User", "Admin"})
    public Response getDadosBasicos() {
        Long playerId = getPlayerIdFromToken();
        DadosBasicosContaResponse dados = contaBancariaService.getDadosBasicos(playerId);
        return Response.ok(dados).build();
    }

    /**
     * Valida uma conta pelo número e retorna o nome do titular
     * Usado para validar o destinatário antes de fazer uma transferência
     */
    @GET
    @Path("/validar/{numeroConta}")
    @RolesAllowed({"User", "Admin"})
    public Response validarConta(@PathParam("numeroConta") String numeroConta) {
        ValidarContaResponse response = contaBancariaService.validarConta(numeroConta);
        return Response.ok(response).build();
    }

    private Long getPlayerIdFromToken() {
        Object claim = jwt.getClaim("playerId");
        if (claim instanceof Number) {
            return ((Number) claim).longValue();
        } else if (claim instanceof String) {
            return Long.parseLong((String) claim);
        }
        throw new IllegalStateException("playerId claim not found or invalid in token");
    }
}
