package com.mini.banking.demo.service.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SessionMember {
    String username;
    @JsonIgnore
    String authToken;
    Date registrationDate;
    Date expiryDate;
}
