package com.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.model.Role;

@Repository 
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
	
}
