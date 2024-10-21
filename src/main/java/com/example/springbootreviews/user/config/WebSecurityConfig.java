package com.example.springbootreviews.user.config;

import com.example.springbootreviews.user.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
public class WebSecurityConfig {
    private final UserDetailService userDetailService;

    public WebSecurityConfig(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public WebSecurityCustomizer ignore() {
        return web -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                        custom -> custom.requestMatchers("/login2", "/signup", "/user")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(custom -> custom.loginPage("/user/login2")
                        .defaultSuccessUrl("/user/abc"))
                .logout(custom -> custom.logoutSuccessUrl("/user/login2")
                        .invalidateHttpSession(true))
                .csrf(custom -> custom.disable())
                .build();
    }
}