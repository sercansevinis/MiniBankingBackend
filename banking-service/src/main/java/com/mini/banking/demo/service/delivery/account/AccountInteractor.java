package com.mini.banking.demo.service.delivery.account;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mini.banking.demo.core.delivery.account.AccountDto;
import com.mini.banking.demo.core.delivery.account.AccountService;
import com.mini.banking.demo.service.common.api.InteractorBase;
import com.mini.banking.demo.service.common.entity.Response;
import com.mini.banking.demo.service.common.presenter.CommonObjectPresenter;
import com.mini.banking.demo.service.common.presenter.CommonVoidPresenter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountInteractor implements InteractorBase<AccountDto> {
    private final CommonObjectPresenter<AccountDto> accountPresenter;
    private final CommonVoidPresenter voidPresenter;
    private final AccountService service;

    public ResponseEntity<Response<AccountDto>> search(String name, String number) {
        AccountDto accounts = service.searchAccount(name, number);
        return accountPresenter.prepareSuccessView(accounts);
    }

    @Override
    public ResponseEntity<Response<AccountDto>> getById(int id) {
        AccountDto dto = service.getAccountById(id);
        return accountPresenter.prepareSuccessView(dto);
    }

    @Override
    public ResponseEntity<Response<Void>> create(AccountDto dto) {
        service.createAccount(dto);
        return voidPresenter.prepareSuccessView(null);
    }

    @Override
    public ResponseEntity<Response<Void>> update(AccountDto dto) {
        service.updateAccount(dto.getId(), dto);
        return voidPresenter.prepareSuccessView(null);
    }

    @Override
    public ResponseEntity<Response<Void>> delete(int id) {
        service.deleteAccount(id);
        return voidPresenter.prepareSuccessView(null);
    }

}
