package com.example.springbootreviews.bank.service;

import com.example.springbootreviews.bank.entity.Account;
import com.example.springbootreviews.bank.entity.Transaction;
import com.example.springbootreviews.bank.entity.TransactionType;
import com.example.springbootreviews.bank.exception.AccountNotFoundException;
import com.example.springbootreviews.bank.exception.InsufficientBalanceException;
import com.example.springbootreviews.bank.model.TransactionDTO;
import com.example.springbootreviews.bank.repository.AccountRepository;
import com.example.springbootreviews.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public TransactionDTO depositWithdrawal(Long accountId, Long targetAccountId, TransactionType transactionType, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("없는 계좌"));
        Transaction transaction = new Transaction();
        transaction.setFromAccount(account);
        transaction.setType(transactionType);
        transaction.setAmount(amount);

        switch (transactionType) {
            case DEPOSIT -> {
                account.setBalance(account.getBalance().add(amount));
                transaction.setToAccount(account);
            }
            case WITHDRAWAL -> {
                if(account.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientBalanceException("잔액부족");
                }
                account.setBalance(account.getBalance().subtract(amount));
            }
            case TRANSFER -> {
                if (account.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientBalanceException("잔액부족");
                }
                Account targetAccount = accountRepository.findById(targetAccountId)
                        .orElseThrow(() -> new AccountNotFoundException("없는 계좌"));
                account.setBalance(account.getBalance().subtract(amount));
                targetAccount.setBalance(targetAccount.getBalance().add(amount));
                accountRepository.save(targetAccount);
                transaction.setToAccount(targetAccount);
            }
        }
        accountRepository.save(account);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }

    public TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setFromAccountId(transaction.getFromAccount());
        transactionDTO.setToAccountId(transaction.getToAccount());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());
        return transactionDTO;
    }
}