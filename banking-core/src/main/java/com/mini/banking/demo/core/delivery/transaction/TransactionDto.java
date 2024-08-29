package com.mini.banking.demo.core.delivery.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.mini.banking.demo.core.common.enumType.TransactionStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {
    private int id;
    private int fromAccountId;
    private int toAccountId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private TransactionStatus status; // Or use TransactionStatus enum if preferred

}
