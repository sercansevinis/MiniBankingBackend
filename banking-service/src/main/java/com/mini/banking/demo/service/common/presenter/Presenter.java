package com.mini.banking.demo.service.common.presenter;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.mini.banking.demo.service.common.entity.Response;

@Service
public abstract class Presenter<T> {
    protected MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public ResponseEntity<Response<T>> prepareSuccessView(T t) {
        Response<T> response = Response.<T>builder()
                .status(true)
                .timestamp(new Date())
                .payload(t)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<Response<T>> prepareSuccessView(T t, String messageCode) {
        return prepareSuccessView(t, messageCode, (String) null);
    }

    public ResponseEntity<Response<T>> prepareSuccessView(T t, String messageCode, Object... messageArgs) {
        Locale locale = LocaleContextHolder.getLocale();

        Response<T> response = Response.<T>builder()
                .status(true)
                .timestamp(new Date())
                .payload(t)
                .message(messageSource.getMessage(messageCode, messageArgs, locale))
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    public void prepareFailedView(String msg) {
        throw new RestClientException(msg);
    }

}
