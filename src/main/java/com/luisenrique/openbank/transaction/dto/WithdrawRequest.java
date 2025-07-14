package com.luisenrique.openbank.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequest {
    @NotNull
    private Long accountId;

    @NotNull
    @DecimalMin(value = "0.01", message = "El monto debe ser positivo y mayor a cero")
    private BigDecimal amount;
}

