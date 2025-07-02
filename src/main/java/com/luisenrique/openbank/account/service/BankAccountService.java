package com.luisenrique.openbank.account.service;

import com.luisenrique.openbank.account.model.BankAccount;
import com.luisenrique.openbank.account.repository.BankAccountRepository;
import com.luisenrique.openbank.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAccountsForCurrentUser() {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return bankAccountRepository.findByOwner(currentUser);
    }

    public BankAccount createAccountForCurrentUser(BigDecimal initialBalance) {
        User currentUser = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        String generatedAccountNumber = generateRandomAccountNumber();

        BankAccount account = BankAccount.builder()
                .accountNumber(generatedAccountNumber)
                .balance(initialBalance != null ? initialBalance : BigDecimal.ZERO)
                .owner(currentUser)
                .build();

        return bankAccountRepository.save(account);
    }

    // Generador simple de número de cuenta (simulado)
    private String generateRandomAccountNumber() {
        return "ES" + (long) (Math.random() * 1_000_000_000L);
    }
}
