package com.bibliotech.repository;

import java.util.*;
import com.bibliotech.model.Socio;


public class SocioRepositeryImpl implements Repository<Socio,Integer> {

    private final Map <Integer, Socio> almacenamiento = new HashMap<>();

    @Override
    public void guardar(Socio socio) {
        almacenamiento.put(socio.id(), socio);
    }

    @Override
    public Optional<Socio> buscarPorId(Integer id) {
        return Optional.ofNullable(almacenamiento.get(id));
    }

    @Override
    public List<Socio> buscarTodos() {
        return new ArrayList<>(almacenamiento.values());
    }

}
