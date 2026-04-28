package com.bibliotech.exceptions;

public class PrestamoNoEncontradoException extends BibliotecaException {
    public PrestamoNoEncontradoException() {
        super("No existe este prestmao");
    }
}
