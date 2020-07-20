package com.mehdi.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehdi.auth.enums.ERole;
import com.mehdi.auth.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
