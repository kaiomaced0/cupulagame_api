package org.cupula.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.cupula.dto.structures.json.JsonContainer;
import org.cupula.dto.structures.json.JsonStructure;
import org.cupula.dto.structures.json.JsonStructureUnit;
import org.cupula.dto.structures.json.JsonStructureUnitPart;
import org.cupula.dto.structures.request.AtualizarStructureRequest;
import org.cupula.dto.structures.request.CriarStructureRequest;
import org.cupula.dto.structures.response.StructureResponse;
import org.cupula.dto.structures.response.StructureUnitResponse;
import org.cupula.model.auth.Usuario;
import org.cupula.model.containers.Container;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.entities.player.Player;
import org.cupula.model.structures.Material;
import org.cupula.model.structures.Structure;
import org.cupula.model.structures.StructureUnit;
import org.cupula.model.structures.StructureUnitPart;
import org.cupula.model.structures.basestructure.BaseStructure;
import org.cupula.model.structures.enums.StructureTipo;
import org.cupula.model.structures.view.ColorMaterial;
import org.cupula.repository.auth.UsuarioRepository;
import org.cupula.repository.containers.ContainerRepository;
import org.cupula.repository.structures.MaterialRepository;
import org.cupula.repository.structures.StructureRepository;
import org.cupula.repository.structures.StructureUnitPartRepository;
import org.cupula.repository.structures.StructureUnitRepository;
import org.cupula.repository.structures.basestructure.BaseStructureRepository;
import org.cupula.repository.structures.view.ColorMaterialRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StructureService {

    @Inject
    StructureRepository structureRepository;

    @Inject
    BaseStructureRepository baseStructureRepository;

    @Inject
    StructureUnitRepository structureUnitRepository;

    @Inject
    StructureUnitPartRepository structureUnitPartRepository;

    @Inject
    ContainerRepository containerRepository;

    @Inject
    MaterialRepository materialRepository;

    @Inject
    ColorMaterialRepository colorMaterialRepository;

    @Inject
    UsuarioRepository usuarioRepository;

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
    private BaseStructure sortearBaseStructure(PlayerRaca raca, StructureTipo tipo) {
        List<BaseStructure> bases = baseStructureRepository.findByRacaAndTipo(raca, tipo);
        
        if (bases.isEmpty()) {
            throw new IllegalStateException(
                "Nenhuma configuração base de estrutura encontrada para raça " + raca + " e tipo " + tipo
            );
        }

        // Seleção ponderada pela possibilidade
        long totalPossibilidade = bases.stream()
            .mapToLong(BaseStructure::getPossibilidade)
            .sum();
        
        long randomValue = random.nextLong(totalPossibilidade);
        long acumulado = 0;
        
        for (BaseStructure base : bases) {
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
        BaseStructure baseStructure = sortearBaseStructure(player.getRaca(), StructureTipo.HOME_PLAYER);
        
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

    // ==================== NOVOS MÉTODOS DE CRUD ====================

    /**
     * Lista structures do usuário com hierarquia completa (StructureUnit -> StructureUnitPart)
     */
    public List<StructureResponse> listarPorUsuarioComHierarquia(Long usuarioId) {
        if (usuarioId == null) {
            return List.of();
        }

        return structureRepository.find("usuario.id", usuarioId).list().stream()
                .map(StructureResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * Busca structure por id do usuário com hierarquia completa
     */
    public StructureResponse buscarPorIdComHierarquia(Long id, Long usuarioId) {
        if (id == null || usuarioId == null) {
            return null;
        }

        Structure structure = structureRepository.find("id = ?1 and usuario.id = ?2", id, usuarioId).firstResult();
        return StructureResponse.from(structure);
    }

    /**
     * Cria uma structure vinculada ao usuário (sem ilha, arena, tipo null por padrão)
     */
    @Transactional
    public StructureResponse criar(Usuario usuario, CriarStructureRequest request) {
        if (usuario == null || request == null) {
            throw new IllegalArgumentException("Usuario e request sao obrigatorios");
        }

        Structure structure = new Structure();
        structure.setNome(request.nome());
        structure.setTipo(request.tipo());
        structure.setUsuario(usuario);
        structure.setAtivo(true);

        structureRepository.persist(structure);
        return StructureResponse.from(structure);
    }

    /**
     * Atualiza structure do usuário
     */
    @Transactional
    public StructureResponse atualizar(Long id, Long usuarioId, AtualizarStructureRequest request) {
        if (id == null || usuarioId == null || request == null) {
            throw new IllegalArgumentException("ID, usuarioId e request sao obrigatorios");
        }

        Structure structure = structureRepository.find("id = ?1 and usuario.id = ?2", id, usuarioId).firstResult();
        if (structure == null) {
            return null;
        }

        if (request.nome() != null) {
            structure.setNome(request.nome());
        }
        if (request.tipo() != null) {
            structure.setTipo(request.tipo());
        }

        return StructureResponse.from(structure);
    }

    /**
     * Deleta structure do usuário
     */
    @Transactional
    public boolean deletar(Long id, Long usuarioId) {
        if (id == null || usuarioId == null) {
            return false;
        }

        Structure structure = structureRepository.find("id = ?1 and usuario.id = ?2", id, usuarioId).firstResult();
        if (structure == null) {
            return false;
        }

        structureRepository.delete(structure);
        return true;
    }

    /**
     * Adiciona (acao=1) ou remove (acao=0) Structure das favoritas do usuário
     */
    @Transactional
    public void adicionarOuRemoverStructureFavorita(Usuario usuario, Long structureId, Integer acao) {
        if (usuario == null || structureId == null || acao == null) {
            throw new IllegalArgumentException("Usuario, structureId e acao sao obrigatorios");
        }

        if (acao != 0 && acao != 1) {
            throw new IllegalArgumentException("Acao deve ser 0 (remover) ou 1 (adicionar)");
        }

        Structure structure = structureRepository.findById(structureId);
        if (structure == null) {
            throw new IllegalArgumentException("Structure nao encontrada");
        }

        if (acao == 1) {
            // Adicionar - Set já garante que não terá duplicados
            boolean adicionado = usuario.getStructureFavoritas().add(structure);
            if (!adicionado) {
                throw new IllegalArgumentException("Structure ja esta nas favoritas");
            }
        } else {
            // Remover
            boolean removido = usuario.getStructureFavoritas().remove(structure);
            if (!removido) {
                throw new IllegalArgumentException("Structure nao esta nas favoritas");
            }
        }

        usuarioRepository.persist(usuario);
    }

    /**
     * Adiciona (acao=1) ou remove (acao=0) StructureUnit das favoritas do usuário
     */
    @Transactional
    public void adicionarOuRemoverStructureUnitFavorita(Usuario usuario, Long structureUnitId, Integer acao) {
        if (usuario == null || structureUnitId == null || acao == null) {
            throw new IllegalArgumentException("Usuario, structureUnitId e acao sao obrigatorios");
        }

        if (acao != 0 && acao != 1) {
            throw new IllegalArgumentException("Acao deve ser 0 (remover) ou 1 (adicionar)");
        }

        StructureUnit structureUnit = structureUnitRepository.findById(structureUnitId);
        if (structureUnit == null) {
            throw new IllegalArgumentException("StructureUnit nao encontrada");
        }

        if (acao == 1) {
            // Adicionar - Set já garante que não terá duplicados
            boolean adicionado = usuario.getStructureUnitFavoritas().add(structureUnit);
            if (!adicionado) {
                throw new IllegalArgumentException("StructureUnit ja esta nas favoritas");
            }
        } else {
            // Remover
            boolean removido = usuario.getStructureUnitFavoritas().remove(structureUnit);
            if (!removido) {
                throw new IllegalArgumentException("StructureUnit nao esta nas favoritas");
            }
        }

        usuarioRepository.persist(usuario);
    }

    // ==================== MÉTODOS DE IMPORT/EXPORT ====================

    /**
     * Exporta Structure para formato JSON (sem IDs, apenas estrutura visual)
     */
    public JsonStructure exportStructure(Long id, Long usuarioId) {
        if (id == null || usuarioId == null) {
            throw new IllegalArgumentException("ID e usuarioId sao obrigatorios");
        }

        Structure structure = structureRepository.find("id = ?1 and usuario.id = ?2", id, usuarioId).firstResult();
        if (structure == null) {
            throw new IllegalArgumentException("Structure nao encontrada");
        }

        // Converter StructureUnits
        List<JsonStructureUnit> jsonUnits = structure.getStructureUnits() != null
            ? structure.getStructureUnits().stream()
                .map(this::convertStructureUnitToJson)
                .collect(Collectors.toList())
            : new ArrayList<>();

        // Converter Containers (apenas structureUnit)
        List<JsonContainer> jsonContainers = structure.getContainers() != null
            ? structure.getContainers().stream()
                .map(c -> new JsonContainer(convertStructureUnitToJson(c.getStructureUnit())))
                .collect(Collectors.toList())
            : new ArrayList<>();

        return new JsonStructure(
            structure.getNome(),
            structure.getTipo(),
            jsonUnits,
            jsonContainers
        );
    }

    /**
     * Importa Structure de formato JSON (cria nova structure vinculada ao usuário)
     */
    @Transactional
    public StructureResponse importStructure(Usuario usuario, JsonStructure json) {
        if (usuario == null || json == null) {
            throw new IllegalArgumentException("Usuario e json sao obrigatorios");
        }

        Structure structure = new Structure();
        structure.setNome(json.nome());
        structure.setTipo(json.tipo());
        structure.setUsuario(usuario);
        structure.setAtivo(true);

        structureRepository.persist(structure);

        // Importar StructureUnits
        if (json.structureUnits() != null) {
            List<StructureUnit> units = json.structureUnits().stream()
                .map(jsonUnit -> convertJsonToStructureUnit(jsonUnit, usuario))
                .collect(Collectors.toList());
            structure.setStructureUnits(units);
        }

        // Importar Containers
        if (json.containers() != null) {
            List<Container> containers = json.containers().stream()
                .map(jsonContainer -> {
                    Container container = new Container();
                    StructureUnit unit = convertJsonToStructureUnit(jsonContainer.structureUnit(), usuario);
                    container.setStructureUnit(unit);
                    container.setAtivo(true);
                    containerRepository.persist(container);
                    return container;
                })
                .collect(Collectors.toList());
            structure.setContainers(containers);
        }

        return StructureResponse.from(structure);
    }

    /**
     * Exporta StructureUnit para formato JSON (sem IDs, apenas estrutura visual)
     */
    public JsonStructureUnit exportStructureUnit(Long id, Long usuarioId) {
        if (id == null || usuarioId == null) {
            throw new IllegalArgumentException("ID e usuarioId sao obrigatorios");
        }

        StructureUnit structureUnit = structureUnitRepository.find("id = ?1 and usuario.id = ?2", id, usuarioId).firstResult();
        if (structureUnit == null) {
            throw new IllegalArgumentException("StructureUnit nao encontrada");
        }

        return convertStructureUnitToJson(structureUnit);
    }

    /**
     * Importa StructureUnit de formato JSON (cria nova structureUnit vinculada ao usuário)
     */
    @Transactional
    public StructureUnitResponse importStructureUnit(Usuario usuario, JsonStructureUnit json) {
        if (usuario == null || json == null) {
            throw new IllegalArgumentException("Usuario e json sao obrigatorios");
        }

        StructureUnit structureUnit = convertJsonToStructureUnit(json, usuario);
        return StructureUnitResponse.from(structureUnit);
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private JsonStructureUnit convertStructureUnitToJson(StructureUnit unit) {
        if (unit == null) return null;

        List<JsonStructureUnitPart> jsonParts = unit.getParts() != null
            ? unit.getParts().stream()
                .map(part -> new JsonStructureUnitPart(
                    part.getNome(),
                    part.getInicioX(),
                    part.getInicioY(),
                    part.getInicioZ(),
                    part.getFimX(),
                    part.getFimY(),
                    part.getFimZ(),
                    part.getMaterial() != null ? part.getMaterial().getId() : null,
                    part.getColor() != null ? part.getColor().getId() : null,
                    part.getAreaContato()
                ))
                .collect(Collectors.toList())
            : new ArrayList<>();

        return new JsonStructureUnit(
            unit.getNome(),
            unit.getLayer(),
            unit.getTipo(),
            jsonParts
        );
    }

    private StructureUnit convertJsonToStructureUnit(JsonStructureUnit json, Usuario usuario) {
        if (json == null) return null;

        StructureUnit unit = new StructureUnit();
        unit.setNome(json.nome());
        unit.setLayer(json.layer());
        unit.setTipo(json.tipo());
        unit.setUsuario(usuario);
        unit.setAtivo(true);

        structureUnitRepository.persist(unit);

        // Importar Parts
        if (json.parts() != null) {
            List<StructureUnitPart> parts = json.parts().stream()
                .map(jsonPart -> {
                    StructureUnitPart part = new StructureUnitPart();
                    part.setNome(jsonPart.nome());
                    part.setInicioX(jsonPart.inicioX());
                    part.setInicioY(jsonPart.inicioY());
                    part.setInicioZ(jsonPart.inicioZ());
                    part.setFimX(jsonPart.fimX());
                    part.setFimY(jsonPart.fimY());
                    part.setFimZ(jsonPart.fimZ());
                    part.setAreaContato(jsonPart.areaContato());
                    part.setAtivo(true);

                    // Buscar Material e ColorMaterial se fornecidos
                    if (jsonPart.materialId() != null) {
                        Material material = materialRepository.findById(jsonPart.materialId());
                        part.setMaterial(material);
                    }
                    if (jsonPart.colorMaterialId() != null) {
                        ColorMaterial color = colorMaterialRepository.findById(jsonPart.colorMaterialId());
                        part.setColor(color);
                    }

                    structureUnitPartRepository.persist(part);
                    return part;
                })
                .collect(Collectors.toList());
            unit.setParts(parts);
        }

        return unit;
    }
    
}
