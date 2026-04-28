package com.bibliotech.exceptions;

public class LibroNoEncontradoException extends BibliotecaException {
    public LibroNoEncontradoException(String isbn) {
        super("Libro no se encontro con el ISBN" + isbn);
    }
}
