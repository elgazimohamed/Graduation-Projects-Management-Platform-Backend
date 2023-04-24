package com.pfe.plateforme.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIsController {
	
    @GetMapping({"/pourEtudiant"})
    @PreAuthorize("hasRole('etudiant')")
    public String pourEtudiant(){
        return "Cette URL n'est accessible qu'à l'etudiant";
    }

    @GetMapping({"/pourEncadrant"})
    @PreAuthorize("hasRole('encadrant')")
    public String pourEncadrant(){
        return "Cette URL n'est accessible qu'à l'encadrant";
    }
	
}
