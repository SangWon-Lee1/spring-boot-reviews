package com.example.springbootreviews.bank.service;

import com.example.springbootreviews.bank.entity.Customer;
import com.example.springbootreviews.bank.model.CustomerDTO;
import com.example.springbootreviews.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=-]).{8,}$");

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO registerCustomer(CustomerDTO customerDTO) {
        validateCustomer(customerDTO);

        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPassword(customerDTO.getPassword());

        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    public CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCreatedAt(customer.getCreatedAt());
        customerDTO.setUpdatedAt(customer.getUpdatedAt());
        return customerDTO;
    }

    private void validateCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getCustomerId() == null || customerDTO.getCustomerId().isEmpty()) {
            throw new IllegalArgumentException("Id 값이 비어있음");
        }
        if (customerDTO.getCustomerName() == null || customerDTO.getCustomerName().isEmpty()) {
            throw new IllegalArgumentException("이름이 비어있음");
        }
        if (customerDTO.getPassword() == null || !PASSWORD_PATTERN.matcher(customerDTO.getPassword()).matches()) {
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상, 숫자, 대문자, 특수 문자를 포함해야 합니다.");
        }
    }
}