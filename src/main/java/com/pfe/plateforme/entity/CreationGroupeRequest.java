package com.pfe.plateforme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class CreationGroupeRequest {
	
	private Long idEtudiant;
	private Long idSujet;
	
}
