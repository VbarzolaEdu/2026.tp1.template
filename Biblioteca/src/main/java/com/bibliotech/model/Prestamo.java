package com.bibliotech.model;

import java.time.LocalDate;
import java.util.Optional;


public record Prestamo(String isbnLibro,
                       int socioId,
                       LocalDate fechaPrestamo,
                       Optional<LocalDate> fechaDevolucion) {}

