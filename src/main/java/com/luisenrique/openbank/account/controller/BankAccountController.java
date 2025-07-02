package com.luisenrique.openbank.account.controller;

import com.luisenrique.openbank.account.dto.CreateBankAccountRequest;
import com.luisenrique.openbank.account.model.BankAccount;
import com.luisenrique.openbank.account.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("/mine")
    public ResponseEntity<List<BankAccount>> getMyAccounts() {
        List<BankAccount> accounts = bankAccountService.getAccountsForCurrentUser();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/create")
    public ResponseEntity<BankAccount> createAccount(@RequestBody CreateBankAccountRequest request) {
        BankAccount createdAccount = bankAccountService.createAccountForCurrentUser(request.getInitialBalance());
        return ResponseEntity.ok(createdAccount);
    }
}
