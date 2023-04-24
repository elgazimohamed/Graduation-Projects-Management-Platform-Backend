package com.pfe.plateforme.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.plateforme.entity.DemandeRendezVous;

@Repository
public interface DemandeRendezVousDao extends JpaRepository<DemandeRendezVous, Long> {
	
}

