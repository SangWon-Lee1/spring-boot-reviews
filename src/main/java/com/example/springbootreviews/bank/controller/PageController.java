package com.example.springbootreviews.bank.controller;

import com.example.springbootreviews.bank.entity.Account;
import com.example.springbootreviews.bank.entity.Customer;
import com.example.springbootreviews.bank.model.CustomerDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/bank")
    public String showBank() {
        return "bank/bank";
    }

    @GetMapping("/bank/login")
    public String showLoginFrom() {
        return "bank/login";
    }

    @GetMapping("/bank/menu")
    public String showMenuForm() {
        return "bank/menuForm";
    }

    @GetMapping("/customers/new")
    public String showCustomerFrom(Model model) {
        model.addAttribute("customer", new Customer());
        return "bank/customerForm";
    }

    @GetMapping("/accounts/new")
    public String showAccountFrom(Model model) {
        model.addAttribute("account", new Account());
        return "bank/accountForm";
    }

    @GetMapping("/customers/find")
    public String showCustomerDetails(Model model, HttpSession session) {
        CustomerDTO loggedInUser = (CustomerDTO) session.getAttribute("loggedInUser");
        model.addAttribute("customerId", loggedInUser.getCustomerId());
        model.addAttribute("customerName", loggedInUser.getCustomerName());
        model.addAttribute("createdAt", loggedInUser.getCreatedAt());
        return "bank/customerDetails";
    }

    @GetMapping("/accounts/find")
    public String showAccountDetails(Model model) {
        model.addAttribute("account", new Account());
        return "bank/accountDetails";
    }
}
