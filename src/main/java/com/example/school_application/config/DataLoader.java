package com.example.school_application.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.school_application.dto.UserDto;
import com.example.school_application.model.Role;
import com.example.school_application.repository.RoleRepository;
import com.example.school_application.repository.UserRepository;
import com.example.school_application.service.UserService;
import com.example.school_application.utils.Constants.Roles;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;
  final String adminEmail="admin@admin.com";
  
  @Override
  public void run(String... args) {
    List<Roles> roles = List.of(Roles.USER, Roles.ADMIN);
    for (Roles role : roles) {
      if (roleRepository.findByName(role).isEmpty()) {
        Role newRole = new Role();
        newRole.setName(role);
        roleRepository.save(newRole);
      }
    }

    if(userRepository.findByEmail(adminEmail).isEmpty()){
      UserDto userDto= new UserDto();
      userDto.setName("admin");
      userDto.setEmail(adminEmail);
      userDto.setPassword("password");
      var user=userService.saveUser(userDto);
      System.out.println(user.getPassword()+"---"+userDto.getPassword());
      userService.appendRole(Roles.ADMIN, adminEmail);
    }
  }
}
