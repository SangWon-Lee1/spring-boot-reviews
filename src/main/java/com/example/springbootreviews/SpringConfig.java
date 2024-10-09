package com.example.springbootreviews;

import com.example.springbootreviews.repository.JdbcTemplateMemberRepository;
import com.example.springbootreviews.repository.MemberRepository;
import com.example.springbootreviews.repository.MemoryMemberRepository;
import com.example.springbootreviews.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
