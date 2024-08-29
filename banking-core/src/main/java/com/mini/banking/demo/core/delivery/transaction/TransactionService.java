package com.mini.banking.demo.core.delivery.transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.mini.banking.demo.core.delivery.account.Account;

public interface TransactionService {

    Optional<Account> getAccountById(int id);

    List<TransactionDto> getTransactionsByFromAccountId(int fromAccountId);

    List<TransactionDto> getTransactionsByToAccountId(int toAccountId);

    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionDto getTransactionById(int id);

    TransactionDto updateTransaction(int id, TransactionDto transactionDto);

    void deleteTransaction(int id);

    TransactionDto initiateMoneyTransfer(int fromAccountId, int toAccountId, BigDecimal amount);

    List<TransactionDto> getTransactionHistory(int accountId);
}
