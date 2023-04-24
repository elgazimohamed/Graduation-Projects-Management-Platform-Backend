package com.pfe.plateforme.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.plateforme.entity.AjouterLienDriveRequest;
import com.pfe.plateforme.entity.CreationGroupeRequest;
import com.pfe.plateforme.entity.Etudiant;
import com.pfe.plateforme.entity.Groupe;
import com.pfe.plateforme.entity.RejoindreGroupeRequest;
import com.pfe.plateforme.service.EtudiantService;
import com.pfe.plateforme.service.GroupeService;

@RestController
public class GroupeController {
		
	@Autowired
	private GroupeService groupeService;
	
	@Autowired
	private EtudiantService etudiantService;
	
	@GetMapping("/groupes")
	public List<Groupe> getAllGroupes(){
		return groupeService.getAllGroupes();
	}
	
	@PostMapping("/groupes")
	@PreAuthorize("hasRole('etudiant')")
	public ResponseEntity<String> addNewGroupe(@RequestBody CreationGroupeRequest request){
		Groupe savedGroupe = groupeService.addNewGroupe(request);
		if(savedGroupe != null) {
			return new ResponseEntity<>("Le groupe a été ajouté avec succès", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Une erreur s'est produite lors de l'ajout du groupe", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/groupes/RejoindreGroupe")
	public ResponseEntity<String> rejoindreGroupe(@RequestBody RejoindreGroupeRequest request){
		return groupeService.rejoindreGroupe(request);
	}
	
	@PostMapping("/groupes/groupe/ajouterLienDrive")
    public ResponseEntity<String> ajouterLienDrive(@RequestBody AjouterLienDriveRequest request){
		return groupeService.ajouterLienDrive(request);
    }
	
	@GetMapping("/groupes/groupe/EtudiantsByGroupe/{idEtudiant}")
    public List<Etudiant> getEtudiantsInGroupeAccepte(@PathVariable Long idEtudiant) {
		return groupeService.getEtudiantsInGroupeAccepte(idEtudiant);
    }
}