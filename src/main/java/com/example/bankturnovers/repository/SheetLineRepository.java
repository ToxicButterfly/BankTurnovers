package com.example.bankturnovers.repository;

import com.example.bankturnovers.entity.SheetLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SheetLineRepository extends JpaRepository<SheetLine, Integer> {
    @Query("SELECT line.accounting FROM SheetLine line WHERE line.accounting LIKE CONCAT(?1,'_') ORDER BY line.accounting")
    List<String> findAccountByClass(String i);

    @Query("SELECT COUNT(DISTINCT className) FROM SheetLine")
    int findUniqueClasses();

    List<SheetLine> findSheetLineByClassNameContains(String num);
    List<SheetLine> findSheetLinesByAccountingStartsWith(String num);
}
