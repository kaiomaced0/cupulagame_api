package org.cupula.initializer;

import java.time.LocalDate;

import org.cupula.dto.auth.CreateUsuarioRequest;
import org.cupula.dto.pessoa.CreatePessoaRequest;
import org.cupula.dto.player.request.CriarPlayerRequest;
import org.cupula.model.auth.Usuario;
import org.cupula.model.auth.enums.Perfil;
import org.cupula.model.auth.pessoa.Sexo;
import org.cupula.repository.auth.UsuarioRepository;
import org.cupula.service.PessoaService;
import org.cupula.service.PlayerService;
import org.cupula.service.UsuarioService;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Startup
public class MyInitializer {

    @Inject
    PessoaService pessoaService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    PlayerService playerService;

    @Inject
    UsuarioRepository usuarioRepository;

    @PostConstruct
    @Transactional
    public void init() {
        System.out.println("=== Iniciando Seeds ===");
        
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
}
