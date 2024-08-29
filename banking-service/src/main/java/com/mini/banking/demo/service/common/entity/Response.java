package com.mini.banking.demo.service.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Response<T> {
    private Date timestamp;
    private boolean status;
    private T payload;
    private String message;
}