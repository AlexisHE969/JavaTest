package com.neondomain.neon.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consultorio_id")  // Nombre de la columna para la relación con Consultorio
    @JsonBackReference
    private Consultorio consultorio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")  // Nombre de la columna para la relación con Doctor
    @JsonBackReference
    private Doctor doctor;

    private LocalDateTime horario;
    private String nombrePaciente;

    private boolean cancelada;
}
