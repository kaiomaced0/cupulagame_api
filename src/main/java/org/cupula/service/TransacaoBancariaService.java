package org.cupula.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.cupula.dto.banco.request.TransferirRequest;
import org.cupula.dto.banco.response.TransacaoDetalhadaResponse;
import org.cupula.dto.banco.response.TransacaoSimplificadaResponse;
import org.cupula.model.banco.ContaBancaria;
import org.cupula.model.banco.TransacaoBancaria;
import org.cupula.model.entities.player.Player;
import org.cupula.repository.banco.TransacaoBancariaRepository;
import org.cupula.repository.player.PlayerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TransacaoBancariaService {

    @Inject
    TransacaoBancariaRepository transacaoBancariaRepository;

    @Inject
    ContaBancariaService contaBancariaService;

    @Inject
    PlayerRepository playerRepository;

    @Inject
    HashService hashService;

    @Transactional
    public TransacaoDetalhadaResponse transferir(Long playerId, TransferirRequest request) {
        // Validações do request
        if (request.numeroContaDestino() == null || request.numeroContaDestino().isBlank()) {
            throw new BadRequestException("Número da conta destino é obrigatório");
        }
        if (request.valor() == null) {
            throw new BadRequestException("Valor é obrigatório");
        }
        if (request.valor() <= 0) {
            throw new BadRequestException("Valor deve ser positivo");
        }
        if (request.senha() == null || request.senha().isBlank()) {
            throw new BadRequestException("Senha é obrigatória");
        }
        // Buscar player e validar senha
        Player player = playerRepository.findByIdOptional(playerId)
            .orElseThrow(() -> new NotFoundException("Player não encontrado"));

        if (player.getUsuario() == null || player.getUsuario().getSenha() == null) {
            throw new ForbiddenException("Usuário sem senha cadastrada");
        }

        // Validar senha (mesmo padrão do AuthService)
        String senhaHash = hashService.getHashSenha(request.senha());
        boolean senhaCorreta = senhaHash.equals(player.getUsuario().getSenha()) || request.senha().equals(player.getUsuario().getSenha());
        if (!senhaCorreta) {
            throw new ForbiddenException("Senha incorreta");
        }

        // Buscar contas
        ContaBancaria contaOrigem = contaBancariaService.findByPlayerId(playerId);
        ContaBancaria contaDestino = contaBancariaService.findByNumeroConta(request.numeroContaDestino());

        // Validações
        if (contaOrigem.getNumeroConta().equals(contaDestino.getNumeroConta())) {
            throw new BadRequestException("Não é possível transferir para a mesma conta");
        }

        if (contaOrigem.getSaldo() < request.valor()) {
            throw new BadRequestException("Saldo insuficiente");
        }

        if (request.valor() <= 0) {
            throw new BadRequestException("Valor deve ser positivo");
        }

        // Salvar saldos anteriores
        Long saldoOrigemAntes = contaOrigem.getSaldo();
        Long saldoDestinoAntes = contaDestino.getSaldo();

        // Realizar transferência
        contaOrigem.setSaldo(saldoOrigemAntes - request.valor());
        contaDestino.setSaldo(saldoDestinoAntes + request.valor());

        // Criar transação
        TransacaoBancaria transacao = new TransacaoBancaria();
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestino(contaDestino);
        transacao.setValor(request.valor());
        transacao.setContaBancariaOrigemAntes(saldoOrigemAntes);
        transacao.setContaBancariaDestinoAntes(saldoDestinoAntes);
        transacao.setContaBancariaOrigemDepois(contaOrigem.getSaldo());
        transacao.setContaBancariaDestinoDepois(contaDestino.getSaldo());

        transacaoBancariaRepository.persist(transacao);

        return mapToDetalhada(transacao);
    }

    public List<TransacaoSimplificadaResponse> listarMinhasTransacoes(Long playerId, int page, int size) {
        ContaBancaria minhaConta = contaBancariaService.findByPlayerId(playerId);

        // Buscar débitos e créditos
        List<TransacaoBancaria> debitos = transacaoBancariaRepository.list("contaOrigem", minhaConta);
        List<TransacaoBancaria> creditos = transacaoBancariaRepository.list("contaDestino", minhaConta);

        // Combinar, ordenar por data e aplicar paginação
        return Stream
            .concat(
                debitos.stream().map(t -> mapToSimplificada(t, minhaConta, "DEBITO")),
                creditos.stream().map(t -> mapToSimplificada(t, minhaConta, "CREDITO"))
            )
            .sorted(Comparator.comparing(TransacaoSimplificadaResponse::data).reversed())
            .skip((long) page * size)
            .limit(size)
            .collect(Collectors.toList());
    }

    public long contarMinhasTransacoes(Long playerId) {
        ContaBancaria minhaConta = contaBancariaService.findByPlayerId(playerId);
        
        long debitos = transacaoBancariaRepository.count("contaOrigem", minhaConta);
        long creditos = transacaoBancariaRepository.count("contaDestino", minhaConta);
        
        return debitos + creditos;
    }

    public TransacaoDetalhadaResponse getDetalhe(Long transacaoId, Long playerId) {
        TransacaoBancaria transacao = transacaoBancariaRepository.findByIdOptional(transacaoId)
            .orElseThrow(() -> new NotFoundException("Transação não encontrada"));

        ContaBancaria minhaConta = contaBancariaService.findByPlayerId(playerId);

        // Verificar se o player tem permissão para ver esta transação
        boolean isOrigem = transacao.getContaOrigem().getId().equals(minhaConta.getId());
        boolean isDestino = transacao.getContaDestino().getId().equals(minhaConta.getId());

        if (!isOrigem && !isDestino) {
            throw new ForbiddenException("Você não tem permissão para visualizar esta transação");
        }

        return mapToDetalhada(transacao);
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

    private TransacaoDetalhadaResponse mapToDetalhada(TransacaoBancaria transacao) {
        ContaBancaria origem = transacao.getContaOrigem();
        ContaBancaria destino = transacao.getContaDestino();

        return new TransacaoDetalhadaResponse(
            transacao.getId(),
            transacao.getValor(),
            transacao.getDataInclusao(),
            origem.getId(),
            origem.getNumeroConta(),
            origem.getTitular(),
            transacao.getContaBancariaOrigemAntes(),
            transacao.getContaBancariaOrigemDepois(),
            destino.getId(),
            destino.getNumeroConta(),
            destino.getTitular(),
            transacao.getContaBancariaDestinoAntes(),
            transacao.getContaBancariaDestinoDepois()
        );
    }
}
