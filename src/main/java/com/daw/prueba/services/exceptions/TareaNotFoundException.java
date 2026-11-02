package com.daw.prueba.services.exceptions;

public class TareaNotFoundException extends RuntimeException {

    public TareaNotFoundException(String message) {
        super(message);
    }
}