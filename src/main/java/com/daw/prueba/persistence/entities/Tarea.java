package com.daw.prueba.persistence.entities;

import java.time.LocalDate;

import com.daw.prueba.persistence.entities.enums.Prioridad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarea")
@Getter
@Setter
@NoArgsConstructor
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;

    @Enumerated(value = EnumType.STRING)
    private Prioridad prioridad;

    private Boolean completada;
}