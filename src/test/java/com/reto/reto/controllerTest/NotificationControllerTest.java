package com.reto.reto.controllerTest;

import com.reto.reto.expose.NotificationController;
import com.reto.reto.model.request.NotificationDto;
import com.reto.reto.proxy.rest.impl.NotificationImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(NotificationController.class)

public class NotificationControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private NotificationImpl notificacionService;

    @Test
    public void testEnviarNotificacion() {
        NotificationDto notificacion = new NotificationDto("user123", "Test message");

        when(notificacionService.enviarNotificacion(notificacion))
                .thenReturn(Mono.just(notificacion));

        webTestClient.post()
                .uri("/api/notificaciones")
                .bodyValue(notificacion)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(NotificationDto.class)
                .value(response -> {
                    assert response.getUserId().equals("user123");
                    assert response.getMessage().equals("Test message");
           });
}


}
