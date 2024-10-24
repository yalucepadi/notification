package com.reto.reto.utils;

import com.reto.reto.model.request.NotificacionRequest;
import com.reto.reto.model.request.NotificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface mapper {

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "message", source = "message")
    NotificacionRequest toNotification(NotificationDto notificationDto);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "message", source = "message")
    NotificationDto toDto(NotificacionRequest notification);

}
