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
    private int accounting;
}
