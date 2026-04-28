package com.bibliotech.exceptions;

public class LimitePrestamosException extends BibliotecaException {
    public LimitePrestamosException(int socioid) {
        super("El socio alcanzo su limite de prestamos " + socioid);
    }
}
