package com.bibliotech.service;
import com.bibliotech.model.Libro;
import com.bibliotech.repository.Repository;

import java.util.List;
import java.util.Optional;

public class LibroService {

    private final Repository<Libro,String> libroRepositery;

    public LibroService(Repository<Libro,String> libroRepositery){
        this.libroRepositery= libroRepositery;
    }

    public void agregarLibro(Libro libro){
        libroRepositery.guardar(libro);
    }

    public Optional <Libro> buscarPorIsbn(String isbn){
        return libroRepositery.buscarPorId(isbn);
    }

    public List<Libro> listarLibros(){
        return libroRepositery.buscarTodos();
    }

}
