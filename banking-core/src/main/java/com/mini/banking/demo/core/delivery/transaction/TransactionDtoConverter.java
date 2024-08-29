package com.mini.banking.demo.core.delivery.transaction;

import org.springframework.stereotype.Component;

import com.mini.banking.demo.core.common.enumType.TransactionStatus;
import com.mini.banking.demo.core.delivery.account.Account;
import com.mini.banking.demo.core.delivery.account.AccountRepository;
import com.mini.banking.demo.core.delivery.account.AccountService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionDtoConverter {

    private final AccountRepository accountRepository;

    public TransactionDto convertDataToDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setFromAccountId(transaction.getFrom().getId());
        transactionDto.setToAccountId(transaction.getTo().getId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionDate(transaction.getTransactionDate());
        transactionDto.setStatus(transaction.getStatus()); // Or use the enum directly if preferred

        return transactionDto;
    }

    public Transaction convertDtoToData(TransactionDto transactionDto) {
        if (transactionDto == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionDate(transactionDto.getTransactionDate());

        Account fromAccount = accountRepository.findById(transactionDto.getFromAccountId()).orElseThrow(
                () -> new RuntimeException("Account not found with id: " + transactionDto.getToAccountId()));
        Account toAccount = accountRepository.findById(transactionDto.getToAccountId())
                .orElseThrow(
                        () -> new RuntimeException("Account not found with id: " + transactionDto.getToAccountId()));

        transaction.setFrom(fromAccount);
        transaction.setTo(toAccount);

        return transaction;
    }
}
