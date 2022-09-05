package com.example.bankturnovers.service;

import com.example.bankturnovers.entity.TurnoverSheets;

import java.util.List;

public interface TurnoverSheetsService {

    void create(TurnoverSheets turnoverSheets);
    void saveAll(List<TurnoverSheets> list);
    List<TurnoverSheets> readAll();
    TurnoverSheets read(int id);
    boolean update(TurnoverSheets turnoverSheets, int id);
    boolean delete(int id);

}
