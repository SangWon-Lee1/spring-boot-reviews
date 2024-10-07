package com.example.springbootreviews.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 이 클래스를 데이터베이스 테이블로 매핑
@Table(name = "users") // 테이블 이름을 users로 명시적 설정
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 생성
public class User {

    @Id // 테이블의 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;

    @Column(nullable = false) // NOT NULL
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
}
