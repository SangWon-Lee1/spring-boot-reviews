package com.example.springbootreviews.bank.controller;

import com.example.springbootreviews.bank.model.CustomerDTO;
import com.example.springbootreviews.bank.service.CustomerService;
import com.mysql.cj.NativeQueryAttributesBindings;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;
    private NativeQueryAttributesBindings session;

    @Autowired
    public  CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> registerCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.registerCustomer(customerDTO);
        return ResponseEntity.status(201).body(createdCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestParam String customerId, @RequestParam String password, HttpSession session) {
        Optional<CustomerDTO> login = customerService.login(customerId, password);
        if (login.isPresent()) {
            session.setAttribute("loggedInUser", login.get());
            return ResponseEntity.status(302).location(URI.create("/bank/menu")).build();
        } else {
            return ResponseEntity.status(302).location(URI.create("/bank/login")).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Optional<CustomerDTO> customerDTO = customerService.getCustomerById(id);
        return customerDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}