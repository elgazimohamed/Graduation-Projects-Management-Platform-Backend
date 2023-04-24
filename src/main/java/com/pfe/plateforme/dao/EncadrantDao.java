package com.pfe.plateforme.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.plateforme.entity.Encadrant;

@Repository
public interface EncadrantDao extends JpaRepository<Encadrant, Long> {
	
	Optional<Encadrant> findByEmail(String email);
	Optional<Encadrant> findById(Long id);
}

