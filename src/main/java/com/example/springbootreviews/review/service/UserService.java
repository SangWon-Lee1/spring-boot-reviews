package com.example.springbootreviews.review.service;

import com.example.springbootreviews.review.entity.User;
import com.example.springbootreviews.review.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final 필드를 위한 생성자 자동생성
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
