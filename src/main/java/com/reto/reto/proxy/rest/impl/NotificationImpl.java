package com.reto.reto.proxy.rest.impl;

import com.reto.reto.model.request.NotificationDto;
import com.reto.reto.model.response.NotificationResponse;
import com.reto.reto.proxy.rest.NotificationService;
import com.reto.reto.proxy.rest.util.RestConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class NotificationImpl implements NotificationService {


    private WebClient webClient;
    @PostConstruct
    protected void init(){
        webClient = WebClient.builder()
                .baseUrl("https://d0c79438-6cd6-488d-9b2c-66d9d742a0e9.mock.pstmn.io")
                .clientConnector(
                        RestConfig.getClientConnector(
                                100,
                                100,
                                100))
                .build();


    }

    @Override
    public Mono<NotificationDto> enviarNotificacion(NotificationDto notificationDto) {
        return  webClient.post()
                .uri("/notifications/mock")
                .bodyValue(new NotificationDto(notificationDto.getUserId(), notificationDto.getMessage()))
                        .retrieve()
                .bodyToMono(NotificationResponse.class)
                .map(response ->  new NotificationDto(response.getId(), response.getMessage()));

    }


}
