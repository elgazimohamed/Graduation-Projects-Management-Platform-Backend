package com.pfe.plateforme.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.plateforme.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	Role findByRoleName(String roleName);
	Optional<Role> findById(Long id);
}

