package com.neondomain.neon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nombre can't be null")
    @NotBlank(message = "Nombre can't be empty")
    private String Nombre;

    @NotNull(message = "Apellido Paterno can't be null")
    @NotBlank(message = "Apellido Paterno can't be empty")
    private String apellidoPaterno;

    @NotNull(message = "Apellido Materno can't be null")
    @NotBlank(message = "Apellido Materno can't be empty")
    private String apellidoMaterno;

    @NotNull(message = "Especialidad can't be null")
    @NotBlank(message = "Especialidad can't be empty")
    private String especialidad;

    @OneToMany(mappedBy = "doctor")
    private List<Cita> citas;

}
