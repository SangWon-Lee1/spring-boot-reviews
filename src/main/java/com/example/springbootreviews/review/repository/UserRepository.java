package com.example.springbootreviews.review.repository;

import com.example.springbootreviews.review.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 데이터 접근 레이어임을 명시
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository 기본적인 데이터베이스 연동 기능 제공
    // save(), findById() 등 메소드 사용 가능
}
