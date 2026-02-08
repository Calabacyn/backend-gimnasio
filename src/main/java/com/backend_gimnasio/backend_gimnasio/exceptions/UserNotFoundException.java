package com.backend_gimnasio.backend_gimnasio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Usuario %s no encontrado";

    public UserNotFoundException() {
        super("User not found");
    }


    public UserNotFoundException(String userEmail) {
        super(String.format(ERROR_MESSAGE, userEmail));
    }
}

