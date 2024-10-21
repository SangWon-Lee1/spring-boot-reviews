package com.example.springbootreviews.user.service;

import com.example.springbootreviews.user.domain.Users;
import com.example.springbootreviews.user.domain.dto.AddUserRequest;
import com.example.springbootreviews.user.repository.UserRepository2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService2 {
    private final UserRepository2 userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService2(UserRepository2 userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Users save(AddUserRequest dto) {
        return userRepository.save(new Users(dto.getEmail(),
                        encoder.encode(dto.getPassword())));
    }
}
