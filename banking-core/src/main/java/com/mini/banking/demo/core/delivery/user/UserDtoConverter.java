package com.mini.banking.demo.core.delivery.user;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mini.banking.demo.core.delivery.account.Account;
import com.mini.banking.demo.core.delivery.account.AccountDto;
import com.mini.banking.demo.core.delivery.account.AccountDtoConverter;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UserDtoConverter {
    @Autowired
    private AccountDtoConverter accountDtoConverter;
    @Autowired
    private UserRepository userRepository;

    public UserDto convertDataToDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        // Avoid setting the password in DTO for security reasons
        userDto.setEmail(user.getEmail());
        if (user.getAccounts() != null) {
            Set<AccountDto> accountDtos = user.getAccounts().stream()
                    .map(accountDtoConverter::convertDataToDto)
                    .collect(Collectors.toSet());
            userDto.setAccounts(accountDtos);
        }
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }

    public User convertDtoToData(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword()); // Handle password encryption
        user.setEmail(userDto.getEmail());

        if (userDto.getAccounts() != null) {
            Set<Account> accounts = userDto.getAccounts().stream()
                    .map(accountDto -> {
                        Account accountEntity = accountDtoConverter.convertDtoToData(accountDto);
                        accountEntity.setUser(user); // Set the user reference
                        return accountEntity;
                    })
                    .collect(Collectors.toSet());
            user.setAccounts(accounts);
        }

        if (userDto.getId() == 0) { // New user
            user.setCreatedAt(LocalDateTime.now());
        } else { // Updating existing user
            User existingUser = userRepository.findById(userDto.getId()).orElseThrow(null);
            user.setCreatedAt(existingUser.getCreatedAt());
            user.setUpdatedAt(LocalDateTime.now()); // Set the new updatedAt
        }
        return user;
    }
}
