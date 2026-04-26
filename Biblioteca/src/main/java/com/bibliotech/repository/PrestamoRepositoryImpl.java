package com.bibliotech.repository;

import com.bibliotech.model.Prestamo;
import java.util.*;

public class PrestamoRepositoryImpl implements Repository<Prestamo,String> {

    private final Map<String,Prestamo> almacenamiento = new HashMap<>();

    @Override
    public void guardar (Prestamo prestamo){
        almacenamiento.put(generarId(prestamo),prestamo);
    }

    @Override
    public Optional<Prestamo> buscarPorId(String id) {
        return Optional.ofNullable(almacenamiento.get(id));
    }

    @Override
    public List<Prestamo> buscarTodos() {
        return new ArrayList<>(almacenamiento.values());
    }

    private String generarId(Prestamo prestamo) {
        return prestamo.isbnLibro() + "-" +
                prestamo.socioId() + "-" +
                prestamo.fechaPrestamo();
    }

}
