package com.neondomain.neon.service;

import com.neondomain.neon.entity.Cita;
import com.neondomain.neon.entity.Consultorio;
import com.neondomain.neon.entity.Doctor;
import com.neondomain.neon.repository.CitaRepository;
import com.neondomain.neon.repository.ConsultorioRepository;
import com.neondomain.neon.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServices {

    @Autowired
    CitaRepository citaRepository;

    @Autowired
    ConsultorioRepository consultorioRepository;

    @Autowired
    DoctorRepository doctorRepository;



    public Cita saveData(Cita cita){


        if (validarCita(cita)) {
            citaRepository.save(cita);

            return cita;
        } else {
            throw new RuntimeException("La cita no cumple con las validaciones");
        }
    }

    public List<Cita> consultar(LocalDate fecha, Consultorio consultorio, Doctor doctor) {
        LocalDateTime inicioDelDia = fecha.atStartOfDay();
        LocalDateTime finDelDia = fecha.atTime(23, 59, 59);
        return citaRepository.findByHorarioBetweenAndConsultorioAndDoctor(inicioDelDia, finDelDia, consultorio, doctor);
    }

    public void cancelarCita(Long citaId) {
        Optional<Cita> optionalCita = citaRepository.findById(citaId);

        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();

            // Verifica si la cita está pendiente según su horario y no está cancelada
            if (cita.getHorario().isAfter(LocalDateTime.now()) && !cita.isCancelada()) {
                cita.setCancelada(true);
                citaRepository.save(cita);
            } else {
                throw new RuntimeException("No se puede cancelar una cita que ya ha ocurrido o que ya ha sido cancelada");
            }
        } else {
            throw new RuntimeException("Cita no encontrada");
        }
    }

    public Cita editarCita(Long citaId, Cita nuevaCita) {
        Optional<Cita> optionalCitaExistente = citaRepository.findById(citaId);

        if (optionalCitaExistente.isPresent()) {
            Cita citaExistente = optionalCitaExistente.get();

            if (validarCita(nuevaCita)) {
                citaExistente.setConsultorio(nuevaCita.getConsultorio());
                citaExistente.setDoctor(nuevaCita.getDoctor());
                citaExistente.setHorario(nuevaCita.getHorario());
                citaExistente.setNombrePaciente(nuevaCita.getNombrePaciente());

                return citaRepository.save(citaExistente);
            } else {
                throw new RuntimeException("La cita no cumple con las reglas de alta");
            }
        } else {
            throw new RuntimeException("Cita no encontrada");
        }
    }

    private boolean validarCita( Cita cita) {
        if (citaRepository.existsByConsultorioAndHorario(cita.getConsultorio(), cita.getHorario())){
            return false;
        }

        if (citaRepository.existsByDoctorAndHorario(cita.getDoctor(), cita.getHorario())){
            return false;
        }

        LocalDateTime limiteInferior = cita.getHorario().minusDays(2);
        LocalDateTime limiteSuperior = cita.getHorario().plusHours(1);
        if (citaRepository.existsByNombrePacienteAndHorarioBetween(cita.getNombrePaciente(), limiteInferior, limiteSuperior)){
            return false;
        }

        LocalDateTime inicioDia = cita.getHorario().toLocalDate().atStartOfDay();
        LocalDateTime finDia = cita.getHorario().toLocalDate().atTime(23, 59);
        if (citaRepository.countByDoctorAndHorarioBetween(cita.getDoctor(), inicioDia, finDia) >= 8){
            return false;
        }

        return true;
    }
}
