package com.example.bankturnovers.controller;

import com.example.bankturnovers.entity.*;
import com.example.bankturnovers.service.Parsing;
import com.example.bankturnovers.service.SheetLineService;
import com.example.bankturnovers.service.TurnoverSheetsService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {

    private final TurnoverSheetsService turnoverSheetsService;
    private final SheetLineService sheetLineService;

    @Autowired
    MyController(TurnoverSheetsService turnoverSheetsService, SheetLineService sheetLineService) {
        this.turnoverSheetsService = turnoverSheetsService;
        this.sheetLineService = sheetLineService;
    }

    @GetMapping(value = "/parse")
    public ResponseEntity<?> findAllSheets(HttpServletRequest request, HttpServletResponse response) {
        String file_name = "ОСВ для тренинга.xls";
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
            sheetLine.setClassName(class_name);
            sheetLine.setAccounting(list.get(0)+"");
            incomeSaldo.setActive(BigDecimal.valueOf(Double.valueOf(list.get(1)+"")));
            incomeSaldo.setPassive(BigDecimal.valueOf(Double.valueOf(list.get(2)+"")));
            turnovers.setDebit(BigDecimal.valueOf(Double.valueOf(list.get(3)+"")));
            turnovers.setCredit(BigDecimal.valueOf(Double.valueOf(list.get(4)+"")));
            outcomeSaldo.setActive(BigDecimal.valueOf(Double.valueOf(list.get(5)+"")));
            outcomeSaldo.setPassive(BigDecimal.valueOf(Double.valueOf(list.get(6)+"")));

            sheetLine.setIncomeSaldo(incomeSaldo);
            sheetLine.setTurnovers(turnovers);
            sheetLine.setOutcomeSaldo(outcomeSaldo);
            turnoverSheets.add(sheetLine);
            list.clear();
        }
        turnoverSheetsService.create(turnoverSheets);

        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        try {
            response.getWriter().write("File " + file_name + " has been parsed!");       // Write response body.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/BankTurnovers")
    public String index(ModelMap model) {
        model.addAttribute("classes", sheetLineService.getClassCount());
        return "index";
    }

    @GetMapping(value = "/BankTurnovers/class/{id}")
    public String getClass(@PathVariable(name = "id") String id, Model model) {
        List<SheetLine> list = sheetLineService.getClass(" " + id + " ");
        List<String> accounts = sheetLineService.getCounts(id);
        model.addAttribute("sheets", list);
        model.addAttribute("accounts", accounts);
        return "list";
    }

    @GetMapping(value = "/BankTurnovers/classes")
    public String getAllSheets(Model model) {
        List<SheetLine> sheetList = sheetLineService.readAll();

        model.addAttribute("sheets", sheetList);
        return "list";
    }

    @GetMapping(value = "/BankTurnovers/class/{id}/{id2}")
    public String getAccounts(@PathVariable(name = "id2") String id, Model model) {
        List<SheetLine> sheetList = sheetLineService.getAccounts(id);
        model.addAttribute("sheets", sheetList);
        return "list";
    }

}
