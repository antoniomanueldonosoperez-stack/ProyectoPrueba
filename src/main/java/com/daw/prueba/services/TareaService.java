package com.daw.prueba.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.prueba.persistence.entities.Tarea;
import com.daw.prueba.persistence.entities.enums.Prioridad;
import com.daw.prueba.persistence.repositories.TareaRepository;
import com.daw.prueba.services.exceptions.TareaException;
import com.daw.prueba.services.exceptions.TareaNotFoundException;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> findAll() {
        return this.tareaRepository.findAll();
    }

    public Tarea findById(int id) {
        if (!this.tareaRepository.existsById(id)) {
            throw new TareaNotFoundException("El ID de la tarea no existe.");
        }
        return this.tareaRepository.findById(id).get();
    }

    public Tarea create(Tarea tarea) {
        if (tarea.getId() != null) {
            throw new TareaException("No se puede crear una tarea indicando el ID.");
        }
        if (tarea.getFechaLimite() != null && tarea.getFechaLimite().isBefore(LocalDate.now())) {
            throw new TareaException("La fecha límite no puede ser anterior a hoy.");
        }
        tarea.setCompletada(false);
        return this.tareaRepository.save(tarea);
    }

    public Tarea update(int id, Tarea tarea) {
        if (!this.tareaRepository.existsById(id)) {
            throw new TareaNotFoundException("El ID de la tarea no existe.");
        }
        if (tarea.getId() == null || id != tarea.getId()) {
            throw new TareaException("El ID del path y el del body no coinciden.");
        }
        if (tarea.getCompletada() != null) {
            throw new TareaException("No se permite modificar el estado de completada.");
        }

        Tarea existente = this.tareaRepository.findById(id).get();
        existente.setTitulo(tarea.getTitulo());
        existente.setFechaLimite(tarea.getFechaLimite());
        existente.setPrioridad(tarea.getPrioridad());
        return this.tareaRepository.save(existente);
    }

    public void delete(int id) {
        if (!this.tareaRepository.existsById(id)) {
            throw new TareaNotFoundException("El ID de la tarea no existe.");
        }
        this.tareaRepository.deleteById(id);
        
    }
    public List<Tarea> findByPrioridad(Prioridad prioridad) {
        return this.tareaRepository.findByPrioridad(prioridad);
    }

    public List<Tarea> findByTitulo(String titulo) {
        return this.tareaRepository.findByTituloContainingIgnoreCase(titulo);
    }
}