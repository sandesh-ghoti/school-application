package com.example.school_application.repository;

import com.example.school_application.model.Role;
import com.example.school_application.utils.Constants.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(Roles name);
}
