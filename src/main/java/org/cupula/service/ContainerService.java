package org.cupula.service;

import java.util.List;
import java.util.Random;

import org.cupula.model.containers.Container;
import org.cupula.model.containers.Inventario;
import org.cupula.model.containers.basecontainer.BaseContainerStructureUnit;
import org.cupula.model.containers.enums.ContainerTipo;
import org.cupula.model.entities.player.Player;
import org.cupula.repository.containers.ContainerRepository;
import org.cupula.repository.containers.InventarioRepository;
import org.cupula.repository.containers.basecontainer.BaseContainerStructureUnitRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ContainerService {

    @Inject
    ContainerRepository containerRepository;

    @Inject
    BaseContainerStructureUnitRepository baseContainerRepository;

    @Inject
    InventarioRepository inventarioRepository;

    private final Random random = new Random();

    /**
     * Sorteia uma configuração base de container ponderada pela possibilidade
     */
    private BaseContainerStructureUnit sortearBaseContainer(ContainerTipo tipo) {
        List<BaseContainerStructureUnit> bases = baseContainerRepository.findByTipo(tipo);
        
        if (bases.isEmpty()) {
            throw new IllegalStateException(
                "Nenhuma configuração base de container encontrada para tipo " + tipo
            );
        }

        // Seleção ponderada pela possibilidade
        long totalPossibilidade = bases.stream()
            .mapToLong(BaseContainerStructureUnit::getPossibilidade)
            .sum();
        
        long randomValue = random.nextLong(totalPossibilidade);
        long acumulado = 0;
        
        for (BaseContainerStructureUnit base : bases) {
            acumulado += base.getPossibilidade();
            if (randomValue < acumulado) {
                return base;
            }
        }
        
        return bases.get(bases.size() - 1); // fallback
    }

    /**
     * Cria um container baseado em uma configuração base
     * A capacidade e peso são limitados pelos valores da base
     */
    @Transactional
    public Container criarContainer(ContainerTipo tipo, Long capacidade, Long peso) {
        // Sorteia visual do container
        BaseContainerStructureUnit base = sortearBaseContainer(tipo);
        
        // Valida limites
        if (capacidade > base.getCapacidadeMaximaEspacoLimite()) {
            capacidade = base.getCapacidadeMaximaEspacoLimite();
        }
        
        if (peso > base.getPesoMaximoLimite()) {
            peso = base.getPesoMaximoLimite();
        }
        
        // Criar container
        Container container = new Container();
        container.setContainerTipo(tipo);
        container.setCapacidadeMaximaEspaco(capacidade);
        container.setPesoMaximo(peso);
        container.setStructureUnit(base.getStructureUnit());
        
        containerRepository.persist(container);
        
        return container;
    }

    /**
     * Cria um inventário para um player com visual aleatório
     * A capacidade é calculada pelo PlayerService e validada contra os limites da base
     */
    @Transactional
    public Inventario criarInventario(Player player, Long capacidadeCalculada, Long pesoCalculado) {
        // Sorteia visual do inventário
        BaseContainerStructureUnit base = sortearBaseContainer(ContainerTipo.INVENTARIO);
        
        // Valida limites - não pode exceder o máximo permitido pela base
        Long capacidadeFinal = Math.min(capacidadeCalculada, base.getCapacidadeMaximaEspacoLimite());
        Long pesoFinal = Math.min(pesoCalculado, base.getPesoMaximoLimite());
        
        // Criar inventário
        Inventario inventario = new Inventario();
        inventario.setContainerTipo(ContainerTipo.INVENTARIO);
        inventario.setCapacidadeMaximaEspaco(capacidadeFinal);
        inventario.setPesoMaximo(pesoFinal);
        inventario.setStructureUnit(base.getStructureUnit());
        inventario.setPlayer(player);
        
        inventarioRepository.persist(inventario);
        
        return inventario;
    }

    /**
     * Cria um baú no mundo com posição específica
     */
    @Transactional
    public Container criarBauNoMundo(Long x, Long y, Long z, Long capacidade, Long peso) {
        Container bau = criarContainer(ContainerTipo.BAU, capacidade, peso);
        bau.setPosicao(x, y, z);
        containerRepository.persist(bau);
        return bau;
    }
    
}
