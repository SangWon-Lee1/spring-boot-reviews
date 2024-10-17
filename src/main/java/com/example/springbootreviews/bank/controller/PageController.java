package com.example.springbootreviews.bank.controller;

import com.example.springbootreviews.bank.entity.Customer;
import com.example.springbootreviews.bank.service.AccountService;
import com.example.springbootreviews.bank.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PageController {
    private final CustomerService customerService;
    private final AccountService accountService;

    public PageController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/bank")
    public String showBank() {
        return "bank/bank";
    }

    @GetMapping("/customers/new")
    public String showCustomerFrom(Model model) {
        model.addAttribute("customer", new Customer());
        return "bank/customerForm";
    }
}
