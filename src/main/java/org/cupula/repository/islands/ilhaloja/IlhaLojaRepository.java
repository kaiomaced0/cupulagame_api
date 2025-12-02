package org.cupula.repository.islands.ilhaloja;

import java.util.List;
import java.util.Optional;

import org.cupula.model.islands.ilhaloja.IlhaLoja;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaLojaRepository implements PanacheRepository<IlhaLoja> {
    
    public List<IlhaLoja> findByIlha(Long ilhaId) {
        return list("ilha.id", ilhaId);
    }
    
    public Optional<IlhaLoja> findByNome(String nome) {
        return find("nome", nome).firstResultOptional();
    }
    
    public Optional<IlhaLoja> findByEstruturaLoja(Long estruturaId) {
        return find("estruturaLoja.id", estruturaId).firstResultOptional();
    }
}

