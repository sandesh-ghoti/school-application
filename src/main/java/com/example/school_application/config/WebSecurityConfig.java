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
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.school_application.utils.Constants.Permissions;
import com.example.school_application.utils.Constants.Roles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
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
      t.requestMatchers(HttpMethod.GET, "/contact/**").hasAnyAuthority(Roles.ADMIN.name(),
          "SCOPE_" + Roles.ADMIN.name());
      t.requestMatchers(HttpMethod.POST, "/contact/**").hasAnyAuthority(Permissions.USER_WRITE.name(),
          "SCOPE_" + Permissions.USER_WRITE
              .name());
      t.requestMatchers(HttpMethod.DELETE, "/contact/**").hasAnyAuthority(Permissions.USER_DELETE.name(),
          "SCOPE_" + Permissions.USER_DELETE);
      t.requestMatchers(HttpMethod.GET, "/auth/**").authenticated();
      t.anyRequest().permitAll();
    });
    httpSecurity.sessionManagement(
        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));
    httpSecurity.exceptionHandling(ex -> {
      log.error("[SecurityConfig:apiSecurityFilterChain] Exception due to :{}", ex);
      ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint());
      ex.accessDeniedHandler(new BearerTokenAccessDeniedHandler());
    });
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
