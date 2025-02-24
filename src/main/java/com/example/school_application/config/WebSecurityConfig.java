package com.example.school_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf(t -> t.disable())
        .authorizeHttpRequests(
            t -> {
              // t.requestMatchers(HttpMethod.GET, "/contact/**").authenticated();
              t.anyRequest().permitAll();
            });
    return httpSecurity.build();
  }
}
