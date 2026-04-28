package com.bibliotech.exceptions;

public class SocioNoEncontradoException extends BibliotecaException{
    public SocioNoEncontradoException(int id) {
        super("Socio no encontrado con ID " + id);
    }
}
