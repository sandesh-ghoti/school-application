package com.example.school_application.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.example.school_application.utils.Constants.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
              t.requestMatchers(HttpMethod.GET, "/contact/**").hasRole(Roles.ADMIN.name());
              // t.requestMatchers(PathRequest.toH2Console()).permitAll();
              t.anyRequest().permitAll();
            });
    httpSecurity.formLogin(withDefaults());
    httpSecurity.httpBasic(withDefaults());
    httpSecurity.headers(h -> h.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
    return httpSecurity.build();
  }

  @Bean
  InMemoryUserDetailsManager userDetailsManager() {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    UserDetails user =
        User.withUsername("user")
            .password(encoder.encode("password"))
            .roles(Roles.USER.name())
            .build();
    UserDetails admin =
        User.withUsername("admin")
            .password(encoder.encode("admin"))
            .roles(Roles.ADMIN.name())
            .build();
    return new InMemoryUserDetailsManager(user, admin);
  }
}
