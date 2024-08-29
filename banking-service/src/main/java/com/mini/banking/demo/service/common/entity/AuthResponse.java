package com.mini.banking.demo.service.common.entity;

import java.util.Map;

import com.mini.banking.demo.core.delivery.user.UserDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class AuthResponse {
    private UserDto user;
    private String authToken;
    private Map<String, String> dateFormat;
}
