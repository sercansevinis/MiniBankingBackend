package com.mini.banking.demo.service.delivery.transaction;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mini.banking.demo.core.delivery.transaction.TransactionDto;
import com.mini.banking.demo.service.common.entity.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionInteractor transactionInteractor;

    @PostMapping("/transfer")
    public ResponseEntity<Response<TransactionDto>> initiateMoneyTransfer(
            @RequestParam int fromAccountId,
            @RequestParam int toAccountId,
            @RequestParam BigDecimal amount) {
        return transactionInteractor.initiateMoneyTransfer(fromAccountId, toAccountId, amount);
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<Page<TransactionDto>> getTransactionHistory(
            @PathVariable int accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<TransactionDto> transactions = transactionInteractor.getTransactionHistory(accountId, page, size);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
