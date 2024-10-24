package com.reto.reto.expose;

import com.reto.reto.model.request.NotificationDto;
import com.reto.reto.model.response.NotificationResponse;
import com.reto.reto.proxy.rest.impl.NotificationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/notificaciones")

public class NotificationController {

    @Autowired
    private NotificationImpl notificacionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<NotificationDto> enviarNotificacion(@RequestBody NotificationDto notificacion) {
        return notificacionService.enviarNotificacion(notificacion);
   }





}
