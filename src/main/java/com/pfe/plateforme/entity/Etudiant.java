package com.pfe.plateforme.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Etudiant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long idEtudiant;
	
	@Column(unique = true, nullable = false)
	private int codeApogee;
	
	private String nom;
	private String prenom;
	
	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "id_filiere") 
	private Filiere filiere;

	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
    @ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	@JsonBackReference
    @JoinTable(
		name = "etudiant_role",
	    joinColumns = {
	    		@JoinColumn(name = "etudiant_id")
	    },
	    inverseJoinColumns = {
	            @JoinColumn(name = "role_id")
	    }
    )
    private Set<Role> roles;
    
    @ManyToOne
	@JsonBackReference
	@JsonIgnore
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;
}
