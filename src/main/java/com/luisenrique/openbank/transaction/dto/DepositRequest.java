package com.luisenrique.openbank.transaction.dto;

import lombok.Data;

@Data
public class DepositRequest {
    private Long accountId;
    private Double amount;
}
