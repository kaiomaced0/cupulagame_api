package org.cupula.repository;

import java.util.List;

import org.cupula.model.islands.ilhaloja.IlhaLojaEstoque;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaLojaEstoqueRepository implements PanacheRepository<IlhaLojaEstoque> {
    
    public List<IlhaLojaEstoque> findByLoja(Long lojaId) {
        return list("loja.id", lojaId);
    }
    
    public List<IlhaLojaEstoque> findByTitulo(String titulo) {
        return list("titulo", titulo);
    }
    
    public List<IlhaLojaEstoque> findByLojaOrderByTitulo(Long lojaId) {
        return list("loja.id = ?1 order by titulo asc", lojaId);
    }
    
    public List<IlhaLojaEstoque> findComQuantidadeDisponivel(Long lojaId) {
        return list("loja.id = ?1 and quantidadeAtual > 0", lojaId);
    }
}
