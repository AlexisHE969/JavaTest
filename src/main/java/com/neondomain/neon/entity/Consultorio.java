package com.neondomain.neon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Numero can't be null")
    private int numero;

    @NotNull(message = "Piso can't be null")
    private int piso;

    @OneToMany(mappedBy = "consultorio")
    private List<Cita> citas;
}
