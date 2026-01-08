package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, Integer> {
}