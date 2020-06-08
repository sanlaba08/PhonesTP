package com.utn.TPFinal.exceptions;

import java.sql.SQLException;

public class IncorrectDataClientPhoneException extends SQLException {
    public IncorrectDataClientPhoneException(String message) {
        super(message);
    }
}
