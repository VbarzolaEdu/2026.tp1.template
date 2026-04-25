package main.java.com.bibliotech.model;

public record Libro(String isbn, String titulo, String autor, int anio, String categoria) implements Recurso {
}

