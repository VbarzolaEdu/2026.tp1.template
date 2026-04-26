package com.bibliotech.service;

import com.bibliotech.model.Socio;
import com.bibliotech.repository.Repository;

import java.util.List;
import java.util.Optional;

public class SocioService {

    private final Repository<Socio, Integer> socioRepository;

    public SocioService(Repository<Socio, Integer> socioRepository) {
        this.socioRepository = socioRepository;
    }

    public void agregarSocio(Socio socio) {
        socioRepository.guardar(socio);
    }

    public Optional<Socio> buscarPorId(Integer id) {
        return socioRepository.buscarPorId(id);
    }

    public List<Socio> listarSocios() {
        return socioRepository.buscarTodos();
    }
}