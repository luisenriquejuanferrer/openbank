package com.luisenrique.openbank.transaction.controller;

import com.luisenrique.openbank.transaction.dto.DepositRequest;
import com.luisenrique.openbank.transaction.dto.TransferRequest;
import com.luisenrique.openbank.transaction.dto.WithdrawRequest;
import com.luisenrique.openbank.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@Valid @RequestBody DepositRequest request) {
        transactionService.deposit(request.getAccountId(), request.getAmount());
        return ResponseEntity.ok("Depósito exitoso");
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@Valid @RequestBody WithdrawRequest request) {
        transactionService.withdraw(request.getAccountId(), request.getAmount());
        return ResponseEntity.ok("Retiro exitoso");
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequest request) {
        transactionService.transfer(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
        return ResponseEntity.ok("Transferencia exitosa");
    }
}
