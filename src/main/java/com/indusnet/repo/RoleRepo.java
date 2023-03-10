package com.indusnet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indusnet.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, String> {

	Role findByRole(String role);
}
