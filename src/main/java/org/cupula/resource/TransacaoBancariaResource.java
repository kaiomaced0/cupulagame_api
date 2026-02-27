package org.cupula.resource;

import java.util.List;

import org.cupula.dto.banco.request.TransferirRequest;
import org.cupula.dto.banco.response.PaginatedResponse;
import org.cupula.dto.banco.response.TransacaoDetalhadaResponse;
import org.cupula.dto.banco.response.TransacaoSimplificadaResponse;
import org.cupula.service.TransacaoBancariaService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transacoes-bancarias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransacaoBancariaResource {

    @Inject
    TransacaoBancariaService transacaoBancariaService;

    @Inject
    JsonWebToken jwt;

    /**
     * Realiza transferência entre contas
     * Requer número da conta destino, valor e senha do usuário
     */
    @POST
    @Path("/transferir")
    @RolesAllowed({"User", "Admin"})
    public Response transferir(TransferirRequest request) {
        Long playerId = getPlayerIdFromToken();
        TransacaoDetalhadaResponse response = transacaoBancariaService.transferir(playerId, request);
        return Response.ok(response).build();
    }

    /**
     * Lista todas as transações do player logado (débitos e créditos) com paginação
     * Retorna versão simplificada ordenada por data
     */
    @GET
    @Path("/me")
    @RolesAllowed({"User", "Admin"})
    public Response listarMinhasTransacoes(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        
        if (size > 100) {
            size = 100; // Limite máximo
        }
        if (size < 1) {
            size = 10; // Valor padrão
        }
        if (page < 0) {
            page = 0;
        }
        
        Long playerId = getPlayerIdFromToken();
        List<TransacaoSimplificadaResponse> transacoes = transacaoBancariaService.listarMinhasTransacoes(playerId, page, size);
        long total = transacaoBancariaService.contarMinhasTransacoes(playerId);
        
        PaginatedResponse<TransacaoSimplificadaResponse> response = PaginatedResponse.of(transacoes, page, size, total);
        return Response.ok(response).build();
    }

    /**
     * Retorna detalhes completos de uma transação específica
     * Player só pode ver transações onde é origem ou destino
     */
    @GET
    @Path("/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response getDetalhe(@PathParam("id") Long id) {
        Long playerId = getPlayerIdFromToken();
        TransacaoDetalhadaResponse response = transacaoBancariaService.getDetalhe(id, playerId);
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
