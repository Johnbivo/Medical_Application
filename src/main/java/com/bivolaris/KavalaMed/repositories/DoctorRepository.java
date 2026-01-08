package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
  }