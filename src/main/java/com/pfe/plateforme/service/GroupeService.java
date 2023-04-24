package com.pfe.plateforme.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.pfe.plateforme.dao.EtudiantDao;
import com.pfe.plateforme.dao.GroupeDao;
import com.pfe.plateforme.dao.SujetDao;
import com.pfe.plateforme.entity.AjouterLienDriveRequest;
import com.pfe.plateforme.entity.CreationGroupeRequest;
import com.pfe.plateforme.entity.Etudiant;
import com.pfe.plateforme.entity.Groupe;
import com.pfe.plateforme.entity.RejoindreGroupeRequest;
import com.pfe.plateforme.entity.Sujet;

@Service
public class GroupeService {
	
	@Autowired
	private GroupeDao groupeDao;
	
	@Autowired
	private SujetDao sujetDao;
	
	@Autowired
	private EtudiantDao etudiantDao;
	
	@Autowired
	private EtudiantService etudiantService;
	
	// Add new groupe
	public Groupe addNewGroupe(Groupe groupe) {
		return groupeDao.save(groupe);
	}
	
	// Get all the groupes
	public List<Groupe> getAllGroupes() {
		return groupeDao.findAll();
	}
	
    public List<Groupe> getAllGroupesBySujet(Long idSujet) {
	    Optional<Sujet> sujet = sujetDao.findById(idSujet);
		return sujet
				.map(Sujet::getGroupesList)
				.orElse(Collections.emptyList());
    }
	
    public Groupe addNewGroupe(CreationGroupeRequest request) {
    	
    	Sujet sujet = sujetDao.findById(request.getIdSujet()).get();
    	Etudiant etudiant = etudiantDao.findById(request.getIdEtudiant()).get();
    	
    	Groupe groupe = new Groupe();
    	
    	groupe.setNombreMembresGroupe(sujet.getNombreMembres());
    	groupe.setEncadre("non");
    	List<Sujet> listSujet = new ArrayList<Sujet>();
    	listSujet.add(sujet);
    	groupe.setSujetsList(listSujet);
    	
    	Groupe savedGroupe = groupeDao.save(groupe);
    	etudiant.setGroupe(groupe);
    	etudiantDao.save(etudiant);
    	return savedGroupe;
    }
    
    public ResponseEntity<String> rejoindreGroupe(RejoindreGroupeRequest request){
    	
    	Groupe groupe = groupeDao.findById(request.getIdGroupe()).get();
    	Etudiant etudiant = etudiantDao.findById(request.getIdEtudiant()).get();
    	
    	etudiant.setGroupe(groupe);
    	Etudiant savedEtudiant = etudiantDao.save(etudiant);
    	
		if(savedEtudiant != null) {
			return new ResponseEntity<>("Vous avez été ajouté avec succès au groupe", HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<>("Une erreur s'est produite lors de la tentative de vous ajouter au groupe", 
					HttpStatus.BAD_REQUEST);
		}
    }
    
    public List<Etudiant> getEtudiantsInGroupeAccepte(Long idEtudiant){
    	Etudiant etudiant = etudiantDao.findById(idEtudiant).get();
    	return etudiantService.getEtudiantsByGroupe(etudiant.getGroupe().getId());
    }
    
    public ResponseEntity<String> ajouterLienDrive(AjouterLienDriveRequest request){
    	Etudiant etudiant = etudiantDao.findById(request.getId()).get();
    	Groupe groupe = groupeDao.findById(etudiant.getGroupe().getId()).get();
    	groupe.setLienDrive(request.getLienDrive());
    	Groupe savedGroupe = groupeDao.save(groupe);
    	if(savedGroupe != null ) {
    		return new ResponseEntity<>("Vous avez ajouté le lien avec succès", HttpStatus.OK);
    	}
    	else {
    		return new ResponseEntity<>("Une erreur s'est produite lors de l'ajout de lien", HttpStatus.BAD_REQUEST);    		
    	}
    }
}
