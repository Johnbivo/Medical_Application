package com.bivolaris.KavalaMed.repositories;

import com.bivolaris.KavalaMed.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}