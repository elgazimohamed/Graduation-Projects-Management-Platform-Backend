package com.pfe.plateforme.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.plateforme.entity.Departement;

@Repository
public interface DepartementDao extends JpaRepository<Departement, Long> {
	
	Optional<Departement> findById(Long id);
}
