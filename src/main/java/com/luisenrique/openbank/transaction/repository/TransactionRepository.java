package com.luisenrique.openbank.transaction.repository;

import com.luisenrique.openbank.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
