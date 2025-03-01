package com.example.school_application.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.school_application.utils.Constants.Permissions;
import com.example.school_application.utils.Constants.Roles;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {
  private final UserDetailsService userDetailsService;
  private final JWTAuthenticationFilter jwtAuthenticationFilter;

  /**
   * GET contact can be accessed by admin only
   * POST contact can be accessed by User only as it has USER_WRITE permission
   * 
   * @param httpSecurity
   * @return
   * @throws Exception
   */
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf(t -> t.disable()).authorizeHttpRequests(t -> {
      t.requestMatchers(HttpMethod.GET, "/contact/**").hasRole(Roles.ADMIN.name());
      t.requestMatchers(HttpMethod.POST, "/contact/**").hasAuthority(Permissions.USER_WRITE.name());
      t.requestMatchers(HttpMethod.DELETE, "/contact/**").hasAuthority(Permissions.USER_DELETE.name());
      t.requestMatchers(HttpMethod.GET, "/auth/**").authenticated();
      t.anyRequest().permitAll();
    });
    httpSecurity.sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    // httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));
    httpSecurity.userDetailsService(userDetailsService);
    httpSecurity.addFilterBefore(jwtAuthenticationFilter,
        UsernamePasswordAuthenticationFilter.class);
    httpSecurity.formLogin(withDefaults());
    httpSecurity.httpBasic(withDefaults());
    httpSecurity.headers(h -> h.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return new ProviderManager(authProvider);
  }
}
