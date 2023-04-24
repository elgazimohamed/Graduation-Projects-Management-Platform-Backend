package com.pfe.plateforme.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.plateforme.entity.Etudiant;
import com.pfe.plateforme.entity.EtudiantRegistrationRequest;
import com.pfe.plateforme.service.EtudiantService;

@RestController
@CrossOrigin
public class EtudiantController {

	@Autowired
	private EtudiantService etudiantService;
    
	@GetMapping("/etudiants/{id}")
	public Etudiant getEtudiant(@PathVariable Long id) {
		return etudiantService.getEtudiant(id);
	}
	
    @PostMapping("/registerEtudiant")
    public ResponseEntity<String> registerEtudiant(@RequestBody EtudiantRegistrationRequest request) {
    	 Etudiant savedEtudiant = etudiantService.registerEtudiant(request);
	     if(savedEtudiant != null) {
	    	 return new ResponseEntity<>("Vous avez été inscrit avec succès.", HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>("Une erreur s'est produite lors de l'inscription", HttpStatus.BAD_REQUEST);
		 }
    }
	
    // Get All etudiant in a group that depend of a specific sujet
    @GetMapping("/etudiantsParGroupe/{idSujet}")
    public List<List<Etudiant>> getAllStudentsByGroupesBySujet(@PathVariable Long idSujet){
    	return etudiantService.getAllEtudiantsByGroupesBySujet(idSujet);
    }
}
