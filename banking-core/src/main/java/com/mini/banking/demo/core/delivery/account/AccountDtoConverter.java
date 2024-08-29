package com.mini.banking.demo.core.delivery.account;

import org.springframework.stereotype.Component;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class AccountDtoConverter {

    public AccountDto convertDataToDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setNumber(account.getNumber());
        accountDto.setName(account.getName());
        accountDto.setBalance(account.getBalance());
        accountDto.setCreatedAt(account.getCreatedAt());
        accountDto.setUpdatedAt(account.getUpdatedAt());

        if (account.getUser() != null) {
            accountDto.setUserId(account.getUser().getId());
        }

        return accountDto;
    }

    public Account convertDtoToData(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }

        Account account = new Account();
        account.setId(accountDto.getId());
        account.setNumber(accountDto.getNumber());
        account.setName(accountDto.getName());
        account.setBalance(accountDto.getBalance());
        account.setCreatedAt(accountDto.getCreatedAt());
        account.setUpdatedAt(accountDto.getUpdatedAt());

        return account;
    }
}
