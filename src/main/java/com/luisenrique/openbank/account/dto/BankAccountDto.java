package com.luisenrique.openbank.account.dto;

import com.luisenrique.openbank.user.dto.UserDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountDto {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private UserDto owner;
}

