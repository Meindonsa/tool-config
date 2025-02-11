package com.meindonsa.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class FunctionalException extends RuntimeException {
    /** */
    private static final long serialVersionUID = 1L;

    public FunctionalException(String exception) {
        super(exception);
    }
}
