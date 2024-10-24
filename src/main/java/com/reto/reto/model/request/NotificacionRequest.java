package com.reto.reto.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionRequest {
private String id;
private String userId;
private String message;
private LocalDateTime timestamp;


}
