package com.example.bankturnovers.dto;

import com.example.bankturnovers.entity.TurnoverSheets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InputRequest {

    private TurnoverSheets turnoverSheets;
}
