package com.lourenco.gestor_contas.tools.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotBalanceAcceptableException extends ResponseStatusException {

    public NotBalanceAcceptableException(String reason) {
        super(HttpStatus.NOT_ACCEPTABLE, reason);
    }

}
