package com.example.bankturnovers.service;

import com.example.bankturnovers.entity.SheetLine;
import com.example.bankturnovers.repository.SheetLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SheetLineServiceImpl implements SheetLineService{

    @Autowired
    public SheetLineRepository sheetLineRepository;

    @Override
    public void create(SheetLine sheetLine) { sheetLineRepository.save(sheetLine); }

    @Override
    public List<SheetLine> readAll() { return sheetLineRepository.findAll(); }

    @Override
    public SheetLine read(int id) { return sheetLineRepository.findById(id).orElse(new SheetLine()); }

    @Override
    public boolean update(SheetLine sheetLine, int id) {
        if(sheetLineRepository.existsById(id)) {
            sheetLine.setId(id);
            sheetLineRepository.save(sheetLine);
            return true;
        }
        else return false;
    }

    @Override
    public boolean delete(int id) {
        if(sheetLineRepository.existsById(id)) {
            sheetLineRepository.deleteById(id);
            return true;
        }
        else return false;
    }

    @Override
    public List<String> getCounts(String i) { return sheetLineRepository.findAccountByClass(i); }

    @Override
    public int getClassCount() { return sheetLineRepository.findUniqueClasses(); }

    @Override
    public List<SheetLine> getClass(String num) { return sheetLineRepository.findSheetLineByClassNameContains(num); }

    @Override
    public List<SheetLine> getAccounts(String num) { return sheetLineRepository.findSheetLinesByAccountingStartsWith(num); }
}
