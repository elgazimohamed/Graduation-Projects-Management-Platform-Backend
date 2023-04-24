package com.pfe.plateforme.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Encadrant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		
	private Long idEncadrant;
	private String nom;
	private String prenom;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "id_departement") 
	private Departement departement;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonBackReference
    @JoinTable(name = "ENCADRANT_ROLE",
            joinColumns = {
                    @JoinColumn(name = "ENCADRANT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> roles;
    
    @OneToMany(mappedBy = "encadrant")
	@JsonIgnore
    private List<Sujet> listSujets;
    
    @OneToMany(mappedBy = "encadrant")
    @JsonIgnore
    private List<DemandeRendezVous> demandes;
}
