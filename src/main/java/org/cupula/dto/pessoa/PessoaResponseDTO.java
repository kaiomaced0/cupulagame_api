package org.cupula.dto.pessoa;

import java.time.LocalDate;

import org.cupula.model.auth.pessoa.Pessoa;
import org.cupula.model.auth.pessoa.Sexo;

public record PessoaResponseDTO(
    Long id,
    String nomeCompleto,
    String cpf,
    LocalDate dataNascimento,
    Sexo sexo
) {
    public static PessoaResponseDTO from(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }
        
        return new PessoaResponseDTO(
            pessoa.getId(),
            pessoa.getNomeCompleto(),
            pessoa.getCpf(),
            pessoa.getDataNascimento(),
            pessoa.getSexo()
        );
    }
}
