package com.pfe.plateforme.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.plateforme.service.InStartingService;

@RestController
public class InStarting {

	@Autowired
	private InStartingService inStartingService;
	
    @PostConstruct
    public void initRoleAndUser() {
    	inStartingService.initDatabase();
    }	
	
}
