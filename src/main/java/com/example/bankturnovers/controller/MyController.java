package com.example.bankturnovers.controller;

import com.example.bankturnovers.entity.TurnoverSheets;
import com.example.bankturnovers.service.TurnoverSheetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {

    private final TurnoverSheetsService turnoverSheetsService;

    @Autowired
    MyController(TurnoverSheetsService turnoverSheetsService) {
        this.turnoverSheetsService = turnoverSheetsService;
    }

    @PostMapping(value = "/placeOrder")
    public ResponseEntity<?> placeTurnoverSheet(@RequestBody TurnoverSheets turnoverSheets) {
        turnoverSheetsService.create(turnoverSheets);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllSheets")
    public ResponseEntity<List<TurnoverSheets>> findAllSheets() {
        List<TurnoverSheets> list = turnoverSheetsService.readAll();
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
