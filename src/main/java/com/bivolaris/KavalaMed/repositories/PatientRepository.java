package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}