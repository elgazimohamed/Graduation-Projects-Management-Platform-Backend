package com.pfe.plateforme.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.plateforme.entity.GroupeSujet;

@Repository
public interface GroupeSujetDao extends JpaRepository<GroupeSujet, Long> {
    GroupeSujet findByGroupeIdAndSujetId(Long idGroupe, Long idSujet);
    GroupeSujet findBySujetIdAndStatut(Long idSujet, String statut);
    GroupeSujet findByGroupeIdAndStatut(Long idGroupe, String statut);
}