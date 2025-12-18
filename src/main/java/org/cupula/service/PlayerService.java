package org.cupula.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.cupula.dto.player.request.CriarPlayerRequest;
import org.cupula.dto.player.response.PlayerResponse;
import org.cupula.model.auth.Usuario;
import org.cupula.model.comunity.VisibilidadePerfil;
import org.cupula.model.entities.baseview.PlayerTipoCabelo;
import org.cupula.model.entities.baseview.PlayerTipoBaseTamanho;
import org.cupula.model.entities.baseview.orelha.PlayerTipoBaseOrelha;
import org.cupula.model.entities.baseview.orelha.TipoOrelhaColorMaterial;
import org.cupula.model.entities.enums.PlayerRaca;
import org.cupula.model.entities.player.Player;
import org.cupula.model.entities.player.PlayerPosicao;
import org.cupula.model.entities.player.PlayerStatus;
import org.cupula.model.structures.view.ColorMaterial;
import org.cupula.repository.entities.baseview.PlayerTipoCabeloRepository;
import org.cupula.repository.entities.baseview.PlayerTipoBaseTamanhoRepository;
import org.cupula.repository.entities.baseview.orelha.PlayerTipoBaseOrelhaRepository;
import org.cupula.repository.player.PlayerPosicaoRepository;
import org.cupula.repository.player.PlayerRepository;
import org.cupula.repository.player.PlayerStatusRepository;
import org.cupula.repository.structures.view.ColorMaterialRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlayerService {

    @Inject
    PlayerRepository playerRepository;

    @Inject
    PlayerStatusRepository playerStatusRepository;

    @Inject
    PlayerPosicaoRepository playerPosicaoRepository;

    @Inject
    PlayerTipoCabeloRepository playerTipoCabeloRepository;

    @Inject
    ColorMaterialRepository colorMaterialRepository;

    @Inject
    PlayerTipoBaseTamanhoRepository playerTipoBaseTamanhoRepository;

    @Inject
    PlayerTipoBaseOrelhaRepository playerTipoBaseOrelhaRepository;

    private final Random random = new Random();

    /**
     * Sorteia uma raça aleatória
     */
    private PlayerRaca sortearRaca() {
        PlayerRaca[] racas = PlayerRaca.values();
        return racas[random.nextInt(racas.length)];
    }

    /**
     * Sorteia uma cor aleatória do banco de dados
     */
    private ColorMaterial sortearCor() {
        List<ColorMaterial> cores = colorMaterialRepository.listAll();
        if (cores.isEmpty()) {
            return null;
        }
        return cores.get(random.nextInt(cores.size()));
    }

    /**
     * Sorteia um tamanho base ponderado pela possibilidade
     */
    private PlayerTipoBaseTamanho sortearTamanho(PlayerRaca raca) {
        List<PlayerTipoBaseTamanho> tamanhos = playerTipoBaseTamanhoRepository
            .find("playerRaca = ?1 and ativo = true", raca)
            .list();
        
        if (tamanhos.isEmpty()) {
            throw new IllegalStateException("Nenhum tamanho configurado para a raça " + raca);
        }

        // Seleção ponderada pela possibilidade
        long totalPossibilidade = tamanhos.stream()
            .mapToLong(PlayerTipoBaseTamanho::getPossibilidade)
            .sum();
        
        long randomValue = random.nextLong(totalPossibilidade);
        long acumulado = 0;
        
        for (PlayerTipoBaseTamanho tamanho : tamanhos) {
            acumulado += tamanho.getPossibilidade();
            if (randomValue < acumulado) {
                return tamanho;
            }
        }
        
        return tamanhos.get(tamanhos.size() - 1); // fallback
    }

    /**
     * Sorteia uma configuração de orelha ponderada pela possibilidade
     */
    private PlayerTipoBaseOrelha sortearOrelha(PlayerRaca raca) {
        List<PlayerTipoBaseOrelha> orelhas = playerTipoBaseOrelhaRepository
            .find("playerRaca = ?1 and ativo = true", raca)
            .list();
        
        if (orelhas.isEmpty()) {
            throw new IllegalStateException("Nenhuma configuração de orelha para a raça " + raca);
        }

        // Seleção ponderada pela possibilidade
        long totalPossibilidade = orelhas.stream()
            .mapToLong(PlayerTipoBaseOrelha::getPossibilidade)
            .sum();
        
        long randomValue = random.nextLong(totalPossibilidade);
        long acumulado = 0;
        
        for (PlayerTipoBaseOrelha orelha : orelhas) {
            acumulado += orelha.getPossibilidade();
            if (randomValue < acumulado) {
                return orelha;
            }
        }
        
        return orelhas.get(orelhas.size() - 1); // fallback
    }

    /**
     * Gera um valor aleatório entre min e max (inclusive)
     */
    private Long randomBetween(Long min, Long max) {
        if (min.equals(max)) {
            return min;
        }
        return min + random.nextLong(max - min + 1);
    }

    /**
     * Sorteia uma cor de orelha da lista de possíveis cores
     */
    private ColorMaterial sortearCorOrelha(PlayerTipoBaseOrelha tipoOrelha) {
        List<TipoOrelhaColorMaterial> possiveisColorMaterials = tipoOrelha.getPossiveisColorMaterials();
        
        if (possiveisColorMaterials == null || possiveisColorMaterials.isEmpty()) {
            return sortearCor(); // fallback para cor aleatória
        }

        // Seleção ponderada pela possibilidade
        long totalPossibilidade = possiveisColorMaterials.stream()
            .mapToLong(TipoOrelhaColorMaterial::getPossibilidade)
            .sum();
        
        long randomValue = random.nextLong(totalPossibilidade);
        long acumulado = 0;
        
        for (TipoOrelhaColorMaterial colorMaterial : possiveisColorMaterials) {
            acumulado += colorMaterial.getPossibilidade();
            if (randomValue < acumulado) {
                return colorMaterial.getColorMaterial();
            }
        }
        
        return possiveisColorMaterials.get(possiveisColorMaterials.size() - 1).getColorMaterial(); // fallback
    }

    /**
     * Sorteia um tipo de cabelo baseado na raça com seleção ponderada
     */
    private PlayerTipoCabelo sortearCabelo(PlayerRaca raca) {
        List<PlayerTipoCabelo> cabelos = playerTipoCabeloRepository
            .find("playerRaca = ?1 and ativo = true", raca)
            .list();
        
        if (cabelos.isEmpty()) {
            return null; // sem cabelo disponível
        }

        // Seleção ponderada pela possibilidade
        long totalPossibilidade = cabelos.stream()
            .mapToLong(PlayerTipoCabelo::getPossibilidade)
            .sum();
        
        long randomValue = random.nextLong(totalPossibilidade);
        long acumulado = 0;
        
        for (PlayerTipoCabelo cabelo : cabelos) {
            acumulado += cabelo.getPossibilidade();
            if (randomValue < acumulado) {
                return cabelo;
            }
        }
        
        return cabelos.get(cabelos.size() - 1); // fallback
    }

    /**
     * Cria um novo player para o usuário logado
     */
    @Transactional
    public PlayerResponse criarPlayer(Usuario usuario, CriarPlayerRequest request) {
        // Criar PlayerPosicao
        PlayerPosicao posicao = new PlayerPosicao();
        posicao.setX(0L);
        posicao.setY(10L); // Spawna a 10 unidades de altura
        posicao.setZ(0L);
        playerPosicaoRepository.persist(posicao);

        // Criar PlayerStatus
        PlayerStatus status = new PlayerStatus();
        status.setPosicao(posicao);
        playerStatusRepository.persist(status);
        
        // Criar Player
        Player player = new Player();
        player.setUsuario(usuario);
        player.setVisibilidade(VisibilidadePerfil.AMIGOS);
        
        // Sortear raça aleatoriamente
        PlayerRaca racaSorteada = sortearRaca();
        player.setRaca(racaSorteada);
        
        // Sortear e definir tamanho baseado na raça
        PlayerTipoBaseTamanho tamanhoBase = sortearTamanho(racaSorteada);
        player.setTamanhoX(randomBetween(tamanhoBase.getEixoXMinimo(), tamanhoBase.getEixoXMaximo()));
        player.setTamanhoY(randomBetween(tamanhoBase.getEixoYMinimo(), tamanhoBase.getEixoYMaximo()));
        player.setTamanhoZ(randomBetween(tamanhoBase.getEixoZMinimo(), tamanhoBase.getEixoZMaximo()));
        
        // Sortear tipo de cabelo baseado na raça
        PlayerTipoCabelo cabeloSorteado = sortearCabelo(racaSorteada);
        player.setTipoCabelo(cabeloSorteado);
        
        // Sortear cor de pele aleatoriamente
        player.setCorPele(sortearCor());
        
        // Sortear e definir orelha baseado na raça
        PlayerTipoBaseOrelha orelhaBase = sortearOrelha(racaSorteada);
        player.setTamanhoXOrelha(randomBetween(orelhaBase.getEixoXMinimo(), orelhaBase.getEixoXMaximo()));
        player.setTamanhoYOrelha(randomBetween(orelhaBase.getEixoYMinimo(), orelhaBase.getEixoYMaximo()));
        player.setTamanhoZOrelha(randomBetween(orelhaBase.getEixoZMinimo(), orelhaBase.getEixoZMaximo()));
        
        // Sortear cor de orelha das possibilidades configuradas
        player.setCorOrelha(sortearCorOrelha(orelhaBase));
        
        player.setStatus(status);
        
        playerRepository.persist(player);
        
        return PlayerResponse.from(player);
    }

    /**
     * Lista todos os players do usuário logado
     */
    public List<PlayerResponse> listarPlayersDoUsuario(Usuario usuario) {
        List<Player> players = playerRepository.findByUsuario(usuario);
        return players.stream()
                .map(PlayerResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * Lista todos os players do sistema
     */
    public List<PlayerResponse> listarTodosPlayers() {
        List<Player> players = playerRepository.listAll();
        return players.stream()
                .map(PlayerResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * Busca um player por ID
     */
    public PlayerResponse buscarPorId(Long id) {
        Player player = playerRepository.findById(id);
        return player != null ? PlayerResponse.from(player) : null;
    }

    @Transactional
    public PlayerResponse alterarNickName(Long playerId, String novoNick, String tag) {
        Player player = playerRepository.findById(playerId);
        if (player == null) {
            throw new IllegalArgumentException("Player nao encontrado");
        }

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime ultima = player.getUltimaAlteracaoNickName();

        if (ultima != null) {
            LocalDateTime proximaData = ultima.plusDays(60);
            if (proximaData.isAfter(agora)) {
                long diasRestantes = ChronoUnit.DAYS.between(agora.toLocalDate(), proximaData.toLocalDate());
                if (diasRestantes < 0) {
                    diasRestantes = 0;
                }
                throw new IllegalArgumentException(
                    "NickName so pode ser alterado a cada 60 dias. Dias restantes: " + diasRestantes);
            }
        }

        String tagFinal = (tag == null || tag.isBlank()) ? "0000" : tag;

        if (!isValidTag(tagFinal)) {
            throw new IllegalArgumentException("Tag invalida: deve ser UTF-8 e conter exatamente 4 caracteres");
        }

        if (novoNick == null || novoNick.isBlank()) {
            throw new IllegalArgumentException("NickName nao pode ser vazio");
        }

        player.setNickName(novoNick);
        player.setTag(tagFinal);
        player.setUltimaAlteracaoNickName(agora);

        return PlayerResponse.from(player);
    }

    private boolean isValidTag(String tag) {
        if (tag == null) {
            return false;
        }
        if (tag.codePointCount(0, tag.length()) != 4) {
            return false;
        }
        // Strings Java sao UTF-16, mas assumimos entrada UTF-8 da API.
        return true;
    }

    /**
     * Atualiza a posição do player
     */
    @Transactional
    public void atualizarPosicao(Long playerId, Long x, Long y, Long z) {
        Player player = playerRepository.findById(playerId);
        
        if (player == null) {
            throw new IllegalArgumentException("Player não encontrado");
        }

        PlayerStatus status = player.getStatus();
        if (status == null) {
            throw new IllegalStateException("Player não possui status");
        }

        // Cria uma NOVA posição (a anterior fica no histórico)
        PlayerPosicao novaPosicao = new PlayerPosicao();
        novaPosicao.setPlayer(player);
        novaPosicao.setX(x);
        novaPosicao.setY(y);
        novaPosicao.setZ(z);
        
        // Define a ilha atual do player como ilha atual da posição
        if (player.getIlhaAtual() != null) {
            novaPosicao.setIlhaAtual(player.getIlhaAtual());
        }
        
        // Persiste a nova posição
        playerPosicaoRepository.persist(novaPosicao);
        
        // Atualiza o status para apontar para a nova posição (última posição)
        status.setPosicao(novaPosicao);
        playerStatusRepository.persist(status);
    }
}
