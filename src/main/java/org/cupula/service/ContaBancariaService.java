package org.cupula.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.cupula.dto.banco.response.DadosBasicosContaResponse;
import org.cupula.dto.banco.response.TransacaoSimplificadaResponse;
import org.cupula.dto.banco.response.ValidarContaResponse;
import org.cupula.model.banco.ContaBancaria;
import org.cupula.model.banco.TransacaoBancaria;
import org.cupula.model.entities.player.Player;
import org.cupula.repository.banco.ContaBancariaRepository;
import org.cupula.repository.banco.TransacaoBancariaRepository;
import org.cupula.repository.player.PlayerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ContaBancariaService {

    @Inject
    ContaBancariaRepository contaBancariaRepository;

    @Inject
    TransacaoBancariaRepository transacaoBancariaRepository;

    @Inject
    PlayerRepository playerRepository;

    public DadosBasicosContaResponse getDadosBasicos(Long playerId) {
        Player player = playerRepository.findByIdOptional(playerId)
            .orElseThrow(() -> new NotFoundException("Player não encontrado"));

        ContaBancaria conta = contaBancariaRepository.find("proprietario", player).firstResultOptional()
            .orElseThrow(() -> new NotFoundException("Conta bancária não encontrada para este player"));

        // Buscar últimas 3 transações (débitos e créditos)
        List<TransacaoBancaria> debitos = transacaoBancariaRepository.list("contaOrigem = ?1 ORDER BY dataInclusao DESC", conta);
        List<TransacaoBancaria> creditos = transacaoBancariaRepository.list("contaDestino = ?1 ORDER BY dataInclusao DESC", conta);

        // Combinar débitos e créditos, ordenar por data e pegar apenas as 3 mais recentes
        List<TransacaoSimplificadaResponse> ultimasTransacoes = Stream
            .concat(
                debitos.stream().map(t -> mapToSimplificada(t, conta, "DEBITO")),
                creditos.stream().map(t -> mapToSimplificada(t, conta, "CREDITO"))
            )
            .sorted(Comparator.comparing(TransacaoSimplificadaResponse::data).reversed())
            .limit(3)
            .collect(Collectors.toList());

        return new DadosBasicosContaResponse(
            conta.getId(),
            conta.getNumeroConta(),
            conta.getTitular(),
            conta.getSaldo(),
            conta.getTipo() != null ? conta.getTipo().name() : null,
            ultimasTransacoes
        );
    }

    public ValidarContaResponse validarConta(String numeroConta) {
        ContaBancaria conta = contaBancariaRepository.find("numeroConta", numeroConta).firstResultOptional()
            .orElse(null);

        if (conta == null) {
            return new ValidarContaResponse(numeroConta, null, false);
        }

        return new ValidarContaResponse(
            numeroConta,
            conta.getTitular(),
            true
        );
    }

    public ContaBancaria findByNumeroConta(String numeroConta) {
        return contaBancariaRepository.find("numeroConta", numeroConta).firstResultOptional()
            .orElseThrow(() -> new NotFoundException("Conta não encontrada: " + numeroConta));
    }

    public ContaBancaria findByPlayerId(Long playerId) {
        Player player = playerRepository.findByIdOptional(playerId)
            .orElseThrow(() -> new NotFoundException("Player não encontrado"));

        return contaBancariaRepository.find("proprietario", player).firstResultOptional()
            .orElseThrow(() -> new NotFoundException("Conta bancária não encontrada para este player"));
    }

    private TransacaoSimplificadaResponse mapToSimplificada(TransacaoBancaria transacao, ContaBancaria minhaConta, String tipo) {
        ContaBancaria outraConta = tipo.equals("DEBITO") ? transacao.getContaDestino() : transacao.getContaOrigem();
        
        return new TransacaoSimplificadaResponse(
            transacao.getId(),
            tipo,
            transacao.getValor(),
            transacao.getDataInclusao(),
            outraConta != null ? outraConta.getNumeroConta() : "N/A",
            outraConta != null ? outraConta.getTitular() : "N/A"
        );
    }
}
