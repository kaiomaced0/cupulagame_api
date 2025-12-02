package org.cupula.repository.islands.ilhaloja;

import java.util.List;

import org.cupula.model.islands.ilhaloja.IlhaLojaMarket;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IlhaLojaMarketRepository implements PanacheRepository<IlhaLojaMarket> {
    
    public List<IlhaLojaMarket> findByLoja(Long lojaId) {
        return list("loja.id", lojaId);
    }
    
    public List<IlhaLojaMarket> findByLojaOrderByPrice(Long lojaId) {
        return list("loja.id = ?1 order by price asc", lojaId);
    }
    
    public List<IlhaLojaMarket> findByQuantidadeGreaterThan(int quantidade) {
        return list("quantity > ?1", quantidade);
    }
    
    public List<IlhaLojaMarket> findByLojaComEstoque(Long lojaId) {
        return list("loja.id = ?1 and quantity > 0", lojaId);
    }
}

