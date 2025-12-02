package org.cupula.repository.islands.ilhaloja;

import java.util.List;
import java.util.Optional;

import org.cupula.model.islands.ilhaloja.FuncionarioLoja;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioLojaRepository implements PanacheRepository<FuncionarioLoja> {
    
    public List<FuncionarioLoja> findByLoja(Long lojaId) {
        return list("loja.id", lojaId);
    }
    
    public Optional<FuncionarioLoja> findByFuncionario(Long funcionarioId) {
        return find("funcionario.id", funcionarioId).firstResultOptional();
    }
    
    public List<FuncionarioLoja> findByLojaOrderByFuncionario(Long lojaId) {
        return list("loja.id = ?1 order by funcionario.id asc", lojaId);
    }
}

