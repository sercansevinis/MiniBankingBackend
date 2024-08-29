package com.mini.banking.demo.service.delivery.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mini.banking.demo.core.delivery.user.UserDto;
import com.mini.banking.demo.core.delivery.user.UserService;
import com.mini.banking.demo.service.common.api.InteractorBase;
import com.mini.banking.demo.service.common.entity.Response;
import com.mini.banking.demo.service.common.presenter.CommonObjectPresenter;
import com.mini.banking.demo.service.common.presenter.CommonVoidPresenter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserInteractor implements InteractorBase<UserDto> {

    private final UserService service;
    private final CommonVoidPresenter voidPresenter;
    private final CommonObjectPresenter<UserDto> userPresenter;

    public ResponseEntity<Response<Void>> update(UserDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    public ResponseEntity<Response<Void>> delete(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Response<UserDto>> getById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public ResponseEntity<Response<Void>> create(UserDto dto) {
        service.create(dto);
        return voidPresenter.prepareSuccessView(null);
    }
}
