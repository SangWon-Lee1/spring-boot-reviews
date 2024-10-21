package com.example.springbootreviews.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPageController2 {
    @GetMapping("/login2")
    public String login() {
        return "user/login2";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }
}
