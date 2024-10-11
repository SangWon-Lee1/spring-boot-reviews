package com.example.springbootreviews.bank.model;

import com.example.springbootreviews.bank.entity.Account;
import com.example.springbootreviews.bank.entity.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {
    private Account fromAccountId;
    private Account toAccountId;
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}
