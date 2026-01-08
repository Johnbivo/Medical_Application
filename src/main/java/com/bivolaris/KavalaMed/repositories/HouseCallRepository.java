package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.HouseCall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseCallRepository extends JpaRepository<HouseCall, Integer> {
}