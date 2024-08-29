package com.mini.banking.demo.service.delivery.account;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mini.banking.demo.core.delivery.account.AccountDto;
import com.mini.banking.demo.core.delivery.account.AccountService;
import com.mini.banking.demo.service.common.entity.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountInteractor accountInteractor;

    @PostMapping
    public ResponseEntity<Response<Void>> createAccount(@RequestBody AccountDto accountDto) {
        return accountInteractor.create(accountDto);
    }

    @PostMapping("/search")
    public ResponseEntity<Response<AccountDto>> searchAccounts(@RequestParam(required = false) String name,
            @RequestParam(required = false) String number) {
        return accountInteractor.search(name, number);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Void>> updateAccount(@PathVariable int id,
            @RequestBody AccountDto accountDto) {
        return accountInteractor.update(accountDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteAccount(@PathVariable int id) {
        return accountInteractor.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<AccountDto>> getAccount(@PathVariable int id) {
        return accountInteractor.getById(id);
    }
}