package com.example.bankturnovers.repository;

import com.example.bankturnovers.entity.Turnovers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoversRepository extends JpaRepository<Turnovers, Integer> {
}
