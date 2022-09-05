package com.example.bankturnovers.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "turnover_sheets")
public class TurnoverSheets {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "t_id")
    private int id;
    @Column
    private String start_date;
    @Column
    private String end_date;
    @Column
    private String bank_name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "turnover_sheet_id", nullable = false)
    private List<SheetLine> turnover_sheet_element = new ArrayList<>();

    public void add(SheetLine sheetLine) { turnover_sheet_element.add(sheetLine); }

}

