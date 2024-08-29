package com.mini.banking.demo.core.delivery.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.mini.banking.demo.core.delivery.account.Account;
import com.mini.banking.demo.core.delivery.account.AccountRepository;

import java.util.List;
import java.util.Optional;

@Component
public class TransactionBackEnd {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionDtoConverter transactionDtoConverter;

    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public List<Transaction> getTransactionsByFromAccountId(int fromAccountId) {
        return transactionRepository.findByFromId(fromAccountId);
    }

    public List<Transaction> getTransactionsByToAccountId(int toAccountId) {
        return transactionRepository.findByToId(toAccountId);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(id);
    }

    public Transaction updateTransaction(int id, Transaction transaction) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found with id: " + id);
        }
        transaction.setId(id);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(int id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }

    public Page<TransactionDto> getTransactionsByAccountId(int accountId, Pageable pageable) {
        Page<Transaction> transactionsPage = transactionRepository.findTransactionHistoryByAccountId(accountId,
                pageable);
        return transactionsPage.map(transactionDtoConverter::convertDataToDto);
    }
}
