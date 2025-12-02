package org.cupula.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.cupula.dto.auth.CreateUsuarioRequest;
import org.cupula.dto.auth.UpdateUsuarioRequest;
import org.cupula.dto.responses.usuario.UsuarioResponseDTO;
import org.cupula.model.auth.Usuario;
import org.cupula.model.auth.UsuarioProvider;
import org.cupula.model.auth.enums.AuthProvider;
import org.cupula.model.auth.enums.Perfil;
import org.cupula.repository.auth.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashService hashService;

    public List<UsuarioResponseDTO> listUsuarios() {
        return usuarioRepository.listAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO findUsuarioById(Long id) {
        if (id == null) {
            return null;
        }
        return toResponse(usuarioRepository.findById(id));
    }

    @Transactional
    public UsuarioResponseDTO createUsuario(CreateUsuarioRequest request) {
        if (request == null || request.login() == null || request.email() == null || request.senha() == null) {
            throw new IllegalArgumentException("Dados obrigatorios faltando");
        }

        if (usuarioRepository.findByLogin(request.login()) != null) {
            throw new IllegalArgumentException("Login ja existe");
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(request.login());
        usuario.setEmail(request.email());
        usuario.setSenha(hashService.getHashSenha(request.senha()));
        usuario.setLoginLocalHabilitado(Boolean.TRUE);
        usuario.setMudarSenha(Boolean.FALSE);
        usuario.setPerfis(new HashSet<>(Set.of(Perfil.USER)));

        usuarioRepository.persist(usuario);
        return toResponse(usuario);
    }

    @Transactional
    public UsuarioResponseDTO updateUsuario(Long id, UpdateUsuarioRequest request) {
        if (id == null || request == null) {
            throw new IllegalArgumentException("Dados obrigatorios faltando");
        }

        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            return null;
        }

        if (request.login() != null && !request.login().equals(usuario.getLogin())) {
            Usuario existing = usuarioRepository.findByLogin(request.login());
            if (existing != null && !existing.getId().equals(id)) {
                throw new IllegalArgumentException("Login ja existe");
            }
            usuario.setLogin(request.login());
        }

        if (request.email() != null) {
            usuario.setEmail(request.email());
        }

        if (request.loginLocalHabilitado() != null) {
            usuario.setLoginLocalHabilitado(request.loginLocalHabilitado());
        }

        if (request.senha() != null) {
            usuario.setSenha(hashService.getHashSenha(request.senha()));
        }

        return toResponse(usuario);
    }

    @Transactional
    public boolean deleteUsuario(Long id) {
        if (id == null) {
            return false;
        }

        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            return false;
        }

        usuarioRepository.delete(usuario);
        return true;
    }

    private UsuarioResponseDTO toResponse(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        Set<AuthProvider> providers = usuario.getProvedoresLogin() == null
                ? Collections.emptySet()
                : usuario.getProvedoresLogin().stream()
                        .map(UsuarioProvider::getProvider)
                        .collect(Collectors.toSet());

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getEmail(),
                usuario.getLoginLocalHabilitado(),
                usuario.getPerfis(),
                providers);
    }
}
