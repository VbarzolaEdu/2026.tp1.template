package com.bibliotech.service;

import com.bibliotech.model.*;
import com.bibliotech.repository.Repository;

import java.time.LocalDate;
import java.util.Optional;

public class PrestamoService {

    private final Repository<Libro, String> libroRepo;
    private final Repository<Socio, Integer> socioRepo;
    private final Repository<Prestamo, String> prestamoRepo;

    public PrestamoService(
            Repository<Libro, String> libroRepo,
            Repository<Socio, Integer> socioRepo,
            Repository<Prestamo, String> prestamoRepo) {

        this.libroRepo = libroRepo;
        this.socioRepo = socioRepo;
        this.prestamoRepo = prestamoRepo;
    }

    public void realizarPrestamo(String isbn, int socioId) {

        // 1. validar libro
        Libro libro = libroRepo.buscarPorId(isbn)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        // 2. validar socio
        Socio socio = socioRepo.buscarPorId(socioId)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        // 3. validar disponibilidad
        boolean prestado = prestamoRepo.buscarTodos().stream()
                .anyMatch(p ->
                        p.isbnLibro().equals(isbn) &&
                                p.fechaDevolucion().isEmpty()
                );

        if (prestado) {
            throw new RuntimeException("Libro ya prestado");
        }

        // 4. validar límite
        long prestamosActivos = prestamoRepo.buscarTodos().stream()
                .filter(p ->
                        p.socioId() == socioId &&
                                p.fechaDevolucion().isEmpty()
                )
                .count();

        if (prestamosActivos >= socio.limitePrestamos()) {
            throw new RuntimeException("Límite de préstamos alcanzado");
        }

        // 5. crear préstamo
        Prestamo prestamo = new Prestamo(
                isbn,
                socioId,
                LocalDate.now(),
                Optional.empty()
        );

        prestamoRepo.guardar(prestamo);
    }

    public void devolverLibro(String isbn, int socioId) {

        // 1. buscar préstamo activo
        Prestamo prestamoActivo = prestamoRepo.buscarTodos().stream()
                .filter(p ->
                        p.isbnLibro().equals(isbn) &&
                                p.socioId() == socioId &&
                                p.fechaDevolucion().isEmpty()
                )
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No existe préstamo activo"));

        // 2. crear nuevo préstamo con fecha de devolución
        Prestamo prestamoDevuelto = new Prestamo(
                prestamoActivo.isbnLibro(),
                prestamoActivo.socioId(),
                prestamoActivo.fechaPrestamo(),
                Optional.of(LocalDate.now())
        );

        // 3. guardar (sobrescribe el anterior)
        prestamoRepo.guardar(prestamoDevuelto);
    }
}