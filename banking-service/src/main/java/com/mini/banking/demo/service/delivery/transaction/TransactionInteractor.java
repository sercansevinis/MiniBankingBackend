package com.mini.banking.demo.service.delivery.transaction;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mini.banking.demo.core.delivery.account.AccountDto;
import com.mini.banking.demo.core.delivery.transaction.TransactionDto;
import com.mini.banking.demo.core.delivery.transaction.TransactionService;
import com.mini.banking.demo.service.common.entity.Response;
import com.mini.banking.demo.service.common.presenter.CommonListPresenter;
import com.mini.banking.demo.service.common.presenter.CommonObjectPresenter;
import com.mini.banking.demo.service.common.presenter.CommonVoidPresenter;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionInteractor {

    private final TransactionService transactionService;
    private final CommonObjectPresenter<TransactionDto> transactionPresenter;
    private final CommonListPresenter<TransactionDto> transactionListPresenter;

    public ResponseEntity<Response<TransactionDto>> initiateMoneyTransfer(int fromAccountId, int toAccountId,
            BigDecimal amount) {
        TransactionDto transactionDto = transactionService.initiateMoneyTransfer(fromAccountId, toAccountId, amount);
        return transactionPresenter.prepareSuccessView(transactionDto);
    }

    public Page<TransactionDto> getTransactionHistory(int accountId, int page, int size) {
        return transactionService.getTransactionHistory(accountId, page, size);
    }
}
