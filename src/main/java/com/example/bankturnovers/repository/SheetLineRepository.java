package com.example.bankturnovers.repository;

import com.example.bankturnovers.entity.SheetLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheetLineRepository extends JpaRepository<SheetLine, Integer> {
}
