package com.luisenrique.openbank.account.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBankAccountRequest {
    private BigDecimal initialBalance;
}
