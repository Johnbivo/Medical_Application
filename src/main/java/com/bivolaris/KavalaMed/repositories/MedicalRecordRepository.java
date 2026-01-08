package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {
}