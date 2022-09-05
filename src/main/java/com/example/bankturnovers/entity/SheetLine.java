package com.example.bankturnovers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="turnover_sheet_elements")
public class SheetLine {

    @Id
    @GeneratedValue
    @Column(name="sid")
    private int id;
    @Column
    private String accounting;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "income_saldo_id")
    private IncomeSaldo incomeSaldo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "turnovers_id")
    private Turnovers turnovers;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "outcome_saldo_id")
    private OutcomeSaldo outcomeSaldo;
    @Column
    private String class_name;

}
