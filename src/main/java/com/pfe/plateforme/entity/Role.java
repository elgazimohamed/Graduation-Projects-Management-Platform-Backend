package com.pfe.plateforme.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idRole;
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
    private Set<Etudiant> etudiants;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
    private Set<Encadrant> encadrants;
}
