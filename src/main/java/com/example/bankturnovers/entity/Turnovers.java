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
@Table(name = "turnovers")
public class Turnovers {
    @Id
    @GeneratedValue
    @Column(name = "t_id")
    private int id;
    @Column(precision = 25, scale = 8)
    private BigDecimal debit;
    @Column(precision = 25, scale = 8)
    private BigDecimal credit;
    @OneToOne(mappedBy = "turnovers", cascade = CascadeType.ALL)
    private SheetLine sheetLine;
}