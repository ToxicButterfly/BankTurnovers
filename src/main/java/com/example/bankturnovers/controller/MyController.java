package com.example.bankturnovers.controller;

import com.example.bankturnovers.entity.*;
import com.example.bankturnovers.service.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    private final TurnoverSheetsService turnoverSheetsService;
//    private final IncomeSaldoService incomeSaldoService;
//    private final TurnoversService turnoversService;
//    private final OutcomeSaldoService outcomeSaldoService;
    private final SheetLineService sheetLineService;

    @Autowired
    MyController(TurnoverSheetsService turnoverSheetsService, SheetLineService sheetLineService) {
        this.turnoverSheetsService = turnoverSheetsService;
//        this.incomeSaldoService = incomeSaldoService;
//        this.turnoversService = turnoversService;
//        this.outcomeSaldoService = outcomeSaldoService;
        this.sheetLineService = sheetLineService;
    }

    @PostMapping(value = "/placeOrder")
    public ResponseEntity<?> placeTurnoverSheet() {
        TurnoverSheets turnoverSheets = new TurnoverSheets();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/parse")
    public ResponseEntity<List<TurnoverSheets>> findAllSheets() {
        HSSFWorkbook wb = Parsing.getFile();

        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        //evaluating cell type
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        Row row;
        ArrayList list = new ArrayList();
        //getting bank name
        String bank_name = sheet.getRow(3).getCell(0).getStringCellValue();
        //getting period time for turnover statements

        String period = sheet.getRow(2).getCell(0).getStringCellValue();
        String start_date, end_date;
        start_date = period.substring(12,22);
        end_date = period.substring(26,36);
        TurnoverSheets turnoverSheets = new TurnoverSheets();
        turnoverSheets.setBank_name(bank_name);
        turnoverSheets.setStart_date(start_date);
        turnoverSheets.setEnd_date(end_date);
        String class_name = null;
        for (int i = 8; i < sheet.getPhysicalNumberOfRows()-1; i++) {
            SheetLine sheetLine = new SheetLine();
            IncomeSaldo incomeSaldo = new IncomeSaldo();
            Turnovers turnovers = new Turnovers();
            OutcomeSaldo outcomeSaldo = new OutcomeSaldo();
            row = sheet.getRow(i);
            for (Cell cell : row)    //iteration over cell using for each loop
            {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case NUMERIC:   //field that represents numeric cell type
                        cell.setCellType(CellType.STRING);
                        list.add(cell.getStringCellValue());
                        // System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                    case STRING:    //field that represents string cell type
                        //getting the value of the cell as a string
                        list.add(cell.getStringCellValue());
                        //   System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                }
            }
            if ((list.get(0) + "").startsWith("КЛАСС")) {
                class_name = (list.get(0) + "");
                list.clear();
                continue;
            }
            sheetLine.setClass_name(class_name);
            sheetLine.setAccounting(list.get(0)+"");
            incomeSaldo.setActive(Double.parseDouble(list.get(1)+""));
            incomeSaldo.setPassive(Double.parseDouble(list.get(2)+""));
            turnovers.setDebit(Double.parseDouble(list.get(3)+""));
            turnovers.setCredit(Double.parseDouble(list.get(4)+""));
            outcomeSaldo.setActive(Double.parseDouble(list.get(5)+""));
            outcomeSaldo.setPassive(Double.parseDouble(list.get(6)+""));
            sheetLine.setIncomeSaldo(incomeSaldo);
            sheetLine.setTurnovers(turnovers);
            sheetLine.setOutcomeSaldo(outcomeSaldo);
            turnoverSheets.add(sheetLine);
            list.clear();
        }
        turnoverSheetsService.create(turnoverSheets);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
