package com.pfe.plateforme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.GroupeSujetDao;
import com.pfe.plateforme.entity.GroupeSujet;

@Service
public class GroupeSujetService {
	
	@Autowired
	private GroupeSujetDao groupeSujetDao;
	
    public void updateStatut(Long idGroupe, Long idSujet, String newStatus) {
        GroupeSujet groupeSujet = groupeSujetDao.findByGroupeIdAndSujetId(idGroupe, idSujet);
        if(groupeSujet != null) {
	        groupeSujet.setStatut(newStatus);
	        groupeSujetDao.save(groupeSujet);
    	}
    }
    
    public GroupeSujet getGroupeSujet(Long idSujet) {
    	return groupeSujetDao.findBySujetIdAndStatut(idSujet, "accepte");
    }
    
    public GroupeSujet getGroupeSujetByGroupeId(Long idGroupe) {
    	return groupeSujetDao.findByGroupeIdAndStatut(idGroupe, "accepte");
    }
}
