package com.luisenrique.openbank.account.repository;

import com.luisenrique.openbank.account.model.BankAccount;
import com.luisenrique.openbank.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByOwner(User owner);
}
