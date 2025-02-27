package com.example.school_application.mapper;

import com.example.school_application.dto.UserDto;
import com.example.school_application.model.User;

public class UserMapper {
  public static UserDto toUserDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setName(user.getName());
    userDto.setEmail(user.getEmail());
    userDto.setPassword(user.getPassword());
    return userDto;
  }

  public static User toUser(UserDto userDto) {
    User user = new User();
    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    return user;
  }
}
