package com.pfe.plateforme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.plateforme.entity.Filiere;
import com.pfe.plateforme.service.FiliereService;

@RestController
@CrossOrigin
public class FiliereController {
	
	@Autowired
	private FiliereService filiereService;
	
	@GetMapping("/filieres")
	public List<Filiere> getAllFilieres(){
		return filiereService.getAllFilieres();
	}
	
}
