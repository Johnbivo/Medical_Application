package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a JOIN FETCH a.patient p JOIN FETCH a.doctor d")
    List<Appointment> findAllWithPatientAndDoctor();

    @Query("SELECT a FROM Appointment a JOIN FETCH a.patient p JOIN FETCH a.doctor d WHERE a.appointmentId = :id")
    Optional<Appointment> findByIdWithPatientAndDoctor(Integer id);

    @Query("SELECT a FROM Appointment a JOIN FETCH a.patient p JOIN FETCH a.doctor d WHERE a.patient.patientId = :patientId")
    List<Appointment> findByPatientId(Integer patientId);
}