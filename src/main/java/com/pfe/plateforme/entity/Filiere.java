package com.pfe.plateforme.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idFiliere;
	private String nomFiliere;
	
    @OneToMany(mappedBy = "filiere")
	@JsonIgnore
    private List<Etudiant> etudiantFiliere;
	
}
