package org.cupula.dto.pessoa;

import java.time.LocalDate;

import org.cupula.model.auth.pessoa.Sexo;

public record CreatePessoaRequest(
    String nomeCompleto,
    String cpf,
    LocalDate dataNascimento,
    Sexo sexo
) {}
