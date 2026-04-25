package com.bibliotech.model;

public record Docente(
        int id,
        String nombre,
        String email
) implements Socio {

    @Override
    public int limitePrestamos() {
        return 5;
    }
}