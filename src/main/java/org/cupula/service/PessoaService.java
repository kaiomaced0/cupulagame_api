package org.cupula.service;

import java.util.List;
import java.util.stream.Collectors;

import org.cupula.dto.pessoa.CreatePessoaRequest;
import org.cupula.dto.pessoa.PessoaResponseDTO;
import org.cupula.dto.pessoa.UpdatePessoaRequest;
import org.cupula.model.auth.pessoa.Pessoa;
import org.cupula.repository.pessoa.PessoaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PessoaService {

    @Inject
    PessoaRepository pessoaRepository;

    public List<PessoaResponseDTO> listPessoas() {
        return pessoaRepository.listAll().stream()
                .map(PessoaResponseDTO::from)
                .collect(Collectors.toList());
    }

    public PessoaResponseDTO findPessoaById(Long id) {
        if (id == null) {
            return null;
        }
        Pessoa pessoa = pessoaRepository.findById(id);
        return PessoaResponseDTO.from(pessoa);
    }

    public PessoaResponseDTO findPessoaByCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        Pessoa pessoa = pessoaRepository.findByCpf(cpf);
        return PessoaResponseDTO.from(pessoa);
    }

    @Transactional
    public PessoaResponseDTO createPessoa(CreatePessoaRequest request) {
        if (request == null || request.nomeCompleto() == null || request.cpf() == null) {
            throw new IllegalArgumentException("Dados obrigatorios faltando");
        }

        if (pessoaRepository.findByCpf(request.cpf()) != null) {
            throw new IllegalArgumentException("CPF ja existe");
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNomeCompleto(request.nomeCompleto());
        pessoa.setCpf(request.cpf());
        pessoa.setDataNascimento(request.dataNascimento());
        pessoa.setSexo(request.sexo());

        pessoaRepository.persist(pessoa);
        return PessoaResponseDTO.from(pessoa);
    }

    @Transactional
    public PessoaResponseDTO updatePessoa(Long id, UpdatePessoaRequest request) {
        if (id == null || request == null) {
            throw new IllegalArgumentException("Dados obrigatorios faltando");
        }

        Pessoa pessoa = pessoaRepository.findById(id);
        if (pessoa == null) {
            return null;
        }

        if (request.nomeCompleto() != null) {
            pessoa.setNomeCompleto(request.nomeCompleto());
        }

        if (request.cpf() != null && !request.cpf().equals(pessoa.getCpf())) {
            Pessoa existing = pessoaRepository.findByCpf(request.cpf());
            if (existing != null && !existing.getId().equals(id)) {
                throw new IllegalArgumentException("CPF ja existe");
            }
            pessoa.setCpf(request.cpf());
        }

        if (request.dataNascimento() != null) {
            pessoa.setDataNascimento(request.dataNascimento());
        }

        if (request.sexo() != null) {
            pessoa.setSexo(request.sexo());
        }

        return PessoaResponseDTO.from(pessoa);
    }

    @Transactional
    public boolean deletePessoa(Long id) {
        if (id == null) {
            return false;
        }

        Pessoa pessoa = pessoaRepository.findById(id);
        if (pessoa == null) {
            return false;
        }

        pessoaRepository.delete(pessoa);
        return true;
    }
}
