package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaryRepository extends JpaRepository<Secretary, Integer> {
}