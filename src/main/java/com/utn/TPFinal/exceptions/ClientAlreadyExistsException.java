package com.utn.TPFinal.exceptions;

public class ClientAlreadyExistsException extends Throwable {
    public ClientAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return "Client existent";
    }
}

