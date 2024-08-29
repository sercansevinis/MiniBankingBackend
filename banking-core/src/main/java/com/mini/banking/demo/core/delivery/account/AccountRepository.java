package com.mini.banking.demo.core.delivery.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByNumberContainingIgnoreCaseOrNameContainingIgnoreCase(String number, String name);
}
