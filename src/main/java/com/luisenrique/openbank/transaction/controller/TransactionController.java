package com.luisenrique.openbank.transaction.controller;

import com.luisenrique.openbank.transaction.dto.DepositRequest;
import com.luisenrique.openbank.transaction.dto.TransferRequest;
import com.luisenrique.openbank.transaction.dto.WithdrawRequest;
import com.luisenrique.openbank.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest request) {
        transactionService.deposit(request.getAccountId(), request.getAmount());
        return ResponseEntity.ok("Depósito exitoso");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawRequest request) {
        transactionService.withdraw(request.getAccountId(), request.getAmount());
        return ResponseEntity.ok("Retiro exitoso");
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        transactionService.transfer(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
        return ResponseEntity.ok("Transferencia exitosa");
    }
}
