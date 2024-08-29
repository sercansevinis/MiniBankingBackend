package com.mini.banking.demo.core.delivery.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByFromId(int fromAccountId);

    List<Transaction> findByToId(int toAccountId);
}
