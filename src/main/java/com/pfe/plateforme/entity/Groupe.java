package com.pfe.plateforme.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Groupe {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int nombreMembresGroupe;
	
	private String encadre;
	
	private String lienDrive;
	
	@OneToMany(mappedBy = "groupe")
	@JsonIgnore
	private List<Etudiant> etudiants;
	
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "groupe_sujet", joinColumns = @JoinColumn(name = "idGroupe"),
        inverseJoinColumns = @JoinColumn(name = "idSujet"))
    private List<Sujet> sujetsList; 
    
    @OneToMany(mappedBy = "groupe")
	@JsonIgnore
    private List<DemandeRendezVous> demandes;
}
