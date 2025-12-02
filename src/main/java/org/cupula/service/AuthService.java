package org.cupula.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.cupula.dto.auth.AuthResponse;
import org.cupula.dto.auth.CreateUsuarioRequest;
import org.cupula.dto.auth.LoginRequest;
import org.cupula.dto.auth.ProviderLoginRequest;
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
public class AuthService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashService hashService;

    @Inject
    TokenJwtService tokenJwtService;

    public AuthResponse login(LoginRequest request) {
        if (request == null || request.login() == null || request.senha() == null) {
            return null;
        }

        Usuario usuario = usuarioRepository.findByLogin(request.login());
        if (usuario == null || Boolean.FALSE.equals(usuario.getLoginLocalHabilitado())) {
            return null;
        }

        if (usuario.getSenha() == null) {
            return null;
        }

        String senhaHash = hashService.getHashSenha(request.senha());
        boolean senhaCorreta = senhaHash.equals(usuario.getSenha()) || request.senha().equals(usuario.getSenha());
        if (!senhaCorreta) {
            return null;
        }

        return toAuthResponse(usuario, tokenJwtService.generateJwt(usuario));
    }

    public AuthResponse loginWithProvider(ProviderLoginRequest request) {
        if (request == null || request.provider() == null) {
            return null;
        }

        AuthProvider provider = request.provider();
        Usuario usuario = null;

        if (request.externalId() != null) {
            usuario = usuarioRepository.findByProvider(provider, request.externalId());
        }

        if (usuario == null && request.email() != null) {
            Usuario candidate = usuarioRepository.findByEmail(request.email());
            if (candidate != null && usuarioRepository.hasProvider(candidate, provider)) {
                usuario = candidate;
            }
        }

        return toAuthResponse(usuario, usuario == null ? null : tokenJwtService.generateJwt(usuario));
    }

    @Transactional
    public UsuarioResponseDTO createUsuario(CreateUsuarioRequest request) {
        if (request == null || request.login() == null || request.senha() == null || request.email() == null) {
            return null;
        }

        if (usuarioRepository.findByLogin(request.login()) != null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(request.login());
        usuario.setEmail(request.email());
        usuario.setSenha(hashService.getHashSenha(request.senha()));
        usuario.setLoginLocalHabilitado(Boolean.TRUE);
        usuario.setMudarSenha(Boolean.FALSE);
        usuario.setPerfis(new HashSet<>(Set.of(Perfil.USER)));

        usuarioRepository.persist(usuario);

        return toUsuarioResponse(usuario);
    }

    private AuthResponse toAuthResponse(Usuario usuario, String token) {
        if (usuario == null) {
            return null;
        }

        Set<AuthProvider> providers = extractProviders(usuario);
        return new AuthResponse(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getEmail(),
                usuario.getLoginLocalHabilitado(),
                usuario.getPerfis(),
                providers,
                token);
    }

    private UsuarioResponseDTO toUsuarioResponse(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        Set<AuthProvider> providers = extractProviders(usuario);
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getEmail(),
                usuario.getLoginLocalHabilitado(),
                usuario.getPerfis(),
                providers);
    }

    private Set<AuthProvider> extractProviders(Usuario usuario) {
        return usuario.getProvedoresLogin() == null
                ? Collections.emptySet()
                : usuario.getProvedoresLogin().stream()
                        .map(UsuarioProvider::getProvider)
                        .collect(Collectors.toSet());
    }
}
