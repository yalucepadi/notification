package com.reto.reto.proxy.rest.impl;

import com.reto.reto.exception.NotFoundException;
import com.reto.reto.model.request.NotificacionRequest;
import com.reto.reto.model.request.NotificationDto;
import com.reto.reto.model.response.NotificationResponse;
import com.reto.reto.proxy.rest.NotificationService;

import com.reto.reto.utils.Constants;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Service
public class NotificationImpl implements NotificationService {


    private WebClient webClient;
    @PostConstruct
    protected void init(){
        webClient = WebClient.builder()
                .baseUrl("https://d0c79438-6cd6-488d-9b2c-66d9d742a0e9.mock.pstmn.io")
                .build();


    }

    @Override
    public Mono<NotificationDto> enviarNotificacion(NotificationDto notificationDto) {
       return   webClient.post()
                .uri("/notifications/mock")
                .bodyValue(new NotificationDto(notificationDto.getUserId(), notificationDto.getMessage()))
                .retrieve()
                .onStatus(httpStatus -> httpStatus.equals(HttpStatus.NOT_FOUND), clientResponse -> {

                    return Mono.error(new NotFoundException("Notificación no encontrada en el servicio externo."));
                })
                .bodyToMono(NotificationResponse.class)
                .map(response -> new NotificationDto(response.getId(), response.getMessage()))
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new NotFoundException("Notificación no encontrada en el servicio externo."));
                    }
                    return Mono.error(new RuntimeException("Error en la comunicación con el servicio externo"));
                    });




    }


}
