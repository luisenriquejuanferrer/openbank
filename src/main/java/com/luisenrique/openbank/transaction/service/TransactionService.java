package com.luisenrique.openbank.transaction.service;

import com.luisenrique.openbank.account.model.BankAccount;
import com.luisenrique.openbank.account.repository.BankAccountRepository;
import com.luisenrique.openbank.transaction.model.Transaction;
import com.luisenrique.openbank.transaction.model.TransactionType;
import com.luisenrique.openbank.transaction.repository.TransactionRepository;
import com.luisenrique.openbank.user.model.User;
import com.luisenrique.openbank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final BankAccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final UserRepository userRepo; // necesario para guardar quién hizo la operación

    public void deposit(Long accountId, Double amount) {
        BankAccount account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
        accountRepo.save(account);

        Transaction tx = Transaction.builder()
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .type(TransactionType.DEPOSIT)
                .toAccount(account)
                .user(account.getOwner()) // o puedes pasar el ID por parámetro
                .build();
        transactionRepo.save(tx);
    }

    public void withdraw(Long accountId, Double amount) {
        BankAccount account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        if (account.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0)
            throw new RuntimeException("Fondos insuficientes");

        account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(amount)));
        accountRepo.save(account);

        Transaction tx = Transaction.builder()
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .type(TransactionType.WITHDRAW)
                .fromAccount(account)
                .user(account.getOwner())
                .build();
        transactionRepo.save(tx);
    }

    public void transfer(Long fromAccountId, Long toAccountId, Double amount) {
        BankAccount fromAccount = accountRepo.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));
        BankAccount toAccount = accountRepo.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        if (fromAccount.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0)
            throw new RuntimeException("Fondos insuficientes");

        fromAccount.setBalance(fromAccount.getBalance().subtract(BigDecimal.valueOf(amount)));
        toAccount.setBalance(toAccount.getBalance().add(BigDecimal.valueOf(amount)));

        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);

        Transaction tx = Transaction.builder()
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .type(TransactionType.TRANSFER)
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .user(fromAccount.getOwner())
                .build();
        transactionRepo.save(tx);
    }
}
