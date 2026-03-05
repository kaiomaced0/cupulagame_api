package org.cupula.service;

import java.util.List;
import java.util.Random;

import org.cupula.model.auth.Usuario;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.entities.player.Player;
import org.cupula.model.structures.Structure;
import org.cupula.model.structures.basestructure.BaseStructureTipo;
import org.cupula.model.structures.enums.StructureTipo;
import org.cupula.repository.structures.StructureRepository;
import org.cupula.repository.structures.basestructure.BaseStructureTipoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StructureService {

    @Inject
    StructureRepository structureRepository;

    @Inject
    BaseStructureTipoRepository baseStructureTipoRepository;

    private final Random random = new Random();

    /**
     * Verifica se o player já possui uma HOME_PLAYER
     */
    public boolean playerTemHomePlayer(Player player) {
        if (player == null || player.getUsuario() == null) {
            return false;
        }
        
        Long count = structureRepository.count(
            "usuario.id = ?1 and tipo = ?2", 
            player.getUsuario().getId(), 
            StructureTipo.HOME_PLAYER
        );
        
        return count > 0;
    }

    /**
     * Sorteia uma configuração base de estrutura ponderada pela possibilidade
     */
    private BaseStructureTipo sortearBaseStructure(PlayerRaca raca, StructureTipo tipo) {
        List<BaseStructureTipo> bases = baseStructureTipoRepository.findByRacaAndTipo(raca, tipo);
        
        if (bases.isEmpty()) {
            throw new IllegalStateException(
                "Nenhuma configuração base de estrutura encontrada para raça " + raca + " e tipo " + tipo
            );
        }

        // Seleção ponderada pela possibilidade
        long totalPossibilidade = bases.stream()
            .mapToLong(BaseStructureTipo::getPossibilidade)
            .sum();
        
        long randomValue = random.nextLong(totalPossibilidade);
        long acumulado = 0;
        
        for (BaseStructureTipo base : bases) {
            acumulado += base.getPossibilidade();
            if (randomValue < acumulado) {
                return base;
            }
        }
        
        return bases.get(bases.size() - 1); // fallback
    }

    /**
     * Cria uma cópia da estrutura template
     */
    private Structure copiarStructure(Structure template) {
        Structure nova = new Structure();
        nova.setTipo(template.getTipo());
        // Nota: ItemStructures e Containers não são copiados por enquanto
        // Futuramente implementar cópia profunda se necessário
        return nova;
    }

    /**
     * Cria uma HOME_PLAYER para o player baseado na raça
     * @throws IllegalStateException se o player já possui uma HOME_PLAYER
     */
    @Transactional
    public Structure criarHomePlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player não pode ser null");
        }
        
        if (player.getUsuario() == null) {
            throw new IllegalArgumentException("Player deve estar associado a um Usuario");
        }
        
        if (player.getRaca() == null) {
            throw new IllegalArgumentException("Player deve ter uma raça definida");
        }
        
        // Validar se já tem HOME_PLAYER
        if (playerTemHomePlayer(player)) {
            throw new IllegalStateException("Player já possui uma HOME_PLAYER");
        }
        
        // Sortear estrutura base
        BaseStructureTipo baseStructure = sortearBaseStructure(player.getRaca(), StructureTipo.HOME_PLAYER);
        
        // Criar nova estrutura baseada no template
        Structure homePlayer = copiarStructure(baseStructure.getStructureTemplate());
        homePlayer.setUsuario(player.getUsuario());
        
        // Persistir
        structureRepository.persist(homePlayer);
        
        return homePlayer;
    }

    /**
     * Lista todas as estruturas de um usuário
     */
    public List<Structure> listarStructuresDoUsuario(Usuario usuario) {
        return structureRepository.find("usuario.id = ?1", usuario.getId()).list();
    }

    /**
     * Lista todas as estruturas de um tipo específico de um usuário
     */
    public List<Structure> listarStructuresDoUsuarioPorTipo(Usuario usuario, StructureTipo tipo) {
        return structureRepository.find("usuario.id = ?1 and tipo = ?2", usuario.getId(), tipo).list();
    }
    
}
