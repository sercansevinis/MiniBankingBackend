package com.mini.banking.demo.core.delivery.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mini.banking.demo.core.common.enumType.TransactionStatus;
import com.mini.banking.demo.core.delivery.account.Account;
import com.mini.banking.demo.core.delivery.account.AccountBackEnd;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionBackEnd transactionBackEnd;
    private final AccountBackEnd accountBackEnd;

    private final TransactionDtoConverter transactionDtoConverter;

    @Override
    public Optional<Account> getAccountById(int id) {
        return transactionBackEnd.getAccountById(id);
    }

    @Override
    public List<TransactionDto> getTransactionsByFromAccountId(int fromAccountId) {
        List<Transaction> transactions = transactionBackEnd.getTransactionsByFromAccountId(fromAccountId);
        return transactions.stream()
                .map(transactionDtoConverter::convertDataToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> getTransactionsByToAccountId(int toAccountId) {
        List<Transaction> transactions = transactionBackEnd.getTransactionsByToAccountId(toAccountId);
        return transactions.stream()
                .map(transactionDtoConverter::convertDataToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionDtoConverter.convertDtoToData(transactionDto);
        Transaction savedTransaction = transactionBackEnd.createTransaction(transaction);
        return transactionDtoConverter.convertDataToDto(savedTransaction);
    }

    @Override
    public TransactionDto getTransactionById(int id) {
        Optional<Transaction> transaction = transactionBackEnd.getTransactionById(id);
        return transaction.map(transactionDtoConverter::convertDataToDto)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    @Override
    public TransactionDto updateTransaction(int id, TransactionDto transactionDto) {
        Transaction transaction = transactionDtoConverter.convertDtoToData(transactionDto);
        Transaction updatedTransaction = transactionBackEnd.updateTransaction(id, transaction);
        return transactionDtoConverter.convertDataToDto(updatedTransaction);
    }

    @Override
    public void deleteTransaction(int id) {
        transactionBackEnd.deleteTransaction(id);
    }

    @Override
    public TransactionDto initiateMoneyTransfer(int fromAccountId, int toAccountId, BigDecimal amount) {
        Account fromAccount = accountBackEnd.getAccountById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("From Account not found with id: " + fromAccountId));
        Account toAccount = accountBackEnd.getAccountById(toAccountId)
                .orElseThrow(() -> new RuntimeException("To Account not found with id: " + toAccountId));

        Transaction transaction = new Transaction();
        transaction.setFrom(fromAccount);
        transaction.setTo(toAccount);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.SUCCESSFULL); // Or based on logic

        Transaction savedTransaction = transactionBackEnd.createTransaction(transaction);
        return transactionDtoConverter.convertDataToDto(savedTransaction);
    }

    @Override
public Page<TransactionDto> getTransactionHistory(int accountId, int page, int size) {
    // Use a pageable object to define the page and size
    Pageable pageable = PageRequest.of(page, size);

    // Retrieve a paginated list of transactions where the account is either the 'from' or 'to' account
    Page<Transaction> transactionsPage = transactionBackEnd.getTransactionsByAccountId(accountId, pageable);

    // Convert the Page of Transaction entities to a Page of TransactionDto objects
    return transactionsPage.map(transactionDtoConverter::convertDataToDto);
}
}
}
