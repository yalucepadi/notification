package com.reto.reto.proxy.rest;

import com.reto.reto.model.request.NotificationDto;
import reactor.core.publisher.Mono;

public interface NotificationService {

    Mono<NotificationDto>enviarNotificacion(NotificationDto notificationDto);

}
