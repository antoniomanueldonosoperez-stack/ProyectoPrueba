package com.daw.prueba.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.prueba.persistence.entities.Tarea;
import com.daw.prueba.persistence.entities.enums.Prioridad;
import com.daw.prueba.services.TareaService;
import com.daw.prueba.services.exceptions.TareaException;
import com.daw.prueba.services.exceptions.TareaNotFoundException;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(this.tareaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(this.tareaService.findById(id));
        } catch (TareaNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Tarea tarea) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.tareaService.create(tarea));
        } catch (TareaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Tarea tarea) {
        try {
            return ResponseEntity.ok(this.tareaService.update(id, tarea));
        } catch (TareaNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (TareaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            this.tareaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (TareaNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/prioridad")
    public ResponseEntity<?> findByPrioridad(@RequestParam Prioridad prioridad) {
        return ResponseEntity.ok(this.tareaService.findByPrioridad(prioridad));
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> findByTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(this.tareaService.findByTitulo(titulo));
    }
}