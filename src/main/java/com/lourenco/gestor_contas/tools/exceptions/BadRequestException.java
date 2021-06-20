package com.lourenco.gestor_contas.tools.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends ResponseStatusException {
    public BadRequestException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
