package com.neondomain.neon.repository;

import com.neondomain.neon.entity.Cita;
import com.neondomain.neon.entity.Consultorio;
import com.neondomain.neon.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    boolean existsByConsultorioAndHorario(Consultorio consultorio, LocalDateTime horario);

    boolean existsByDoctorAndHorario(Doctor doctor, LocalDateTime horario);

    boolean existsByNombrePacienteAndHorarioBetween(String nombrePaciente, LocalDateTime inicio, LocalDateTime fin);

    long countByDoctorAndHorarioBetween(Doctor doctor, LocalDateTime inicio, LocalDateTime fin);

    @Query("SELECT c FROM Cita c WHERE c.horario BETWEEN :inicio AND :fin AND c.consultorio = :consultorio AND c.doctor = :doctor")
    List<Cita> findByHorarioBetweenAndConsultorioAndDoctor(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin,
            @Param("consultorio") Consultorio consultorio,
            @Param("doctor") Doctor doctor
    );
}
