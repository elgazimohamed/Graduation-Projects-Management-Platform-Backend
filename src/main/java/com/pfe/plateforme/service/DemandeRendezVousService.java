package com.pfe.plateforme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.DemandeRendezVousDao;
import com.pfe.plateforme.dao.EncadrantDao;
import com.pfe.plateforme.dao.GroupeDao;
import com.pfe.plateforme.dao.SujetDao;
import com.pfe.plateforme.entity.DemandeRendezVous;
import com.pfe.plateforme.entity.Encadrant;
import com.pfe.plateforme.entity.Groupe;
import com.pfe.plateforme.entity.Sujet;
import com.pfe.plateforme.entity.demandeRdvRequest;

@Service
public class DemandeRendezVousService {
	
	@Autowired
	private EncadrantDao encadrantDao;

	@Autowired
	private SujetDao sujetDao;

	@Autowired
	private GroupeDao groupeDao;
	
	@Autowired
	private DemandeRendezVousDao drvDao;
	
	public DemandeRendezVous addDemande(demandeRdvRequest request) {
		
		DemandeRendezVous demande = new DemandeRendezVous();
		
		demande.setSujetRdv(request.getSujetRdv());
		demande.setDescription(request.getSujetRdv());
		demande.setStatut("en attente");
		
		Sujet sujet   = sujetDao.findById(request.getIdSujet()).get();
		Groupe groupe = groupeDao.findById(request.getIdGroupe()).get();
		Encadrant encadrant = encadrantDao.findById(request.getIdEncadrant()).get();
		
		demande.setSujet(sujet);
		demande.setGroupe(groupe);
		demande.setEncadrant(encadrant);
		
		DemandeRendezVous savedDamande = drvDao.save(demande);
		
		return savedDamande;
	}
	
}
