package com.example.school_application.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.school_application.dto.UserDto;
import com.example.school_application.mapper.UserMapper;
import com.example.school_application.repository.RoleRepository;
import com.example.school_application.repository.UserRepository;
import com.example.school_application.utils.Constants.Roles;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UserDto saveUser(UserDto userDto) {
    var user = UserMapper.toUser(userDto);
    var userRole = roleRepository.findByName(Roles.USER).get();
    user.setRoles(Set.of(userRole));
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user = userRepository.save(user);
    return UserMapper.toUserDto(user);
  }

  public List<UserDto> getAllUser() {
    var users = userRepository.findAll();
    return users.stream().map(user -> UserMapper.toUserDto(user)).toList();
  }

  public UserDto getUser(String email) {
    var user = userRepository.findByEmail(email).get();
    return UserMapper.toUserDto(user);
  }

  public void appendRole(Roles role, String email) {
    var userRole = roleRepository.findByName(role).get();
    var user = userRepository.findByEmail(email).get();
    user.getRoles().add(userRole);
    userRepository.save(user);
  }
}
