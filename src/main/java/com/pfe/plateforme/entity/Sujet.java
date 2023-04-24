package com.pfe.plateforme.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Sujet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		
	private Long id;
	
	private String titre;
	private String description;
	private int nombreMembres;
	private String choisie;
	
	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "id_encadrant") 
	private Encadrant encadrant;
	
//	@ManyToMany(mappedBy = "sujetsList")
//	@JsonIgnore
//    private List<Groupe> groupesList;
	
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "groupe_sujet", joinColumns = @JoinColumn(name = "idSujet"),
        inverseJoinColumns = @JoinColumn(name = "idGroupe"))
    private List<Groupe> groupesList;
    
    @OneToMany(mappedBy = "sujet")
	@JsonIgnore
    private List<DemandeRendezVous> demandes;
}
