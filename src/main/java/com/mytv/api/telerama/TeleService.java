package com.mytv.api.telerama;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TeleService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final String externalApiUrl = "http://mytelevision.stream:8000/";

    public Mono<String> fetchTeleData() {
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(externalApiUrl)
                .retrieve()
                .bodyToMono(String.class);
    }
}
