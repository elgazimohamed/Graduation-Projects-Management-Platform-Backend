package com.pfe.plateforme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor
public class AccepteGroupeRequest {
	
		private Long idGroupe;
    	private Long idSujet;
    	private Long idEncadrant;
}