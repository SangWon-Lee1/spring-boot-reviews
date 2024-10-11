package com.example.springbootreviews.bank.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDTO {
    private String accountId;
    private String customerId;
    private BigDecimal balance;
    private LocalDateTime createdAt;
}
