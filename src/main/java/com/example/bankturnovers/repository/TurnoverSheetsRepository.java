package com.example.bankturnovers.repository;

import com.example.bankturnovers.entity.TurnoverSheets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoverSheetsRepository extends JpaRepository<TurnoverSheets, Integer> {
}
