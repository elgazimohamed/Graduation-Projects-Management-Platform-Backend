package com.pfe.plateforme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.plateforme.entity.DemandeRendezVous;
import com.pfe.plateforme.entity.demandeRdvRequest;
import com.pfe.plateforme.service.DemandeRendezVousService;

@RestController
@CrossOrigin
public class DemandeRendezVousController {

	@Autowired
	private DemandeRendezVousService drvService;
	
    @PostMapping("/demandes/addDemande")
    public ResponseEntity<String> addDemande(@RequestBody demandeRdvRequest request) {
    	
    	DemandeRendezVous drv = drvService.addDemande(request);
    	
	     if( drv != null ) {
	    	 return new ResponseEntity<>("Votre demande a été enregistrée avec succès", HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<>("Une erreur s'est produite lors de l'enregistrement de votre demande", 
					 HttpStatus.BAD_REQUEST);
		 }
    }
}
