package com.neondomain.neon.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CitaDTO {
    @NotNull(message = "Doctor Id can't be null")
    private Long doctorId;

    @NotNull(message = "Consultorio Id can't be null")
    private Long consultorioId;

    @NotNull(message = "Hoario can't be null")
    private LocalDateTime horario;

    @NotNull(message = "Nombre Paciente can't be null")
    private String nombrePaciente;

}
