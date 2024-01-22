package com.neondomain.neon.controller;

import com.neondomain.neon.dto.CitaDTO;
import com.neondomain.neon.dto.ConsultaCitasRequest;
import com.neondomain.neon.entity.Cita;
import com.neondomain.neon.repository.CitaRepository;
import com.neondomain.neon.repository.ConsultorioRepository;
import com.neondomain.neon.repository.DoctorRepository;
import com.neondomain.neon.service.CitaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("citas")
public class CitaController {


    @Autowired
    CitaServices citaServices;

    @Autowired
    ConsultorioRepository consultorioRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping("/create")
    public ResponseEntity<Cita> save(@Valid @RequestBody Cita cita){
        Cita data = citaServices.saveData(cita);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/consulta")
    public ResponseEntity<List<Cita>> consultar(@RequestBody ConsultaCitasRequest request) {
        List<Cita> citas = citaServices.consultar(request.getFecha(), consultorioRepository.findById(request.getConsultorioId()).orElse(null), doctorRepository.findById(request.getDoctorId()).orElse(null));
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @PostMapping("/cancelar/{citaId}")
    public ResponseEntity<String> cancelarCita(@PathVariable Long citaId) {
        try {
            citaServices.cancelarCita(citaId);
            return new ResponseEntity<>("Cita cancelada exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/editar/{citaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cita> editarCita(@PathVariable Long citaId, @RequestBody Cita nuevaCita) {
        try {
            Cita citaEditada = citaServices.editarCita(citaId, nuevaCita);
            return new ResponseEntity<>(citaEditada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
