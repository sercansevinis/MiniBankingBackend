package com.mini.banking.demo.core.delivery.account;

import java.util.List;

public interface AccountService {
    AccountDto searchAccount(String number, String name);

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(int id);

    AccountDto updateAccount(int id, AccountDto accountDto);

    void deleteAccount(int id);
}
