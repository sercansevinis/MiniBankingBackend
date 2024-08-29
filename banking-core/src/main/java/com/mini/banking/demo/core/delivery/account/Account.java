package com.mini.banking.demo.core.delivery.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mini.banking.demo.core.delivery.user.User;

import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "Account", indexes = {
        @Index(name = "INX_ACCOUNT_ID", columnList = "id")
})
@NoArgsConstructor
public class Account {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or GenerationType.AUTO for auto-generation
    private int id;

    @Column(name = "Number")
    private String number;

    @Column(name = "Name")
    private String name;

    @Column(name = "Balance")
    private BigDecimal balance;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
