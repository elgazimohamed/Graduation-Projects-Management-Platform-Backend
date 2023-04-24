package com.pfe.plateforme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.plateforme.entity.GroupeSujet;
import com.pfe.plateforme.entity.Sujet;
import com.pfe.plateforme.entity.SujetRegistrationRequest;
import com.pfe.plateforme.service.SujetService;

@RestController
public class SujetController {
	
	@Autowired
	private SujetService sujetService;
		
	@GetMapping("/sujets")
    /*@PreAuthorize("hasRole('etudiant')") */
	public List<Sujet> getAllSujets(){
		return sujetService.getAllSujets();
	}
	
	@GetMapping("/sujets/{id}")
	public Sujet getSujet(@PathVariable Long id) {
		return sujetService.getSujetById(id);
	}
	
	@GetMapping("/sujets/sujet/sujetAccepte/{idEtudiant}")
	public Sujet getSujetAccepteByGroupeId(@PathVariable Long idEtudiant) {
		return sujetService.getSujetAccepteByGroupeId(idEtudiant);
	}
	
    @GetMapping("/mes-sujets/{id_encadrant}")
	@PreAuthorize("hasRole('encadrant')")
    public List<Sujet> getAllSujetsByEncadrant(@PathVariable("id_encadrant") Long id){
    	return sujetService.getAllSujetsByEncadrantId(id);
    }
	
	@PostMapping("/mes-sujets")
	@PreAuthorize("hasRole('encadrant')")
	public ResponseEntity<String> addSujet(@RequestBody SujetRegistrationRequest request){
		
		Sujet savedSujet = sujetService.addNewSujet(request);
		
		if(savedSujet != null)
			return new ResponseEntity<>("Le sujet a été ajouté avec succès", HttpStatus.OK);
		else
			return new ResponseEntity<>("Une erreur s'est produite lors de l'ajout du sujet.", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/mes-sujets/{id_encadrant}/{id_sujet}")
	public ResponseEntity<String> deleteSujet(@PathVariable("id_sujet") Long id){
		return sujetService.deleteSujet(id);
	}
	
}
