package com.pfe.plateforme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.plateforme.entity.AccepteGroupeRequest;
import com.pfe.plateforme.entity.Encadrant;
import com.pfe.plateforme.entity.EncadrantRegistrationRequest;
import com.pfe.plateforme.entity.Etudiant;
import com.pfe.plateforme.service.EncadrantService;

@RestController
@CrossOrigin
public class EncadrantController {
	
	@Autowired
	private EncadrantService encadrantService;
	
    @PostMapping("/registerEncadrant")
    public String registerEtudiant(@RequestBody EncadrantRegistrationRequest request) {
    	 Encadrant savedEncadrant = encadrantService.registerEncadrant(request);
    	 if (savedEncadrant != null )
    		 return "L'encadrant a été ajouté avec succès";
		return "Error dans l'ajoute de l'encadrant";
    }
    
    @GetMapping("/encadrants/{id}")
    public Encadrant getEncadrant(@PathVariable Long id) {
    	return encadrantService.getEncadrant(id);
    }
    
    @PostMapping("/sujets/accepter")
    public String accepterGroupe(@RequestBody AccepteGroupeRequest request) {
    	encadrantService.accepterGroupe(request);
    	return "L'acceptation fait avec succee";
    }
    
    @GetMapping("/etudiantsGroupeAccepter/{idSujet}")
    public List<Etudiant> getEtudiantByGroupeAccepte(@PathVariable Long idSujet){
    	return encadrantService.getEtudiantByGroupeAccepte(idSujet);
    }
}
