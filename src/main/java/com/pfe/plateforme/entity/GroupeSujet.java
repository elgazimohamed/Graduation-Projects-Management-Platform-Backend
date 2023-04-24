package com.pfe.plateforme.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "groupe_sujet")
public class GroupeSujet {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
	
    private String statut;
}
