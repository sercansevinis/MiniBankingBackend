package com.mini.banking.demo.core.delivery.auth;

import com.mini.banking.demo.core.delivery.user.UserDto;

public interface SessionService {
    UserDto getUser();

    UserDto getUserOrAnonymous();

    UserDto getUserOrThrow();

}
