package com.mini.banking.demo.service.delivery.user;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mini.banking.demo.core.delivery.user.UserDto;
import com.mini.banking.demo.service.common.entity.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserInteractor interactor;

    @PostMapping("/api/users/register")
    public ResponseEntity<Response<Void>> create(@RequestBody UserDto dto) {
        return interactor.create(dto);
    }
}
