package com.pfe.plateforme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.plateforme.entity.Departement;
import com.pfe.plateforme.service.DepartementService;

@RestController
@CrossOrigin
public class DepartementController {
	
	@Autowired
	private DepartementService departementService;
	
	@GetMapping("/departements")
	public List<Departement> getAllDepartement(){ 
		return departementService.getAllDepartement();
	}
	
}
