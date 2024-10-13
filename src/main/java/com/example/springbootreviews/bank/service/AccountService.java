package com.example.springbootreviews.bank.service;

import com.example.springbootreviews.bank.entity.Account;
import com.example.springbootreviews.bank.entity.Customer;
import com.example.springbootreviews.bank.model.AccountDTO;
import com.example.springbootreviews.bank.repository.AccountRepository;
import com.example.springbootreviews.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Autowired
    AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountId(accountDTO.getAccountId());
        Customer customer = customerRepository.findByCustomerId(accountDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("없는 고객"));
        account.setCustomer(customer);
        account.setBalance(accountDTO.getBalance());
        account.setCreatedAt(accountDTO.getCreatedAt());

        Account saveAccount = accountRepository.save(account);
        return convertToDTO(saveAccount);
    }

    public AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setCustomerId(account.getCustomer().getCustomerId());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setCreatedAt(account.getCreatedAt());
        return accountDTO;
    }

    public Optional<AccountDTO> getAccountById(Long id) {
        return accountRepository.findById(id).map(this::convertToDTO);
    }

    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow();
        account.setDeletedAt(LocalDateTime.now());
        account.setDeleted(true);
        accountRepository.save(account);
    }
}
