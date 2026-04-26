package com.bibliotech.model;

import java.time.LocalDate;
import java.util.Optional;


public record Prestamo(String isbnLibro,
                       int SocioId,
                       LocalDate fechaprestamo,
                       Optional<LocalDate> fechaDevolucion) {}

