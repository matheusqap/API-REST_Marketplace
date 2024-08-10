package com.github.qualquercoisavinteconto.exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
        super("Usuário já existe");
    }
}
