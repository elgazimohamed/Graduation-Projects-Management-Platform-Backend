package com.pfe.plateforme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor
public class SujetRegistrationRequest {
	
	private String titre;
	private String description;
	private int nombreMembres;
	private Long idEncadrant;
	
}
