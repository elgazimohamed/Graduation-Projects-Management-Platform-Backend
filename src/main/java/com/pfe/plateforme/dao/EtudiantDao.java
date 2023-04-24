package com.pfe.plateforme.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.plateforme.entity.Etudiant;

@Repository
public interface EtudiantDao extends JpaRepository<Etudiant, Long> {
	
	Optional<Etudiant> findByEmail(String email);
	Optional<Etudiant> findById(Long id);
	//List<Etudiant> findByGroupe(Long id);
}

