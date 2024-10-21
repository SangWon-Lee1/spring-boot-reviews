package com.example.springbootreviews.user.repository;

import com.example.springbootreviews.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository2 extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}