package com.example.springbootreviews.bank.controller;

import com.example.springbootreviews.bank.entity.TransactionType;
import com.example.springbootreviews.bank.model.TransactionDTO;
import com.example.springbootreviews.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> handleTransaction(@RequestParam Long accountId,
                                                            @RequestParam(required = false) Long targetAccountId,
                                                            @RequestParam TransactionType transactionType,
                                                            @RequestParam BigDecimal amount) {
        TransactionDTO transactionDTO = transactionService.depositWithdrawal(accountId, targetAccountId, transactionType, amount);
        return ResponseEntity.ok(transactionDTO);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionByAccount(@PathVariable Long accountId) {
        List<TransactionDTO> transactionHistory = transactionService.getTransactionHistory(accountId);
        return ResponseEntity.ok(transactionHistory);

    }
}
