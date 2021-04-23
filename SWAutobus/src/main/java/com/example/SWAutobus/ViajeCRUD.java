package com.example.SWAutobus;

import org.springframework.data.repository.CrudRepository;

public interface ViajeCRUD extends CrudRepository<Viajes,Integer> {
    Viajes findByViaje(String viaje);
}
