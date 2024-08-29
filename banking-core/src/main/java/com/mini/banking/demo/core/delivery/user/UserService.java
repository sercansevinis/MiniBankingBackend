package com.mini.banking.demo.core.delivery.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mini.banking.demo.core.common.crud.ServiceBase;

public interface UserService extends ServiceBase<UserDto> {
    UserDto findByUsername(String username);
}
