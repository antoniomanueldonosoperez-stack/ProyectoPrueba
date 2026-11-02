package com.daw.prueba.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.prueba.persistence.entities.Tarea;
import com.daw.prueba.persistence.entities.enums.Prioridad;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {

    List<Tarea> findByPrioridad(Prioridad prioridad);

    List<Tarea> findByTituloContainingIgnoreCase(String titulo);
}