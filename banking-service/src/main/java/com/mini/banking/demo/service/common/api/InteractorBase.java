package com.mini.banking.demo.service.common.api;

import org.springframework.http.ResponseEntity;

import com.mini.banking.demo.service.common.entity.Response;

public interface InteractorBase<T> {

    ResponseEntity<Response<T>> getById(int id);

    ResponseEntity<Response<Void>> create(T dto);

    ResponseEntity<Response<Void>> update(T dto);

    ResponseEntity<Response<Void>> delete(int id);
}