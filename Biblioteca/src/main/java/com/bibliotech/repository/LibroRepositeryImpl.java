package com.bibliotech.repository;

import com.bibliotech.model.Libro;
import java.util.*;

public class LibroRepositeryImpl implements Repository<Libro,String> {

    private final Map <String,Libro> almacenamiento = new HashMap<>();

    @Override
    public void guardar (Libro libro){
        almacenamiento.put(libro.isbn(),libro);

    }
    @Override
    public Optional<Libro> buscarPorId(String isbn) {
        return Optional.ofNullable(almacenamiento.get(isbn));
    }
    @Override
    public List<Libro> buscarTodos(){
        return new ArrayList<>(almacenamiento.values());
    }
}
