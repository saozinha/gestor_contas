package com.lourenco.gestor_contas.tools.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends ResponseStatusException {

    public ConflictException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }

}
