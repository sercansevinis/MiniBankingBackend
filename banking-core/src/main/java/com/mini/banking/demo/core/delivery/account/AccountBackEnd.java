package com.mini.banking.demo.core.delivery.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountBackEnd {

    @Autowired
    private AccountRepository accountRepository;

    public Account searchAccount(String number, String name) {
        return accountRepository.findByNumberContainingIgnoreCaseOrNameContainingIgnoreCase(number, name);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public Account updateAccount(int id, Account account) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found with id: " + id);
        }
        account.setId(id);
        return accountRepository.save(account);
    }

    public void deleteAccount(int id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found with id: " + id);
        }
        accountRepository.deleteById(id);
    }
}
