package com.pfe.plateforme.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfe.plateforme.entity.Groupe;

@Repository
public interface GroupeDao extends JpaRepository<Groupe, Long> {
	
	Optional<Groupe> findById(Long id);	
	
    @Query("SELECT g FROM Groupe g JOIN g.sujetsList s WHERE s.id = :idSujet")
    List<Groupe> findBySujetId(@Param("idSujet") Long idSujet);
    
}