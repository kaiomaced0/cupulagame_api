package org.cupula.repository.player;

import org.cupula.model.entities.player.Player;
import org.cupula.model.entities.player.PlayerStatus;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerStatusRepository implements PanacheRepository<PlayerStatus> {

    /**
     * Busca o status de um player específico
     */
    public PlayerStatus findByPlayer(Player player) {
        return find("SELECT ps FROM PlayerStatus ps JOIN Player p ON p.status = ps WHERE p = ?1", player)
                .firstResult();
    }

    /**
     * Incrementa o contador de mortes
     */
    public void incrementarMortes(PlayerStatus status) {
        status.setTotalMortes(status.getTotalMortes() + 1);
        persist(status);
    }

    /**
     * Incrementa o contador de kills de mobs
     */
    public void incrementarKillsMobs(PlayerStatus status) {
        status.setTotalKillsMobs(status.getTotalKillsMobs() + 1);
        persist(status);
    }

    /**
     * Incrementa o contador de kills de players
     */
    public void incrementarKillsPlayers(PlayerStatus status) {
        status.setTotalKillsPlayers(status.getTotalKillsPlayers() + 1);
        persist(status);
    }

    /**
     * Restaura HP para o máximo
     */
    public void restaurarHP(PlayerStatus status) {
        status.setHpAtual(status.getHpMaximo());
        persist(status);
    }

    /**
     * Restaura Estamina para o máximo
     */
    public void restaurarEstamina(PlayerStatus status) {
        status.setEstaminaAtual(status.getEstaminaMaxima());
        persist(status);
    }

    /**
     * Restaura HP e Estamina (full restore)
     */
    public void restaurarCompleto(PlayerStatus status) {
        status.setHpAtual(status.getHpMaximo());
        status.setEstaminaAtual(status.getEstaminaMaxima());
        persist(status);
    }

    /**
     * Aplica dano ao player
     * @return true se o player morreu (HP zerado)
     */
    public boolean aplicarDano(PlayerStatus status, Long dano) {
        Long hpAtual = status.getHpAtual();
        Long novoHp = Math.max(0, hpAtual - dano);
        status.setHpAtual(novoHp);
        persist(status);
        return novoHp == 0;
    }

    /**
     * Aplica cura ao player
     */
    public void aplicarCura(PlayerStatus status, Long cura) {
        Long hpAtual = status.getHpAtual();
        Long novoHp = Math.min(status.getHpMaximo(), hpAtual + cura);
        status.setHpAtual(novoHp);
        persist(status);
    }

    /**
     * Consome estamina
     * @return true se tinha estamina suficiente
     */
    public boolean consumirEstamina(PlayerStatus status, Long custo) {
        Long estaminaAtual = status.getEstaminaAtual();
        if (estaminaAtual < custo) {
            return false;
        }
        status.setEstaminaAtual(estaminaAtual - custo);
        persist(status);
        return true;
    }

    /**
     * Adiciona XP e verifica se subiu de nível
     * @return true se subiu de nível
     */
    public boolean adicionarXP(PlayerStatus status, Long xp) {
        Long xpAtual = status.getXpAtual();
        Long nivelAtual = status.getNivelAtual();
        
        status.setXpAtual(xpAtual + xp);
        
        // Fórmula simples: XP necessário = nivel * 100
        Long xpNecessario = nivelAtual * 100;
        
        if (status.getXpAtual() >= xpNecessario) {
            status.setNivelAtual(nivelAtual + 1);
            status.setXpAtual(status.getXpAtual() - xpNecessario);
            persist(status);
            return true;
        }
        
        persist(status);
        return false;
    }

    /**
     * Adiciona ou remove gold do banco
     */
    public void modificarSaldo(PlayerStatus status, Long valor) {
        Long saldoAtual = status.getSaldoBanco();
        status.setSaldoBanco(Math.max(0, saldoAtual + valor));
        persist(status);
    }
}
