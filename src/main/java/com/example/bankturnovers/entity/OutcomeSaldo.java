package com.example.bankturnovers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

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
    @Column(precision = 25, scale = 8)
    private BigDecimal active;
    @Column(precision = 25, scale = 8)
    private BigDecimal passive;
    @OneToOne(mappedBy = "outcomeSaldo", cascade = CascadeType.ALL)
    private SheetLine sheetLine;
}
