package com.example.school_application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.school_application.model.Role;
import com.example.school_application.utils.Constants.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  @EntityGraph(attributePaths = "permissions")
  Optional<Role> findByName(Roles name);
}
