package org.cupula.repository.comunity;

import java.util.List;

import org.cupula.model.auth.Usuario;
import org.cupula.model.comunity.Amizade;
import org.cupula.model.comunity.StatusAmizade;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AmizadeRepository implements PanacheRepository<Amizade> {

    /**
     * Busca todas as amizades de um usuário (onde ele é solicitante ou solicitado)
     */
    public List<Amizade> findByUsuario(Usuario usuario) {
        return list("(solicitante = ?1 OR solicitado = ?1) ORDER BY dataSolicitacao DESC", usuario);
    }

    /**
     * Busca amizades aceitas de um usuário
     */
    public List<Amizade> findAmigosAceitos(Usuario usuario) {
        return list("(solicitante = ?1 OR solicitado = ?1) AND status = ?2 ORDER BY dataSolicitacao DESC", 
                    usuario, StatusAmizade.ACEITO);
    }

    /**
     * Busca solicitações pendentes recebidas
     */
    public List<Amizade> findSolicitacoesPendentes(Usuario usuario) {
        return list("solicitado = ?1 AND status = ?2 ORDER BY dataSolicitacao DESC", 
                    usuario, StatusAmizade.PENDENTE);
    }

    /**
     * Verifica se já existe uma solicitação entre dois usuários
     */
    public Amizade findBetweenUsuarios(Usuario usuario1, Usuario usuario2) {
        return find("(solicitante = ?1 AND solicitado = ?2) OR (solicitante = ?2 AND solicitado = ?1)", 
                    usuario1, usuario2).firstResult();
    }
}
