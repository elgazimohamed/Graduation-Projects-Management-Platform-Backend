package com.pfe.plateforme.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor
public class DemandeRendezVous {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String sujetRdv;
	
	private String description;
	
	private String statut;
	
    @ManyToOne
	@JsonBackReference
	@JsonIgnore
    @JoinColumn(name = "idGroupe")
    private Groupe groupe;

    @ManyToOne
	@JsonBackReference
	@JsonIgnore
    @JoinColumn(name = "idSujet")
    private Sujet sujet;

    @ManyToOne
	@JsonBackReference
	@JsonIgnore
    @JoinColumn(name = "idEncadrant")
    private Encadrant encadrant;
}
