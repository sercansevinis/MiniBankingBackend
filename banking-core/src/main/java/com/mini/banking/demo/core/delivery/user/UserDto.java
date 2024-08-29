package com.mini.banking.demo.core.delivery.user;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mini.banking.demo.core.delivery.account.Account;
import com.mini.banking.demo.core.delivery.account.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserDto {

    @Min(0)
    private int id;

    @Size(max = 20)
    private String username;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,150}$", message = "Password must contains one lower case letter, one upper case letter, one digit, minimum 6 length and no spaces")
    @Size(max = 150)
    @ToString.Exclude
    @NotBlank
    private String password;

    // RFC 5322 Email standard
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Size(max = 40)
    private String email;

    private Set<AccountDto> accounts;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
