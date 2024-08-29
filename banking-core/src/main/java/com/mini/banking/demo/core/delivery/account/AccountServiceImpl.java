package com.mini.banking.demo.core.delivery.account;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountBackEnd accountBackEnd;
    private final AccountDtoConverter accountDtoConverter;

    @Override
    public AccountDto searchAccount(String number, String name) {
        Account account = accountBackEnd.searchAccount(number, name);
        return accountDtoConverter.convertDataToDto(account);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = accountDtoConverter.convertDtoToData(accountDto);
        Account savedAccount = accountBackEnd.createAccount(account);
        return accountDtoConverter.convertDataToDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(int id) {
        Optional<Account> account = accountBackEnd.getAccountById(id);
        return account.map(accountDtoConverter::convertDataToDto)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    @Override
    public AccountDto updateAccount(int id, AccountDto accountDto) {
        Account account = accountDtoConverter.convertDtoToData(accountDto);
        Account updatedAccount = accountBackEnd.updateAccount(id, account);
        return accountDtoConverter.convertDataToDto(updatedAccount);
    }

    @Override
    public void deleteAccount(int id) {
        accountBackEnd.deleteAccount(id);
    }

}
