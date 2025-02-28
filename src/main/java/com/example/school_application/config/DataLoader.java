package com.example.school_application.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.school_application.dto.UserDto;
import com.example.school_application.model.Role;
import com.example.school_application.repository.RoleRepository;
import com.example.school_application.repository.UserRepository;
import com.example.school_application.service.UserService;
import com.example.school_application.utils.Constants.Permissions;
import com.example.school_application.utils.Constants.Roles;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final UserService userService;
  final String adminEmail = "admin@admin.com";

  @Override
  public void run(String... args) {
    Roles role = Roles.ADMIN;
    if (roleRepository.findByName(role).isEmpty()) {
      Role newRole = new Role();
      newRole.setName(role);
      newRole.setPermissions(Set.of(Permissions.ADMIN_READ, Permissions.ADMIN_WRITE,
          Permissions.USER_READ, Permissions.USER_DELETE));
      roleRepository.save(newRole);
    }
    role = Roles.USER;
    if (roleRepository.findByName(role).isEmpty()) {
      Role newRole = new Role();
      newRole.setName(role);
      newRole.setPermissions(Set.of(Permissions.USER_WRITE));
      roleRepository.save(newRole);
    }

    if (userRepository.findByEmail(adminEmail).isEmpty()) {
      UserDto userDto = new UserDto();
      userDto.setName("admin");
      userDto.setEmail(adminEmail);
      userDto.setPassword("password");
      userService.saveUser(userDto);
      userService.appendRole(Roles.ADMIN, adminEmail);
    }
  }
}
