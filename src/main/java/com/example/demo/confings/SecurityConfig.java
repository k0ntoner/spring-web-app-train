package com.example.demo.confings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/user/register").permitAll()
        .requestMatchers("/user/**").authenticated()
        
        .anyRequest().permitAll()
        
        )
        .formLogin(form ->form
        .loginPage("/user/login")
        .usernameParameter("email")
        .defaultSuccessUrl("/user/dashboard", true)
        .permitAll()
        )
        
        .logout(logout ->logout
        .permitAll()
        );
        return http.build();
    }
}
