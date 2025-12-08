package org.cupula.service;


import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import org.cupula.model.auth.Usuario;
import org.cupula.model.entities.player.Player;
import org.jboss.logging.Logger;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenJwtService {
    private static final Duration EXPIRATION_TIME = Duration.ofDays(700);

    public static final Logger LOG = Logger.getLogger(TokenJwtService.class);

    public String generateJwt(Usuario usuario) {

        try {
            Instant now = Instant.now();

            Instant expiryDate = now.plus(EXPIRATION_TIME);

            Set<String> roles = usuario.getPerfis()
                    .stream().map(p -> p.getLabel())
                    .collect(Collectors.toSet());

            LOG.info("Requisição TokenJwt.generateJwt()");

            return Jwt.issuer("kaioprojects-jwt")
                    .subject(usuario.getLogin())
                    .groups(roles)
                    .expiresAt(expiryDate)
                    .sign();

        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição TokenJwt.generateJwt()");
            return null;
        }

    }

    public String generateJwt(Usuario usuario, Player player) {
        try {
            Instant now = Instant.now();

            Instant expiryDate = now.plus(EXPIRATION_TIME);

            Set<String> roles = usuario.getPerfis()
                    .stream().map(p -> p.getLabel())
                    .collect(Collectors.toSet());

            LOG.info("Requisição TokenJwt.generateJwt(usuario,player)");

            return Jwt.issuer("kaioprojects-jwt")
                    .subject(usuario.getLogin())
                    .groups(roles)
                    .claim("playerId", player == null ? null : player.getId())
                    .expiresAt(expiryDate)
                    .sign();

        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição TokenJwt.generateJwt(usuario,player)");
            return null;
        }
    }
}
