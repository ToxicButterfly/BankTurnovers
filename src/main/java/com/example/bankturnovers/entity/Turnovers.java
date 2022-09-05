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
@Table(name = "turnovers")
public class Turnovers {

    @Id
    @GeneratedValue
    @Column(name = "t_id")
    private int id;
    private Double debit;
    private Double credit;
    @OneToOne(mappedBy = "turnovers", cascade = CascadeType.ALL)
    private SheetLine sheetLine;
}