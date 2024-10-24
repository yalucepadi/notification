package com.reto.reto.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
public class MockRequestNotFoundException {
    private final String name;
    private final String message;
    private final String header;

    public MockRequestNotFoundException(String header, String name, String message) {
        this.message= message;
        this.name = name;
        this.header = header;

    }






}
