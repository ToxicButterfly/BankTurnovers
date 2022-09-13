package com.example.bankturnovers.service;

import com.example.bankturnovers.entity.IncomeSaldo;
import com.example.bankturnovers.entity.SheetLine;

import java.util.List;

public interface SheetLineService {
    void create(SheetLine sheetLine);
    List<SheetLine> readAll();
    SheetLine read(int id);
    boolean update(SheetLine sheetLine, int id);
    boolean delete(int id);
    List<String> getCounts(String i);
    int getClassCount();

    List<SheetLine> getClass(String num);

    List<SheetLine> getAccounts(String num);
}
