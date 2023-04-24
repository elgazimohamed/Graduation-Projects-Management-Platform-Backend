package com.pfe.plateforme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class EncadrantRegistrationRequest {
	
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private long idDepartement;
	
}
