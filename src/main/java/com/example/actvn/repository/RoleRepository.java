package com.example.actvn.repository;

import com.example.actvn.entity.Role;
import com.example.actvn.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<Role,Integer> {
    Optional<Role> findByName (String roleName);
}
