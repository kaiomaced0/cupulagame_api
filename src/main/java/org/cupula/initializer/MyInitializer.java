package org.cupula.initializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.cupula.dto.auth.CreateUsuarioRequest;
import org.cupula.dto.pessoa.CreatePessoaRequest;
import org.cupula.dto.player.request.CriarPlayerRequest;
import org.cupula.model.auth.Usuario;
import org.cupula.model.auth.enums.Perfil;
import org.cupula.model.auth.pessoa.Sexo;
import org.cupula.model.entities.baseview.PlayerTipoBaseColorSkin;
import org.cupula.model.entities.baseview.PlayerTipoBaseTamanho;
import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.baseview.orelha.PlayerTipoBaseOrelha;
import org.cupula.model.entities.baseview.orelha.TipoOrelhaColorMaterial;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.structures.view.ColorMaterial;
import org.cupula.model.containers.basecontainer.BaseContainerStructureUnit;
import org.cupula.model.containers.enums.ContainerTipo;
import org.cupula.model.structures.Material;
import org.cupula.model.structures.StructureUnit;
import org.cupula.model.structures.StructureUnitPart;
import org.cupula.model.structures.enums.Layer;
import org.cupula.model.structures.enums.StructureUnitTipo;
import org.cupula.model.structures.basestructure.BaseStructure;
import org.cupula.model.structures.enums.StructureTipo;
import org.cupula.repository.auth.UsuarioRepository;
import org.cupula.repository.playertipo.PlayerTipoBaseColorSkinRepository;
import org.cupula.repository.playertipo.PlayerTipoBaseOrelhaRepository;
import org.cupula.repository.playertipo.PlayerTipoBaseTamanhoRepository;
import org.cupula.repository.playertipo.PlayerTipoCabeloRepository;
import org.cupula.repository.playertipo.TipoOrelhaColorMaterialRepository;
import org.cupula.repository.structures.view.ColorMaterialRepository;
import org.cupula.service.PessoaService;
import org.cupula.service.PlayerService;
import org.cupula.service.UsuarioService;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MyInitializer {

    @Inject
    PessoaService pessoaService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    PlayerService playerService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    ColorMaterialRepository colorMaterialRepository;

    @Inject
    PlayerTipoBaseColorSkinRepository playerTipoBaseColorSkinRepository;

    @Inject
    PlayerTipoBaseTamanhoRepository playerTipoBaseTamanhoRepository;

    @Inject
    TipoOrelhaColorMaterialRepository tipoOrelhaColorMaterialRepository;

    @Inject
    PlayerTipoBaseOrelhaRepository playerTipoBaseOrelhaRepository;

    @Inject
    PlayerTipoCabeloRepository playerTipoCabeloRepository;

    @Inject
    org.cupula.repository.structures.MaterialRepository materialRepository;

    @Inject
    org.cupula.repository.structures.StructureUnitRepository structureUnitRepository;

    @Inject
    org.cupula.repository.structures.StructureUnitPartRepository structureUnitPartRepository;

    @Inject
    org.cupula.repository.containers.basecontainer.BaseContainerStructureUnitRepository baseContainerStructureUnitRepository;

    @Inject
    org.cupula.repository.items.ItemTipoRepository itemTipoRepository;

    @Inject
    org.cupula.repository.structures.basestructure.BaseStructureRepository baseStructureRepository;

    @Inject
    org.cupula.repository.structures.StructureRepository structureRepository;

    @Transactional
    public void init(@Observes StartupEvent event) {
        System.out.println("=== Iniciando Seeds ===");
        
        // Seed de ItemTipo (primeiro, pois são usados em vários lugares)
        seedItemTipos();
        
        // Seed de PlayerTipo (primeiro, pois são usados na criação de players)
        seedPlayerTipoBaseColorSkin();
        seedPlayerTipoBaseTamanho();
        seedTipoOrelhaColorMaterial();
        seedPlayerTipoBaseOrelha();
        seedPlayerTipoCabelo();
        
        // Seed de Base Containers
        seedBaseContainers();
        
        // Seed de Base Structures (HOME_PLAYER para cada raça)
        seedBaseStructures();
        
        // Seed de Pessoas
        seedPessoas();
        
        // Seed de Usuários
        seedUsuarios();
        
        // Seed de Players
        seedPlayers();
        
        System.out.println("=== Seeds concluídos ===");
    }

    private void seedPessoas() {
        try {
            // Pessoa 1
            pessoaService.createPessoa(new CreatePessoaRequest(
                "João Silva",
                "12345678901",
                LocalDate.of(1990, 5, 15),
                Sexo.MASCULINO
            ));
            System.out.println("✓ Pessoa 1 criada: João Silva");

            // Pessoa 2
            pessoaService.createPessoa(new CreatePessoaRequest(
                "Maria Santos",
                "98765432109",
                LocalDate.of(1995, 8, 22),
                Sexo.FEMININO
            ));
            System.out.println("✓ Pessoa 2 criada: Maria Santos");

            // Pessoa 3
            pessoaService.createPessoa(new CreatePessoaRequest(
                "Pedro Oliveira",
                "11122233344",
                LocalDate.of(1988, 12, 10),
                Sexo.MASCULINO
            ));
            System.out.println("✓ Pessoa 3 criada: Pedro Oliveira");

            // Pessoa 4
            pessoaService.createPessoa(new CreatePessoaRequest(
                "Ana Costa",
                "55566677788",
                LocalDate.of(2000, 3, 5),
                Sexo.FEMININO
            ));
            System.out.println("✓ Pessoa 4 criada: Ana Costa");

            // Pessoa 5
            pessoaService.createPessoa(new CreatePessoaRequest(
                "Carlos Pereira",
                "99988877766",
                LocalDate.of(1992, 7, 18),
                Sexo.MASCULINO
            ));
            System.out.println("✓ Pessoa 5 criada: Carlos Pereira");

        } catch (Exception e) {
            System.err.println("Erro ao criar pessoas: " + e.getMessage());
        }
    }

    private void seedUsuarios() {
        try {
            // Usuário Admin
            usuarioService.createUsuario(new CreateUsuarioRequest(
                "admin",
                "admin@cupula.com",
                "admin123"
            ));
            
            // Atualizar perfil para ADMIN
            Usuario admin = usuarioRepository.findByNickName("admin");
            if (admin != null) {
                admin.getPerfis().add(Perfil.ADMIN);
                usuarioRepository.persist(admin);
            }
            System.out.println("✓ Usuário Admin criado");

            // Usuário Teste 1
            usuarioService.createUsuario(new CreateUsuarioRequest(
                "0Glacks",
                "0glacks@cupula.com",
                "senha123"
            ));
            System.out.println("✓ Usuário 0Glacks criado");

            // Usuário Teste 2
            usuarioService.createUsuario(new CreateUsuarioRequest(
                "Glacks",
                "glacks@cupula.com",
                "senha123"
            ));
            System.out.println("✓ Usuário Glacks criado");

            // Usuário Teste 3
            usuarioService.createUsuario(new CreateUsuarioRequest(
                "Gobila",
                "gobila@cupula.com",
                "senha123"
            ));
            System.out.println("✓ Usuário Gobila criado");

            // Usuário Teste 4
            usuarioService.createUsuario(new CreateUsuarioRequest(
                "Capelao",
                "capelao@cupula.com",
                "senha123"
            ));
            System.out.println("✓ Usuário Capelao criado");

        } catch (Exception e) {
            System.err.println("Erro ao criar usuários: " + e.getMessage());
        }
    }

    private void seedPlayers() {
        try {
            // Criar players para alguns usuários
            Usuario usuario1 = usuarioRepository.findByNickName("0Glacks");
            if (usuario1 != null) {
                playerService.criarPlayer(usuario1, new CriarPlayerRequest());
                playerService.criarPlayer(usuario1, new CriarPlayerRequest());
                playerService.criarPlayer(usuario1, new CriarPlayerRequest());
                System.out.println("✓ 3 Players criados para 0Glacks");
            }

            Usuario usuario2 = usuarioRepository.findByNickName("Glacks");
            if (usuario2 != null) {
                playerService.criarPlayer(usuario2, new CriarPlayerRequest());
                System.out.println("✓ Player criado para Glacks");
            }

            Usuario usuario3 = usuarioRepository.findByNickName("Gobila");
            if (usuario3 != null) {
                playerService.criarPlayer(usuario3, new CriarPlayerRequest());
                System.out.println("✓ Player criado para Gobila");
            }

            Usuario usuario4 = usuarioRepository.findByNickName("Capelao");
            if (usuario4 != null) {
                playerService.criarPlayer(usuario4, new CriarPlayerRequest());
                playerService.criarPlayer(usuario4, new CriarPlayerRequest());
                playerService.criarPlayer(usuario4, new CriarPlayerRequest());
                System.out.println("✓ 3 Players criados para Capelao");
            }

        } catch (Exception e) {
            System.err.println("Erro ao criar players: " + e.getMessage());
        }
    }

    private void seedPlayerTipoBaseColorSkin() {
        try {
            // HUMANO - Todas as variações naturais
            createPlayerTipoBaseColorSkin(PlayerRaca.HUMANO, 20L, 1L);
            createPlayerTipoBaseColorSkin(PlayerRaca.HUMANO, 30L, 2L);
            createPlayerTipoBaseColorSkin(PlayerRaca.HUMANO, 30L, 3L);
            createPlayerTipoBaseColorSkin(PlayerRaca.HUMANO, 20L, 4L);

            // ELFO - Predominantemente tons claros
            createPlayerTipoBaseColorSkin(PlayerRaca.ELFO, 40L, 1L);
            createPlayerTipoBaseColorSkin(PlayerRaca.ELFO, 35L, 2L);
            createPlayerTipoBaseColorSkin(PlayerRaca.ELFO, 20L, 3L);
            createPlayerTipoBaseColorSkin(PlayerRaca.ELFO, 5L, 4L);

            // ANÃO - Predominantemente tons médios/escuros
            createPlayerTipoBaseColorSkin(PlayerRaca.ANAO, 15L, 1L);
            createPlayerTipoBaseColorSkin(PlayerRaca.ANAO, 30L, 2L);
            createPlayerTipoBaseColorSkin(PlayerRaca.ANAO, 35L, 3L);
            createPlayerTipoBaseColorSkin(PlayerRaca.ANAO, 20L, 4L);

            // ORC - Tons verdes e cinzas
            createPlayerTipoBaseColorSkin(PlayerRaca.ORC, 30L, 5L);
            createPlayerTipoBaseColorSkin(PlayerRaca.ORC, 40L, 6L);
            createPlayerTipoBaseColorSkin(PlayerRaca.ORC, 25L, 7L);
            createPlayerTipoBaseColorSkin(PlayerRaca.ORC, 5L, 3L);

            // GOBLIN - Tons verdes e amarronzados
            createPlayerTipoBaseColorSkin(PlayerRaca.GOBLIN, 35L, 5L);
            createPlayerTipoBaseColorSkin(PlayerRaca.GOBLIN, 30L, 6L);
            createPlayerTipoBaseColorSkin(PlayerRaca.GOBLIN, 25L, 7L);
            createPlayerTipoBaseColorSkin(PlayerRaca.GOBLIN, 10L, 4L);

            // GIGANTE - Tons terrosos variados
            createPlayerTipoBaseColorSkin(PlayerRaca.GIGANTE, 25L, 2L);
            createPlayerTipoBaseColorSkin(PlayerRaca.GIGANTE, 30L, 3L);
            createPlayerTipoBaseColorSkin(PlayerRaca.GIGANTE, 30L, 4L);
            createPlayerTipoBaseColorSkin(PlayerRaca.GIGANTE, 15L, 7L);

            System.out.println("✓ PlayerTipoBaseColorSkin criados (24 registros)");
        } catch (Exception e) {
            System.err.println("Erro ao criar PlayerTipoBaseColorSkin: " + e.getMessage());
        }
    }

    private void createPlayerTipoBaseColorSkin(PlayerRaca raca, Long possibilidade, Long colorMaterialId) {
        ColorMaterial color = colorMaterialRepository.findById(colorMaterialId);
        if (color != null) {
            PlayerTipoBaseColorSkin entity = new PlayerTipoBaseColorSkin();
            entity.setPlayerRaca(raca);
            entity.setPossibilidade(possibilidade);
            entity.setColorMaterial(color);
            entity.setAtivo(true);
            entity.setDataInclusao(LocalDateTime.now());
            playerTipoBaseColorSkinRepository.persist(entity);
        }
    }

    private void seedPlayerTipoBaseTamanho() {
        try {
            // ANÃO - Pequeno e robusto (120-150cm altura) - 10 variações
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 12L, 118L, 125L, 80L, 110L, 45L, 65L);    // Anão Muito Atarracado
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 15L, 120L, 130L, 70L, 100L, 40L, 60L);    // Anão Atarracado
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 18L, 125L, 135L, 75L, 105L, 42L, 62L);    // Anão Muito Largo
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 20L, 130L, 140L, 65L, 95L, 38L, 58L);     // Anão Robusto
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 15L, 132L, 142L, 68L, 98L, 39L, 59L);     // Anão Forte
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 12L, 135L, 145L, 63L, 93L, 37L, 57L);     // Anão Equilibrado
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 8L, 140L, 150L, 60L, 90L, 35L, 55L);      // Anão Alto
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 6L, 138L, 148L, 72L, 102L, 40L, 60L);     // Anão Alto e Largo
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 4L, 128L, 138L, 58L, 88L, 33L, 53L);      // Anão Magro (raro)
            createPlayerTipoBaseTamanho(PlayerRaca.ANAO, 2L, 145L, 152L, 55L, 85L, 32L, 52L);      // Anão Muito Alto (muito raro)

            // HUMANO - Médio e equilibrado (160-195cm altura) - 12 variações
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 8L, 158L, 165L, 45L, 65L, 28L, 43L);    // Humano Muito Magro
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 12L, 160L, 170L, 50L, 70L, 30L, 45L);   // Humano Magro
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 15L, 165L, 175L, 52L, 72L, 31L, 46L);   // Humano Esbelto
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 18L, 165L, 178L, 55L, 75L, 32L, 48L);   // Humano Médio
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 16L, 170L, 180L, 56L, 76L, 33L, 49L);   // Humano Normal
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 14L, 172L, 185L, 58L, 80L, 35L, 50L);   // Humano Atlético
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 10L, 175L, 188L, 62L, 85L, 36L, 52L);   // Humano Forte
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 8L, 175L, 190L, 65L, 90L, 38L, 55L);    // Humano Musculoso
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 6L, 178L, 192L, 68L, 93L, 40L, 57L);    // Humano Muito Forte
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 5L, 160L, 168L, 70L, 95L, 40L, 58L);    // Humano Robusto
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 3L, 182L, 195L, 70L, 98L, 42L, 60L);    // Humano Gigante
            createPlayerTipoBaseTamanho(PlayerRaca.HUMANO, 2L, 155L, 162L, 42L, 62L, 26L, 41L);    // Humano Pequeno (raro)

            // ORC - Alto e muito largo (180-225cm altura) - 10 variações
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 10L, 178L, 190L, 85L, 120L, 43L, 70L);     // Orc Guerreiro Ágil
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 15L, 180L, 195L, 90L, 130L, 45L, 75L);     // Orc Guerreiro
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 18L, 185L, 200L, 95L, 135L, 48L, 78L);     // Orc Forte
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 20L, 190L, 205L, 98L, 138L, 50L, 80L);     // Orc Brutal
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 15L, 195L, 210L, 100L, 140L, 52L, 82L);    // Orc Gigante
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 10L, 200L, 215L, 105L, 145L, 54L, 84L);    // Orc Colossal
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 6L, 205L, 220L, 110L, 150L, 56L, 86L);     // Orc Titã
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 4L, 185L, 198L, 105L, 145L, 52L, 82L);     // Orc Muito Largo
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 3L, 210L, 225L, 112L, 155L, 58L, 88L);     // Orc Lendário
            createPlayerTipoBaseTamanho(PlayerRaca.ORC, 2L, 182L, 195L, 78L, 115L, 40L, 68L);      // Orc Esguio (raro)

            // ELFO - Bem mais alto e esbelto (180-220cm altura) - 12 variações
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 10L, 180L, 190L, 38L, 56L, 24L, 38L);     // Elfo Delicado
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 15L, 185L, 195L, 40L, 60L, 25L, 40L);     // Elfo Esbelto
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 18L, 190L, 200L, 42L, 62L, 26L, 41L);     // Elfo Elegante
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 20L, 195L, 205L, 43L, 63L, 27L, 42L);     // Elfo Alto
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 15L, 198L, 208L, 44L, 64L, 27L, 43L);     // Elfo Nobre
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 12L, 200L, 210L, 45L, 65L, 28L, 44L);     // Elfo Muito Alto
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 8L, 205L, 215L, 47L, 67L, 29L, 45L);      // Elfo Majestoso
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 6L, 208L, 218L, 48L, 68L, 30L, 46L);      // Elfo Ancião
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 4L, 210L, 220L, 50L, 70L, 31L, 47L);      // Elfo Régio
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 3L, 195L, 205L, 48L, 68L, 29L, 45L);      // Elfo Bem Proporcionado
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 2L, 212L, 220L, 52L, 72L, 32L, 48L);      // Elfo Supremo (raro)
            createPlayerTipoBaseTamanho(PlayerRaca.ELFO, 1L, 178L, 188L, 36L, 54L, 23L, 37L);      // Elfo Jovem (muito raro)

            // GOBLIN - Muito pequeno e ágil (90-120cm altura) - 10 variações
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 12L, 90L, 98L, 45L, 65L, 28L, 42L);      // Goblin Franzino
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 15L, 95L, 103L, 48L, 68L, 30L, 44L);     // Goblin Pequeno
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 18L, 98L, 106L, 50L, 70L, 31L, 45L);     // Goblin Rápido
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 20L, 100L, 108L, 52L, 72L, 32L, 46L);    // Goblin Normal
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 15L, 103L, 111L, 54L, 74L, 33L, 47L);    // Goblin Ágil
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 12L, 105L, 113L, 56L, 76L, 34L, 48L);    // Goblin Robusto
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 8L, 108L, 116L, 58L, 78L, 35L, 49L);     // Goblin Forte
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 6L, 110L, 118L, 60L, 80L, 36L, 50L);     // Goblin Guerreiro
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 4L, 112L, 120L, 62L, 82L, 37L, 51L);     // Goblin Alto (raro)
            createPlayerTipoBaseTamanho(PlayerRaca.GOBLIN, 2L, 88L, 96L, 42L, 62L, 26L, 40L);       // Goblin Minúsculo (muito raro)

            // GIGANTE - Enorme e volumoso (230-280cm altura) - 10 variações
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 12L, 230L, 245L, 115L, 160L, 60L, 92L);  // Gigante Jovem
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 15L, 235L, 250L, 120L, 165L, 62L, 95L);  // Gigante Esbelto
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 18L, 240L, 255L, 125L, 170L, 64L, 97L);  // Gigante Normal
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 20L, 245L, 260L, 130L, 175L, 66L, 100L); // Gigante Forte
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 15L, 250L, 265L, 135L, 180L, 68L, 102L); // Gigante Poderoso
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 12L, 255L, 270L, 140L, 185L, 70L, 105L); // Gigante Ancestral
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 8L, 260L, 275L, 145L, 190L, 72L, 107L);  // Gigante Titânico
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 6L, 265L, 280L, 150L, 195L, 74L, 110L);  // Gigante Colossal
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 4L, 228L, 243L, 145L, 190L, 70L, 105L);  // Gigante Maciço (raro)
            createPlayerTipoBaseTamanho(PlayerRaca.GIGANTE, 2L, 270L, 280L, 155L, 200L, 76L, 112L);  // Gigante Lendário (muito raro)

            System.out.println("✓ PlayerTipoBaseTamanho criados (64 registros)");
        } catch (Exception e) {
            System.err.println("Erro ao criar PlayerTipoBaseTamanho: " + e.getMessage());
        }
    }

    private void createPlayerTipoBaseTamanho(PlayerRaca raca, Long possibilidade, 
            Long xMin, Long xMax, Long yMin, Long yMax, Long zMin, Long zMax) {
        PlayerTipoBaseTamanho entity = new PlayerTipoBaseTamanho();
        entity.setPlayerRaca(raca);
        entity.setPossibilidade(possibilidade);
        entity.setEixoXMinimo(xMin);
        entity.setEixoXMaximo(xMax);
        entity.setEixoYMinimo(yMin);
        entity.setEixoYMaximo(yMax);
        entity.setEixoZMinimo(zMin);
        entity.setEixoZMaximo(zMax);
        entity.setAtivo(true);
        entity.setDataInclusao(LocalDateTime.now());
        playerTipoBaseTamanhoRepository.persist(entity);
    }

    private void seedTipoOrelhaColorMaterial() {
        try {
            createTipoOrelhaColorMaterial(8L, 25L);   // Orelha Natural Clara
            createTipoOrelhaColorMaterial(9L, 25L);   // Orelha Natural Média
            createTipoOrelhaColorMaterial(10L, 25L);  // Orelha Natural Escura
            createTipoOrelhaColorMaterial(11L, 15L);  // Orelha Élfica Dourada
            createTipoOrelhaColorMaterial(12L, 10L);  // Orelha Élfica Prateada

            System.out.println("✓ TipoOrelhaColorMaterial criados (5 registros)");
        } catch (Exception e) {
            System.err.println("Erro ao criar TipoOrelhaColorMaterial: " + e.getMessage());
        }
    }

    private void createTipoOrelhaColorMaterial(Long colorMaterialId, Long possibilidade) {
        ColorMaterial color = colorMaterialRepository.findById(colorMaterialId);
        if (color != null) {
            TipoOrelhaColorMaterial entity = new TipoOrelhaColorMaterial();
            entity.setColorMaterial(color);
            entity.setPossibilidade(possibilidade);
            entity.setAtivo(true);
            entity.setDataInclusao(LocalDateTime.now());
            tipoOrelhaColorMaterialRepository.persist(entity);
        }
    }

    private void seedPlayerTipoBaseOrelha() {
        try {
            // ANÃO - Orelhas pequenas e arredondadas (9 variações)
            createPlayerTipoBaseOrelha(PlayerRaca.ANAO, 15L, 2L, 3L, 3L, 4L, 2L, 3L, List.of(1L, 2L, 3L));    // Orelha Anã Muito Pequena
            createPlayerTipoBaseOrelha(PlayerRaca.ANAO, 20L, 2L, 3L, 4L, 5L, 2L, 3L, List.of(1L, 2L, 3L));    // Orelha Anã Pequena
            createPlayerTipoBaseOrelha(PlayerRaca.ANAO, 25L, 3L, 4L, 4L, 6L, 2L, 3L, List.of(1L, 2L, 3L));    // Orelha Anã Normal
            createPlayerTipoBaseOrelha(PlayerRaca.ANAO, 18L, 3L, 4L, 5L, 6L, 2L, 3L, List.of(1L, 2L, 3L));    // Orelha Anã Arredondada
            createPlayerTipoBaseOrelha(PlayerRaca.ANAO, 12L, 2L, 4L, 5L, 7L, 2L, 3L, List.of(1L, 2L, 3L));    // Orelha Anã Destacada
            createPlayerTipoBaseOrelha(PlayerRaca.ANAO, 8L, 3L, 5L, 4L, 6L, 2L, 4L, List.of(1L, 2L, 3L));     // Orelha Anã Larga
            createPlayerTipoBaseOrelha(PlayerRaca.ANAO, 5L, 2L, 4L, 6L, 8L, 2L, 3L, List.of(1L, 2L, 3L));     // Orelha Anã Grande (raro)
            createPlayerTipoBaseOrelha(PlayerRaca.ANAO, 3L, 3L, 4L, 3L, 5L, 3L, 4L, List.of(1L, 2L, 3L));     // Orelha Anã Grossa
            createPlayerTipoBaseOrelha(PlayerRaca.ANAO, 2L, 2L, 3L, 5L, 7L, 2L, 3L, List.of(1L, 2L, 3L));     // Orelha Anã Alongada (muito raro)

            // HUMANO - Orelhas médias e variadas (12 variações)
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 12L, 2L, 3L, 5L, 6L, 2L, 3L, List.of(1L, 2L, 3L));  // Orelha Humana Muito Pequena
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 15L, 3L, 4L, 5L, 7L, 2L, 3L, List.of(1L, 2L, 3L));  // Orelha Humana Pequena
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 20L, 3L, 5L, 6L, 8L, 2L, 4L, List.of(1L, 2L, 3L));  // Orelha Humana Normal
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 18L, 3L, 4L, 6L, 8L, 2L, 3L, List.of(1L, 2L, 3L));  // Orelha Humana Média
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 15L, 4L, 5L, 6L, 8L, 2L, 4L, List.of(1L, 2L, 3L));  // Orelha Humana Proporcional
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 10L, 4L, 6L, 6L, 9L, 3L, 4L, List.of(1L, 2L, 3L));  // Orelha Humana Grande
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 8L, 3L, 5L, 5L, 7L, 2L, 3L, List.of(1L, 2L, 3L));   // Orelha Humana Colada
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 6L, 3L, 5L, 7L, 9L, 2L, 4L, List.of(1L, 2L, 3L));   // Orelha Humana Afastada
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 4L, 4L, 6L, 7L, 10L, 3L, 5L, List.of(1L, 2L, 3L));  // Orelha Humana Muito Grande
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 3L, 2L, 4L, 6L, 8L, 2L, 3L, List.of(1L, 2L, 3L));   // Orelha Humana Alongada
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 2L, 4L, 5L, 5L, 7L, 3L, 5L, List.of(1L, 2L, 3L));   // Orelha Humana Larga (raro)
            createPlayerTipoBaseOrelha(PlayerRaca.HUMANO, 1L, 3L, 6L, 8L, 11L, 3L, 5L, List.of(1L, 2L, 3L));  // Orelha Humana Proeminente (muito raro)

            // ORC - Orelhas grandes, pontiagudas e grossas (10 variações)
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 15L, 5L, 7L, 7L, 10L, 3L, 5L, List.of(2L, 3L));        // Orelha Orc Normal
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 20L, 6L, 8L, 8L, 12L, 4L, 6L, List.of(2L, 3L));        // Orelha Orc Grande
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 18L, 7L, 9L, 9L, 13L, 4L, 6L, List.of(2L, 3L));        // Orelha Orc Gigante
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 15L, 5L, 8L, 6L, 9L, 3L, 5L, List.of(2L, 3L));         // Orelha Orc Larga
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 12L, 6L, 9L, 10L, 14L, 4L, 7L, List.of(2L, 3L));       // Orelha Orc Pontiaguda
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 10L, 7L, 10L, 10L, 15L, 5L, 7L, List.of(2L, 3L));      // Orelha Orc Muito Pontiaguda
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 6L, 8L, 10L, 11L, 16L, 5L, 8L, List.of(2L, 3L));       // Orelha Orc Colossal
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 4L, 5L, 8L, 8L, 12L, 5L, 7L, List.of(2L, 3L));         // Orelha Orc Grossa
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 3L, 6L, 9L, 7L, 11L, 4L, 6L, List.of(2L, 3L));         // Orelha Orc Baixa
            createPlayerTipoBaseOrelha(PlayerRaca.ORC, 2L, 9L, 11L, 12L, 17L, 6L, 9L, List.of(2L, 3L));       // Orelha Orc Titânica (raro)

            // ELFO - Orelhas longas e pontiagudas (12 variações)
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 10L, 4L, 6L, 9L, 13L, 2L, 3L, List.of(1L, 2L, 4L, 5L));   // Orelha Élfica Curta
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 15L, 4L, 6L, 10L, 14L, 2L, 3L, List.of(1L, 2L, 4L, 5L));  // Orelha Élfica Alongada
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 18L, 5L, 7L, 12L, 16L, 2L, 3L, List.of(1L, 2L, 4L, 5L));  // Orelha Élfica Longa
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 20L, 5L, 7L, 11L, 15L, 2L, 3L, List.of(1L, 2L, 4L, 5L));  // Orelha Élfica Elegante
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 15L, 5L, 8L, 14L, 18L, 2L, 3L, List.of(1L, 2L, 4L, 5L));  // Orelha Élfica Muito Longa
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 12L, 6L, 8L, 16L, 20L, 2L, 3L, List.of(1L, 2L, 4L, 5L));  // Orelha Élfica Majestosa
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 8L, 6L, 8L, 15L, 19L, 2L, 3L, List.of(1L, 2L, 4L, 5L));   // Orelha Élfica Nobre
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 6L, 5L, 9L, 17L, 21L, 2L, 3L, List.of(1L, 2L, 4L, 5L));   // Orelha Élfica Ancestral
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 4L, 6L, 9L, 18L, 22L, 2L, 3L, List.of(1L, 2L, 4L, 5L));   // Orelha Élfica Suprema
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 3L, 4L, 7L, 13L, 17L, 2L, 4L, List.of(1L, 2L, 4L, 5L));   // Orelha Élfica Larga
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 2L, 7L, 9L, 19L, 23L, 2L, 3L, List.of(1L, 2L, 4L, 5L));   // Orelha Élfica Real (raro)
            createPlayerTipoBaseOrelha(PlayerRaca.ELFO, 1L, 5L, 8L, 20L, 25L, 2L, 3L, List.of(1L, 2L, 4L, 5L));   // Orelha Élfica Lendária (muito raro)

            // GOBLIN - Orelhas pontiagudas e médias (9 variações)
            createPlayerTipoBaseOrelha(PlayerRaca.GOBLIN, 15L, 3L, 4L, 4L, 6L, 2L, 3L, List.of(2L, 3L));        // Orelha Goblin Pequena
            createPlayerTipoBaseOrelha(PlayerRaca.GOBLIN, 20L, 3L, 5L, 5L, 7L, 2L, 3L, List.of(2L, 3L));        // Orelha Goblin Normal
            createPlayerTipoBaseOrelha(PlayerRaca.GOBLIN, 20L, 4L, 5L, 6L, 8L, 2L, 3L, List.of(2L, 3L));        // Orelha Goblin Média
            createPlayerTipoBaseOrelha(PlayerRaca.GOBLIN, 18L, 4L, 6L, 7L, 9L, 2L, 4L, List.of(2L, 3L));        // Orelha Goblin Pontiaguda
            createPlayerTipoBaseOrelha(PlayerRaca.GOBLIN, 12L, 4L, 6L, 8L, 10L, 3L, 4L, List.of(2L, 3L));       // Orelha Goblin Grande
            createPlayerTipoBaseOrelha(PlayerRaca.GOBLIN, 8L, 3L, 5L, 6L, 9L, 2L, 4L, List.of(2L, 3L));         // Orelha Goblin Larga
            createPlayerTipoBaseOrelha(PlayerRaca.GOBLIN, 6L, 5L, 7L, 7L, 10L, 3L, 5L, List.of(2L, 3L));        // Orelha Goblin Destacada
            createPlayerTipoBaseOrelha(PlayerRaca.GOBLIN, 4L, 3L, 5L, 5L, 8L, 3L, 4L, List.of(2L, 3L));         // Orelha Goblin Grossa
            createPlayerTipoBaseOrelha(PlayerRaca.GOBLIN, 2L, 5L, 7L, 9L, 12L, 3L, 5L, List.of(2L, 3L));        // Orelha Goblin Muito Pontiaguda (raro)

            // GIGANTE - Orelhas grandes e grossas (10 variações)
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 12L, 6L, 8L, 8L, 11L, 4L, 6L, List.of(1L, 2L, 3L));   // Orelha Gigante Normal
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 15L, 7L, 9L, 9L, 12L, 4L, 6L, List.of(1L, 2L, 3L));   // Orelha Gigante Grande
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 18L, 8L, 10L, 10L, 13L, 5L, 7L, List.of(1L, 2L, 3L)); // Orelha Gigante Larga
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 20L, 7L, 10L, 11L, 14L, 5L, 8L, List.of(1L, 2L, 3L)); // Orelha Gigante Robusta
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 15L, 8L, 11L, 10L, 14L, 6L, 8L, List.of(1L, 2L, 3L)); // Orelha Gigante Grossa
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 12L, 9L, 12L, 12L, 16L, 6L, 9L, List.of(1L, 2L, 3L)); // Orelha Gigante Colossal
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 8L, 7L, 10L, 9L, 13L, 5L, 7L, List.of(1L, 2L, 3L));   // Orelha Gigante Média
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 6L, 6L, 9L, 12L, 15L, 5L, 8L, List.of(1L, 2L, 3L));   // Orelha Gigante Baixa
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 4L, 10L, 13L, 13L, 17L, 7L, 10L, List.of(1L, 2L, 3L));// Orelha Gigante Titânica (raro)
            createPlayerTipoBaseOrelha(PlayerRaca.GIGANTE, 2L, 8L, 11L, 14L, 18L, 6L, 9L, List.of(1L, 2L, 3L));  // Orelha Gigante Ancestral (muito raro)

            System.out.println("✓ PlayerTipoBaseOrelha criados (62 registros)");
        } catch (Exception e) {
            System.err.println("Erro ao criar PlayerTipoBaseOrelha: " + e.getMessage());
        }
    }

    private void createPlayerTipoBaseOrelha(PlayerRaca raca, Long possibilidade,
            Long xMin, Long xMax, Long yMin, Long yMax, Long zMin, Long zMax,
            List<Long> tipoOrelhaColorMaterialIds) {
        PlayerTipoBaseOrelha entity = new PlayerTipoBaseOrelha();
        entity.setPlayerRaca(raca);
        entity.setPossibilidade(possibilidade);
        entity.setEixoXMinimo(xMin);
        entity.setEixoXMaximo(xMax);
        entity.setEixoYMinimo(yMin);
        entity.setEixoYMaximo(yMax);
        entity.setEixoZMinimo(zMin);
        entity.setEixoZMaximo(zMax);
        entity.setAtivo(true);
        entity.setDataInclusao(LocalDateTime.now());
        
        // Buscar e adicionar as cores possíveis
        List<TipoOrelhaColorMaterial> cores = tipoOrelhaColorMaterialIds.stream()
            .map(id -> tipoOrelhaColorMaterialRepository.findById(id))
            .filter(c -> c != null)
            .toList();
        entity.setPossiveisColorMaterials(cores);
        
        playerTipoBaseOrelhaRepository.persist(entity);
    }

    private void seedPlayerTipoCabelo() {
        try {
            // ANÃO - Cabelos robustos e volumosos (10 variações)
            createPlayerTipoCabelo("Careca com Barba Curta", "/hair/dwarf/bald_short_beard.obj", 15L, PlayerRaca.ANAO);
            createPlayerTipoCabelo("Barba Curta Aparada", "/hair/dwarf/short_trimmed_beard.obj", 18L, PlayerRaca.ANAO);
            createPlayerTipoCabelo("Cabelo Curto com Barba", "/hair/dwarf/short_with_beard.obj", 20L, PlayerRaca.ANAO);
            createPlayerTipoCabelo("Barba Longa Anã", "/hair/dwarf/long_beard.obj", 22L, PlayerRaca.ANAO);
            createPlayerTipoCabelo("Barba Trançada", "/hair/dwarf/braided_beard.obj", 18L, PlayerRaca.ANAO);
            createPlayerTipoCabelo("Barba Dupla Trançada", "/hair/dwarf/double_braided_beard.obj", 12L, PlayerRaca.ANAO);
            createPlayerTipoCabelo("Barba com Anéis", "/hair/dwarf/ringed_beard.obj", 10L, PlayerRaca.ANAO);
            createPlayerTipoCabelo("Barba de Batalha", "/hair/dwarf/war_beard.obj", 8L, PlayerRaca.ANAO);
            createPlayerTipoCabelo("Cabelo Longo com Barba Longa", "/hair/dwarf/long_hair_long_beard.obj", 5L, PlayerRaca.ANAO);
            createPlayerTipoCabelo("Barba Real Anã", "/hair/dwarf/royal_dwarf_beard.obj", 2L, PlayerRaca.ANAO);

            // HUMANO - Cabelos variados e versáteis (14 variações)
            createPlayerTipoCabelo("Careca", "/hair/human/bald.obj", 8L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo Muito Curto", "/hair/human/very_short.obj", 12L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo Curto", "/hair/human/short_hair.obj", 18L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo Curto Espetado", "/hair/human/short_spiked.obj", 10L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo Médio", "/hair/human/medium_hair.obj", 20L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo Médio Ondulado", "/hair/human/medium_wavy.obj", 15L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo Longo", "/hair/human/long_hair.obj", 12L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo Longo Liso", "/hair/human/long_straight.obj", 10L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Rabo de Cavalo", "/hair/human/ponytail.obj", 8L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Trança Simples", "/hair/human/single_braid.obj", 6L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Coque", "/hair/human/bun.obj", 5L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo Encaracolado", "/hair/human/curly_hair.obj", 4L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo Afro", "/hair/human/afro.obj", 3L, PlayerRaca.HUMANO);
            createPlayerTipoCabelo("Cabelo com Dreadlocks", "/hair/human/dreadlocks.obj", 2L, PlayerRaca.HUMANO);

            // ORC - Cabelos selvagens e guerreiros (12 variações)
            createPlayerTipoCabelo("Careca Orc", "/hair/orc/bald_orc.obj", 12L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Cabelo Muito Curto", "/hair/orc/very_short.obj", 15L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Moicano Orc", "/hair/orc/mohawk.obj", 20L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Moicano Duplo", "/hair/orc/double_mohawk.obj", 12L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Cabelo Espetado", "/hair/orc/spiked_hair.obj", 18L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Cabelo Espetado Alto", "/hair/orc/tall_spikes.obj", 10L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Crinas Curtas", "/hair/orc/short_mane.obj", 15L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Crinas Longas", "/hair/orc/long_mane.obj", 12L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Cabelo de Guerra", "/hair/orc/war_hair.obj", 8L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Topete Orc", "/hair/orc/orc_topknot.obj", 6L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Tranças de Batalha", "/hair/orc/battle_braids.obj", 4L, PlayerRaca.ORC);
            createPlayerTipoCabelo("Cabelo de Chefe Orc", "/hair/orc/warchief_hair.obj", 2L, PlayerRaca.ORC);

            // ELFO - Cabelos elegantes e fluidos (14 variações)
            createPlayerTipoCabelo("Cabelo Curto Élfico", "/hair/elf/short_elven.obj", 8L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo Médio Liso", "/hair/elf/medium_straight.obj", 12L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo Liso Longo", "/hair/elf/straight_long.obj", 18L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo Ondulado", "/hair/elf/wavy_hair.obj", 20L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo Ondulado Longo", "/hair/elf/long_wavy.obj", 15L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Tranças Élficas", "/hair/elf/elven_braids.obj", 12L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Tranças Laterais", "/hair/elf/side_braids.obj", 10L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Meia Trança", "/hair/elf/half_braid.obj", 8L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo com Tiara", "/hair/elf/hair_with_circlet.obj", 6L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo Real Élfico", "/hair/elf/royal_elven_hair.obj", 5L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo de Arqueiro", "/hair/elf/archer_hair.obj", 4L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo de Mago Élfico", "/hair/elf/elven_mage_hair.obj", 3L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo Ancião Élfico", "/hair/elf/ancient_elven_hair.obj", 2L, PlayerRaca.ELFO);
            createPlayerTipoCabelo("Cabelo Supremo Élfico", "/hair/elf/supreme_elven_hair.obj", 1L, PlayerRaca.ELFO);

            // GOBLIN - Cabelos desgrenhados e caóticos (10 variações)
            createPlayerTipoCabelo("Careca Goblin", "/hair/goblin/bald_goblin.obj", 15L, PlayerRaca.GOBLIN);
            createPlayerTipoCabelo("Cabelo Espetado Irregular", "/hair/goblin/irregular_spikes.obj", 18L, PlayerRaca.GOBLIN);
            createPlayerTipoCabelo("Cabelo Desgrenhado", "/hair/goblin/messy_hair.obj", 20L, PlayerRaca.GOBLIN);
            createPlayerTipoCabelo("Topete Goblin", "/hair/goblin/goblin_tuft.obj", 18L, PlayerRaca.GOBLIN);
            createPlayerTipoCabelo("Cabelo Selvagem", "/hair/goblin/wild_hair.obj", 15L, PlayerRaca.GOBLIN);
            createPlayerTipoCabelo("Moicano Irregular", "/hair/goblin/ragged_mohawk.obj", 12L, PlayerRaca.GOBLIN);
            createPlayerTipoCabelo("Cabelo em Tufos", "/hair/goblin/patchy_tufts.obj", 10L, PlayerRaca.GOBLIN);
            createPlayerTipoCabelo("Crinas Despenteadas", "/hair/goblin/unkempt_mane.obj", 8L, PlayerRaca.GOBLIN);
            createPlayerTipoCabelo("Cabelo de Xamã Goblin", "/hair/goblin/shaman_hair.obj", 4L, PlayerRaca.GOBLIN);
            createPlayerTipoCabelo("Cabelo de Chefe Goblin", "/hair/goblin/goblin_chief_hair.obj", 2L, PlayerRaca.GOBLIN);

            // GIGANTE - Cabelos grossos e volumosos (12 variações)
            createPlayerTipoCabelo("Careca Gigante", "/hair/giant/bald_giant.obj", 10L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo Muito Curto e Grosso", "/hair/giant/very_short_thick.obj", 12L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo Volumoso Curto", "/hair/giant/short_voluminous.obj", 15L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo Robusto", "/hair/giant/robust_hair.obj", 18L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo Longo e Grosso", "/hair/giant/long_thick.obj", 20L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo Trançado Gigante", "/hair/giant/giant_braids.obj", 15L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo com Barba Completa", "/hair/giant/full_beard_hair.obj", 12L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Crinas de Montanha", "/hair/giant/mountain_mane.obj", 10L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo de Guerreiro Gigante", "/hair/giant/giant_warrior_hair.obj", 8L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo de Titã", "/hair/giant/titan_hair.obj", 6L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo Ancestral Gigante", "/hair/giant/ancient_giant_hair.obj", 4L, PlayerRaca.GIGANTE);
            createPlayerTipoCabelo("Cabelo Lendário Gigante", "/hair/giant/legendary_giant_hair.obj", 2L, PlayerRaca.GIGANTE);

            System.out.println("✓ PlayerTipoCabelo criados (72 registros)");
        } catch (Exception e) {
            System.err.println("Erro ao criar PlayerTipoCabelo: " + e.getMessage());
        }
    }

    private void createPlayerTipoCabelo(String nome, String path, Long possibilidade, PlayerRaca raca) {
        PlayerTipoCabelo entity = new PlayerTipoCabelo();
        entity.setNome(nome);
        entity.setPath(path);
        entity.setPossibilidade(possibilidade);
        entity.setPlayerRaca(raca);
        entity.setAtivo(true);
        entity.setDataInclusao(LocalDateTime.now());
        playerTipoCabeloRepository.persist(entity);
    }

    private void seedBaseContainers() {
        try {
            // Buscar material e cor padrão (assumindo que já existem no banco)
            Material materialMadeira = materialRepository.findById(1L);
            if (materialMadeira == null) {
                System.err.println("⚠ Material ID 1 não encontrado, criando material básico");
                materialMadeira = new Material();
                materialMadeira.setName("Madeira Básica");
                materialMadeira.setResistenciaFogo(50L);
                materialMadeira.setResistenciaAgua(30L);
                materialMadeira.setResistenciaImpacto(70L);
                materialMadeira.setDataInclusao(LocalDateTime.now());
                materialRepository.persist(materialMadeira);
            }

            ColorMaterial corMarrom = colorMaterialRepository.findById(1L);
            if (corMarrom == null) {
                System.err.println("⚠ ColorMaterial ID 1 não encontrado, pulando seed de containers");
                return;
            }

            // INVENTARIO (Mochila de costas - maior em altura)
            createBaseContainer(
                "Mochila Básica",
                "Mochila simples de inventário pessoal",
                ContainerTipo.INVENTARIO,
                300L, 500L, 200L,  // 30cm largura x 50cm altura x 20cm profundidade
                50L,  // possibilidade
                1000000L,  // 1m³ capacidade (1000L)
                50000L,    // 50kg peso máximo
                materialMadeira,
                corMarrom
            );

            createBaseContainer(
                "Mochila Reforçada",
                "Mochila maior com mais espaço",
                ContainerTipo.INVENTARIO,
                400L, 600L, 250L,  // 40cm largura x 60cm altura x 25cm profundidade
                30L,  // possibilidade
                2000000L,  // 2m³ capacidade
                75000L,    // 75kg peso máximo
                materialMadeira,
                corMarrom
            );

            // BAU (Caixa - maior em largura)
            createBaseContainer(
                "Baú Pequeno",
                "Baú de madeira simples",
                ContainerTipo.BAU,
                800L, 400L, 500L,  // 80cm largura x 40cm altura x 50cm profundidade
                50L,  // possibilidade
                3000000L,  // 3m³ capacidade
                150000L,   // 150kg peso máximo
                materialMadeira,
                corMarrom
            );

            createBaseContainer(
                "Baú Grande",
                "Baú robusto para armazenamento",
                ContainerTipo.BAU,
                1200L, 600L, 700L,  // 120cm largura x 60cm altura x 70cm profundidade
                30L,  // possibilidade
                6000000L,  // 6m³ capacidade
                300000L,   // 300kg peso máximo
                materialMadeira,
                corMarrom
            );

            // MOCHILA (Mochila portátil - balanceada)
            createBaseContainer(
                "Mochila de Viagem",
                "Mochila leve para aventuras",
                ContainerTipo.MOCHILA,
                350L, 450L, 200L,  // 35cm largura x 45cm altura x 20cm profundidade
                50L,  // possibilidade
                1500000L,  // 1.5m³ capacidade
                60000L,    // 60kg peso máximo
                materialMadeira,
                corMarrom
            );

            createBaseContainer(
                "Mochila de Expedição",
                "Mochila grande para longas jornadas",
                ContainerTipo.MOCHILA,
                450L, 550L, 300L,  // 45cm largura x 55cm altura x 30cm profundidade
                30L,  // possibilidade
                2500000L,  // 2.5m³ capacidade
                100000L,   // 100kg peso máximo
                materialMadeira,
                corMarrom
            );

            System.out.println("✓ BaseContainerStructureUnit criados (6 registros)");
        } catch (Exception e) {
            System.err.println("Erro ao criar BaseContainers: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createBaseContainer(
        String nome,
        String descricao,
        ContainerTipo tipo,
        Long largura, Long altura, Long profundidade,
        Long possibilidade,
        Long capacidadeMaxima,
        Long pesoMaximo,
        Material material,
        ColorMaterial color
    ) {
        // Criar StructureUnitPart (corpo do container)
        StructureUnitPart part = new StructureUnitPart();
        part.setNome(nome + " - Corpo");
        part.setInicioX(0L);
        part.setInicioY(0L);
        part.setInicioZ(0L);
        part.setFimX(largura);
        part.setFimY(altura);
        part.setFimZ(profundidade);
        part.setMaterial(material);
        part.setColor(color);
        part.setAreaContato(true);
        part.setDataInclusao(LocalDateTime.now());
        structureUnitPartRepository.persist(part);

        // Criar StructureUnit
        StructureUnit unit = new StructureUnit();
        unit.setNome(nome);
        unit.setParts(List.of(part));
        unit.setLayer(Layer.INTERIOR);
        unit.setTipo(StructureUnitTipo.PADRAO);
        unit.setDataInclusao(LocalDateTime.now());
        structureUnitRepository.persist(unit);

        // Criar BaseContainerStructureUnit
        BaseContainerStructureUnit base = new BaseContainerStructureUnit();
        base.setNome(nome);
        base.setDescricao(descricao);
        base.setContainerTipo(tipo);
        base.setStructureUnit(unit);
        base.setCapacidadeMaximaEspacoLimite(capacidadeMaxima);
        base.setPesoMaximoLimite(pesoMaximo);
        base.setPossibilidade(possibilidade);
        base.setAtivo(true);
        base.setDataInclusao(LocalDateTime.now());
        baseContainerStructureUnitRepository.persist(base);
    }

    private void seedItemTipos() {
        try {
            // ARMAS CORPO A CORPO (1-10)
            createItemTipo("Espada de Ferro", "Espada básica forjada em ferro", 1L, 150L);
            createItemTipo("Espada de Aço", "Espada resistente de aço", 1L, 180L);
            createItemTipo("Espada Longa", "Espada de duas mãos com grande alcance", 1L, 250L);
            createItemTipo("Adaga", "Lâmina curta e ágil", 1L, 50L);
            createItemTipo("Machado de Batalha", "Machado pesado de guerra", 1L, 200L);
            createItemTipo("Machado de Madeira", "Machado simples com cabo de madeira", 1L, 120L);
            createItemTipo("Lança", "Arma de haste com ponta afiada", 1L, 180L);
            createItemTipo("Martelo de Guerra", "Martelo maciço para combate", 1L, 300L);
            createItemTipo("Foice", "Lâmina curva montada em cabo longo", 1L, 140L);
            createItemTipo("Katana", "Espada curva oriental afiada", 1L, 160L);

            // ARMAS À DISTÂNCIA (11-15)
            createItemTipo("Arco Curto", "Arco leve e rápido", 1L, 60L);
            createItemTipo("Arco Longo", "Arco de madeira para longa distância", 1L, 80L);
            createItemTipo("Besta", "Arco mecânico potente", 1L, 150L);
            createItemTipo("Zarabatana", "Tubo para disparar dardos", 1L, 30L);
            createItemTipo("Shuriken", "Estrela de arremesso ninja", 50L, 5L);

            // ESCUDOS (16-20)
            createItemTipo("Escudo de Madeira", "Escudo básico de madeira reforçada", 1L, 200L);
            createItemTipo("Escudo de Ferro", "Escudo robusto de ferro", 1L, 300L);
            createItemTipo("Escudo Grande", "Escudo de corpo inteiro", 1L, 400L);
            createItemTipo("Escudo Redondo", "Escudo circular ágil", 1L, 150L);
            createItemTipo("Tarja", "Pequeno escudo de braço", 1L, 100L);

            // ARMADURAS CABEÇA (21-25)
            createItemTipo("Elmo de Ferro", "Capacete básico de ferro", 1L, 100L);
            createItemTipo("Elmo de Aço", "Capacete reforçado de aço", 1L, 120L);
            createItemTipo("Capuz de Couro", "Proteção leve para a cabeça", 1L, 40L);
            createItemTipo("Coroa de Ouro", "Ornamento régio dourado", 1L, 200L);
            createItemTipo("Máscara Ninja", "Máscara furtiva preta", 1L, 20L);

            // ARMADURAS CORPO (26-30)
            createItemTipo("Armadura de Couro", "Armadura leve de couro", 1L, 250L);
            createItemTipo("Armadura de Ferro", "Armadura pesada de ferro", 1L, 500L);
            createItemTipo("Cota de Malha", "Armadura de anéis entrelaçados", 1L, 350L);
            createItemTipo("Armadura de Aço", "Armadura completa de aço", 1L, 600L);
            createItemTipo("Túnica de Mago", "Veste mágica encantada", 1L, 80L);

            // ARMADURAS PERNAS (31-35)
            createItemTipo("Calças de Couro", "Proteção leve para pernas", 1L, 100L);
            createItemTipo("Perneiras de Ferro", "Proteção pesada de ferro", 1L, 200L);
            createItemTipo("Calças de Tecido", "Calças simples de tecido", 1L, 30L);
            createItemTipo("Botas de Couro", "Botas resistentes de couro", 1L, 80L);
            createItemTipo("Botas de Ferro", "Botas pesadas blindadas", 1L, 150L);

            // ACESSÓRIOS (36-40)
            createItemTipo("Anel de Ouro", "Anel valioso dourado", 10L, 2L);
            createItemTipo("Colar de Prata", "Colar elegante prateado", 10L, 5L);
            createItemTipo("Amuleto Mágico", "Talismã com poder arcano", 1L, 3L);
            createItemTipo("Cinto de Couro", "Cinto resistente com fivela", 1L, 20L);
            createItemTipo("Mochila", "Bolsa para carregar itens", 1L, 50L);

            // CONSUMÍVEIS (41-50)
            createItemTipo("Poção de Vida Pequena", "Restaura 50 HP", 99L, 5L);
            createItemTipo("Poção de Vida", "Restaura 150 HP", 99L, 8L);
            createItemTipo("Poção de Vida Grande", "Restaura 300 HP", 99L, 12L);
            createItemTipo("Poção de Mana Pequena", "Restaura 30 MP", 99L, 5L);
            createItemTipo("Poção de Mana", "Restaura 100 MP", 99L, 8L);
            createItemTipo("Poção de Mana Grande", "Restaura 200 MP", 99L, 12L);
            createItemTipo("Poção de Energia", "Restaura stamina", 99L, 6L);
            createItemTipo("Antídoto", "Cura envenenamento", 99L, 4L);
            createItemTipo("Pão", "Alimento básico nutritivo", 99L, 2L);
            createItemTipo("Carne Assada", "Carne cozida restauradora", 99L, 4L);

            // MATERIAIS DE CRAFT (51-65)
            createItemTipo("Madeira", "Tábuas de madeira para construção", 999L, 10L);
            createItemTipo("Tora de Madeira", "Madeira bruta cortada", 999L, 15L);
            createItemTipo("Minério de Ferro", "Minério bruto de ferro", 999L, 20L);
            createItemTipo("Barra de Ferro", "Ferro processado", 999L, 25L);
            createItemTipo("Minério de Ouro", "Minério bruto de ouro", 999L, 18L);
            createItemTipo("Barra de Ouro", "Ouro puro refinado", 999L, 22L);
            createItemTipo("Carvão", "Combustível mineral", 999L, 8L);
            createItemTipo("Pedra", "Blocos de pedra", 999L, 30L);
            createItemTipo("Areia", "Areia fina para construção", 999L, 5L);
            createItemTipo("Argila", "Barro moldável", 999L, 12L);
            createItemTipo("Couro", "Pele curtida de animal", 999L, 6L);
            createItemTipo("Tecido", "Tecido básico de algodão", 999L, 3L);
            createItemTipo("Corda", "Corda resistente de fibra", 999L, 3L);
            createItemTipo("Prego", "Prego de metal", 999L, 1L);
            createItemTipo("Cola", "Substância adesiva", 999L, 2L);

            // GEMAS E TESOUROS (66-75)
            createItemTipo("Pedra Preciosa", "Gema valiosa para comércio", 999L, 1L);
            createItemTipo("Rubi", "Gema vermelha rara", 99L, 1L);
            createItemTipo("Safira", "Gema azul valiosa", 99L, 1L);
            createItemTipo("Esmeralda", "Gema verde reluzente", 99L, 1L);
            createItemTipo("Diamante", "Gema transparente raríssima", 50L, 1L);
            createItemTipo("Pérola", "Gema oceânica iridescente", 99L, 1L);
            createItemTipo("Moeda de Ouro", "Moeda valiosa", 9999L, 0L);
            createItemTipo("Moeda de Prata", "Moeda comum", 9999L, 0L);
            createItemTipo("Relíquia Antiga", "Artefato histórico valioso", 1L, 10L);
            createItemTipo("Cristal Mágico", "Cristal com energia arcana", 50L, 2L);

            // FERRAMENTAS (76-80)
            createItemTipo("Picareta de Ferro", "Ferramenta para minerar", 1L, 180L);
            createItemTipo("Machado de Lenhador", "Ferramenta para cortar árvores", 1L, 160L);
            createItemTipo("Pá", "Ferramenta para cavar", 1L, 120L);
            createItemTipo("Foice de Colheita", "Ferramenta agrícola", 1L, 100L);
            createItemTipo("Anzol", "Ferramenta de pesca", 10L, 5L);

            System.out.println("✓ ItemTipos criados (80 registros)");
        } catch (Exception e) {
            System.err.println("Erro ao criar ItemTipos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createItemTipo(String nome, String descricao, Long limiteQuantidade, Long pesoPorQuantidade) {
        org.cupula.model.items.ItemTipo entity = new org.cupula.model.items.ItemTipo();
        entity.setNome(nome);
        entity.setDescricao(descricao);
        entity.setLimiteQuantidade(limiteQuantidade);
        entity.setPesoPorQuantidade(pesoPorQuantidade);
        entity.setAtivo(true);
        entity.setDataInclusao(LocalDateTime.now());
        itemTipoRepository.persist(entity);
    }

    private void seedBaseStructures() {
        try {
            // Buscar material e cor padrão
            Material materialMadeira = materialRepository.findById(1L);
            ColorMaterial corMarrom = colorMaterialRepository.findById(1L);
            
            if (materialMadeira == null || corMarrom == null) {
                System.err.println("⚠ Material ou ColorMaterial não encontrado, pulando seed de BaseStructures");
                return;
            }

            // HUMANO - Casa simples de madeira
            createBaseStructureHomePlayer(
                PlayerRaca.HUMANO,
                "Casa Humana Simples",
                "Casa de madeira básica para humanos",
                100L,
                materialMadeira, corMarrom,
                5000L, 3000L, 2500L  // 5m largura x 3m altura x 2.5m profundidade
            );

            // ELFO - Casa Élfica elegante
            createBaseStructureHomePlayer(
                PlayerRaca.ELFO,
                "Casa Élfica",
                "Habitação élfica elegante em harmonia com a natureza",
                100L,
                materialMadeira, corMarrom,
                4500L, 3500L, 2500L  // Casa mais alta e estreita
            );

            // ANAO - Casa Anã robusta
            createBaseStructureHomePlayer(
                PlayerRaca.ANAO,
                "Casa Anã",
                "Habitação anã robusta e compacta",
                100L,
                materialMadeira, corMarrom,
                4000L, 2500L, 3000L  // Casa mais baixa e larga
            );

            // ORC - Cabana Orc
            createBaseStructureHomePlayer(
                PlayerRaca.ORC,
                "Cabana Orc",
                "Habitação orc simples e funcional",
                100L,
                materialMadeira, corMarrom,
                6000L, 3500L, 3000L  // Casa grande e espaçosa
            );

            // GOBLIN - Toca Goblin
            createBaseStructureHomePlayer(
                PlayerRaca.GOBLIN,
                "Toca Goblin",
                "Pequena toca adaptada para goblins",
                100L,
                materialMadeira, corMarrom,
                3000L, 2000L, 2000L  // Casa pequena
            );

            // GIGANTE - Salão Gigante
            createBaseStructureHomePlayer(
                PlayerRaca.GIGANTE,
                "Salão Gigante",
                "Grande salão para acomodar gigantes",
                100L,
                materialMadeira, corMarrom,
                8000L, 5000L, 4000L  // Casa enorme
            );

            System.out.println("✓ BaseStructures HOME_PLAYER criadas (6 registros)");
        } catch (Exception e) {
            System.err.println("Erro ao criar BaseStructures: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createBaseStructureHomePlayer(
        PlayerRaca raca,
        String nome,
        String descricao,
        Long possibilidade,
        Material material,
        ColorMaterial color,
        Long largura,
        Long altura,
        Long profundidade
    ) {
        // Criar StructureUnitPart (paredes da casa)
        StructureUnitPart part = new StructureUnitPart();
        part.setNome(nome + " - Estrutura");
        part.setInicioX(0L);
        part.setInicioY(0L);
        part.setInicioZ(0L);
        part.setFimX(largura);
        part.setFimY(altura);
        part.setFimZ(profundidade);
        part.setMaterial(material);
        part.setColor(color);
        part.setAreaContato(true);
        part.setDataInclusao(LocalDateTime.now());
        structureUnitPartRepository.persist(part);

        // Criar StructureUnit
        StructureUnit unit = new StructureUnit();
        unit.setNome(nome + " Unit");
        unit.setParts(List.of(part));
        unit.setLayer(Layer.EXTERIOR);
        unit.setTipo(StructureUnitTipo.PADRAO);
        unit.setDataInclusao(LocalDateTime.now());
        structureUnitRepository.persist(unit);

        // Criar Structure Template
        org.cupula.model.structures.Structure structureTemplate = new org.cupula.model.structures.Structure();
        structureTemplate.setNome(nome + " Template");
        structureTemplate.setStructureUnits(List.of(unit));
        structureTemplate.setTipo(StructureTipo.HOME_PLAYER);
        structureTemplate.setDataInclusao(LocalDateTime.now());
        structureRepository.persist(structureTemplate);

        // Criar BaseStructure
        BaseStructure base = new BaseStructure();
        base.setNome(nome);
        base.setDescricao(descricao);
        base.setStructureTipo(StructureTipo.HOME_PLAYER);
        base.setPlayerRaca(raca);
        base.setStructureTemplate(structureTemplate);
        base.setPossibilidade(possibilidade);
        base.setAtivo(true);
        base.setDataInclusao(LocalDateTime.now());
        baseStructureRepository.persist(base);
    }
}
