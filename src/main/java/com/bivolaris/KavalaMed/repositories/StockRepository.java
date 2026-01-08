package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}