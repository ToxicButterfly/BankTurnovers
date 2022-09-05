package com.example.bankturnovers.service;

import com.example.bankturnovers.entity.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parsing {


    public static HSSFWorkbook getFile() {
        FileInputStream fis = null;
        String str = "ОСВ для тренинга.xls";
        try {
            //put your own path here!!!
            fis = new FileInputStream(new File("C:\\Spring\\BankTurnovers", str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
}
