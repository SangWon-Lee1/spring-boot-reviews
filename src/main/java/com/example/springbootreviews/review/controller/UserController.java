package com.example.springbootreviews.review.controller;

import com.example.springbootreviews.review.entity.User;
import com.example.springbootreviews.review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // REST API 요청을 처리
@RequestMapping("/example") // 기본 URL 경로 설정
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register") // post 요청을 처리
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "회원가입 성공";
    }
}
