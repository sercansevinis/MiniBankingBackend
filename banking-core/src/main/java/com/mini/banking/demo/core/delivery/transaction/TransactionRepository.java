package com.mini.banking.demo.core.delivery.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByFromId(int fromAccountId);

    List<Transaction> findByToId(int toAccountId);

    // Method to find transactions where the account is either the 'from' or 'to'
    // account
    @Query("SELECT t FROM Transaction t WHERE t.from.id = :accountId OR t.to.id = :accountId")
    Page<Transaction> findTransactionHistoryByAccountId(@Param("accountId") int accountId, Pageable pageable);
}
