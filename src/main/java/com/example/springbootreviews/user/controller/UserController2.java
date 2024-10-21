package com.example.springbootreviews.user.controller;

import com.example.springbootreviews.user.domain.dto.AddUserRequest;
import com.example.springbootreviews.user.service.UserService2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController2 {
    private final UserService2 userService;

    public UserController2(UserService2 userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String save(@ModelAttribute AddUserRequest request) {
        userService.save(request);
        return "redirect:/login2";
    }
}
