package com.example.bankturnovers.service;

import com.example.bankturnovers.entity.TurnoverSheets;
import com.example.bankturnovers.repository.TurnoverSheetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoverSheetsServiceImpl implements TurnoverSheetsService{

    @Autowired
    public TurnoverSheetsRepository turnoverSheetsRepository;

    @Override
    public void create(TurnoverSheets turnoverSheets) { turnoverSheetsRepository.save(turnoverSheets); }

    @Override
    public void saveAll(List<TurnoverSheets> list) { turnoverSheetsRepository.saveAll(list); }

    @Override
    public List<TurnoverSheets> readAll() { return turnoverSheetsRepository.findAll(); }

    @Override
    public TurnoverSheets read(int id) { return turnoverSheetsRepository.findById(id).orElse(new TurnoverSheets()); }

    @Override
    public boolean update(TurnoverSheets turnoverSheets, int id) {
        if(turnoverSheetsRepository.existsById(id)) {
            turnoverSheets.setId(id);
            turnoverSheetsRepository.save(turnoverSheets);
            return true;
        }
        else return false;
    }

    @Override
    public boolean delete(int id) {
        if(turnoverSheetsRepository.existsById(id)) {
            turnoverSheetsRepository.deleteById(id);
            return true;
        }
        else return false;
    }
}
