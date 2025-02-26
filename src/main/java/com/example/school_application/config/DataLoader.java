package com.example.school_application.config;

import com.example.school_application.model.Role;
import com.example.school_application.repository.RoleRepository;
import com.example.school_application.utils.Constants.Roles;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
  private final RoleRepository roleRepository;

  public DataLoader(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @PostConstruct
  public void init() {
    List<Roles> roles = List.of(Roles.USER, Roles.ADMIN);
    for (Roles role : roles) {
      if (roleRepository.findByName(role).isEmpty()) {
        Role newRole = new Role();
        newRole.setName(role);
        roleRepository.save(newRole);
      }
    }
  }
}
