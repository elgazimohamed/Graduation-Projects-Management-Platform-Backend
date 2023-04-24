package com.pfe.plateforme.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.plateforme.entity.Filiere;

@Repository
public interface FiliereDao extends JpaRepository<Filiere, Long> {
	Optional<Filiere> findById(Long id);
}