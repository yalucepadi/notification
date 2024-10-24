package com.reto.reto.utils;

import com.reto.reto.model.response.ResponseGeneralDto;

public class NotificationAdapter {
    public static ResponseGeneralDto responseGeneral(String code, Integer status, String message, Object data) {
        return ResponseGeneralDto.builder()
                .status(status)
                .code(code)
                .comment(message)
                .data(data)
                .build();



    }


}
