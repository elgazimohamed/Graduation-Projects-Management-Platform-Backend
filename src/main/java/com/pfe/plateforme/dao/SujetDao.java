package com.pfe.plateforme.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.plateforme.entity.Sujet;

@Repository
public interface SujetDao extends JpaRepository<Sujet, Long> {
	
	Optional<Sujet> findById(Long id);
	Optional<Sujet> findByTitre(String titre);
	
}