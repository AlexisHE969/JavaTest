package com.neondomain.neon.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultaCitasRequest {
    private LocalDate fecha;
    private Long consultorioId;
    private Long doctorId;
}
