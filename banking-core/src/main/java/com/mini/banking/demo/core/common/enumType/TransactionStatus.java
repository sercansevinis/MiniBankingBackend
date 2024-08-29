package com.mini.banking.demo.core.common.enumType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TransactionStatus {

    SUCCESSFULL("SUCCESSFULL"),
    FAILED("FAILED"),
    PENDING("PENDING");

    private String displayName;
}
