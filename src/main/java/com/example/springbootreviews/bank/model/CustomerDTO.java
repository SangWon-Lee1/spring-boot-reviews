package com.example.springbootreviews.bank.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDTO {
    private String customerId;
    private String customerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
