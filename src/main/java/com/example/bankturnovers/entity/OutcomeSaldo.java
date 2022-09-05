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
@Table(name = "outcome_saldo")
public class OutcomeSaldo {

    @Id
    @GeneratedValue
    @Column(name = "o_id")
    private int id;
    private Double active;
    private Double passive;
    @OneToOne(mappedBy = "outcomeSaldo", cascade = CascadeType.ALL)
    private SheetLine sheetLine;
}
