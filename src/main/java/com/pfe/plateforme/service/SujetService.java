package com.pfe.plateforme.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.EncadrantDao;
import com.pfe.plateforme.dao.EtudiantDao;
import com.pfe.plateforme.dao.SujetDao;
import com.pfe.plateforme.entity.Encadrant;
import com.pfe.plateforme.entity.Etudiant;
import com.pfe.plateforme.entity.GroupeSujet;
import com.pfe.plateforme.entity.Sujet;
import com.pfe.plateforme.entity.SujetRegistrationRequest;

@Service
public class SujetService {
	
	@Autowired
	private SujetDao sujetDao;
	
	@Autowired
	private EncadrantDao encadrantDao;
	
	@Autowired
	private EtudiantService etudiantService;
	
	@Autowired
	private GroupeSujetService groupeSujetService;
	
	public Sujet getSujetById(Long id) {
		Optional<Sujet> sujet = sujetDao.findById(id);
		if(sujet.isPresent())
			return sujet.get();
		else return new Sujet();
	}
	
	public List<Sujet> getAllSujets(){
		return sujetDao.findAll();
	}
	
    // Obtenir les sujets de l'encadrant
    public List<Sujet> getAllSujetsByEncadrantId(Long id){
    	
		Optional<Encadrant> encadrant = encadrantDao.findById(id);
		return encadrant
						.map(Encadrant::getListSujets)
						.orElse(Collections.emptyList());
    }
	
	public Sujet addNewSujet(SujetRegistrationRequest request) {
		
		if(!sujetDao.findByTitre(request.getTitre()).isPresent()) {
		
			Sujet sujet = new Sujet();
			
			sujet.setTitre(request.getTitre());
			sujet.setDescription(request.getDescription());
			sujet.setNombreMembres(request.getNombreMembres());
			sujet.setChoisie("non");
			
			Encadrant encadrant = encadrantDao.findById(request.getIdEncadrant()).get();
			sujet.setEncadrant(encadrant);
			
			Sujet savedSujet = sujetDao.save(sujet);
			return savedSujet;
		}
		return null;
	}
	
	public ResponseEntity<String> deleteSujet(Long id){
		if(sujetDao.findById(id).isPresent()) {
			sujetDao.deleteById(id);
			return new ResponseEntity<>("Le sujet a été supprimé avec succès", HttpStatus.OK); 
		}
		return new ResponseEntity<>("Une erreur s'est produite lors de la suppression du sujet", HttpStatus.BAD_REQUEST);
	}
	
	public Sujet getSujetAccepteByGroupeId(Long idEtudiant) {
		Sujet sujet = null;
		Etudiant etudiant = etudiantService.getEtudiant(idEtudiant);
		Long idGroupe = etudiant.getGroupe().getId();
		System.out.println("id Groupe : " + idGroupe);
		GroupeSujet groupe_sujet = groupeSujetService.getGroupeSujetByGroupeId(idGroupe);
		if(groupe_sujet != null) {
			sujet = sujetDao.findById(groupe_sujet.getSujet().getId()).get();
			return sujet;
		}
		return null;
	}
}
