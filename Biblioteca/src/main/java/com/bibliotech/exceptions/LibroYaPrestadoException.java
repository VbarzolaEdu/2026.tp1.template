package com.bibliotech.exceptions;

public class LibroYaPrestadoException extends BibliotecaException {
    public LibroYaPrestadoException(String isbn) {
        super("El libro ya esta prestado: " + isbn);
    }
}
