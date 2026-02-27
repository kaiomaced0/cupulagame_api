package org.cupula.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.cupula.dto.backgroundasset.response.BackgroundAssetChangedWebhookPayload;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BackgroundAssetWebhookService {

    private static final Logger LOG = Logger.getLogger(BackgroundAssetWebhookService.class);

    @ConfigProperty(name = "cupula.background.webhook.url", defaultValue = "")
    String webhookUrl;

    @Inject
    ObjectMapper objectMapper;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void notifyBackgroundChanged(BackgroundAssetChangedWebhookPayload payload) {
        if (webhookUrl == null || webhookUrl.isBlank() || payload == null) {
            return;
        }

        try {
            String body = objectMapper.writeValueAsString(payload);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(webhookUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                LOG.warnf("Webhook de background retornou status %s", response.statusCode());
            }
        } catch (JsonProcessingException e) {
            LOG.error("Erro ao serializar payload de webhook de background", e);
        } catch (IOException | InterruptedException e) {
            LOG.error("Erro ao enviar webhook de troca de background", e);
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
