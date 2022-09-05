package com.example.bankturnovers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "income_saldo")
public class IncomeSaldo {

    @Id
    @GeneratedValue
    @Column(name = "i_id")
    private int id;
    private Double active;
    private Double passive;
    @OneToOne(mappedBy = "incomeSaldo", cascade = CascadeType.ALL)
    private SheetLine sheetLine;
}