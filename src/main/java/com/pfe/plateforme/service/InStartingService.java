package com.pfe.plateforme.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.DepartementDao;
import com.pfe.plateforme.dao.EncadrantDao;
import com.pfe.plateforme.dao.EtudiantDao;
import com.pfe.plateforme.dao.FiliereDao;
import com.pfe.plateforme.dao.GroupeDao;
import com.pfe.plateforme.dao.RoleDao;
import com.pfe.plateforme.dao.SujetDao;
import com.pfe.plateforme.entity.Departement;
import com.pfe.plateforme.entity.Encadrant;
import com.pfe.plateforme.entity.Etudiant;
import com.pfe.plateforme.entity.Filiere;
import com.pfe.plateforme.entity.Groupe;
import com.pfe.plateforme.entity.Role;
import com.pfe.plateforme.entity.Sujet;

@Service
public class InStartingService {
	
	@Autowired
	private EtudiantDao etudiantDao;
	
	@Autowired
	private EncadrantDao encadrantDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private FiliereDao filiereDao;
	
	@Autowired
	private DepartementDao departementDao;
	
	@Autowired
	private SujetDao sujetDao;
	
	@Autowired
	private GroupeDao groupeDao;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public void initDatabase() {

		// Ajouter un role "etudiant"		
	
		Role etudiantRole = new Role();
		etudiantRole.setRoleName("etudiant");
		roleDao.save(etudiantRole);
		
		// Ajouter un role "encadrant"
		
		Role encadrantRole = new Role();
		encadrantRole.setRoleName("encadrant");
		roleDao.save(encadrantRole);
		
		// Ajouter un role "encadre"
		
		Role encadreRole = new Role();
		encadreRole.setRoleName("encadre");
		roleDao.save(encadreRole);
	
		// Ajouter une filiere
		
		Filiere smi = new Filiere();
		smi.setNomFiliere("Sciences Mathématiques et Informatique");
		filiereDao.save(smi);
		
		Filiere physique = new Filiere();
		physique.setNomFiliere("Sciences Physiques");
		filiereDao.save(physique);
		
		Filiere chimie = new Filiere();
		chimie.setNomFiliere("Sciences Chimiques");
		filiereDao.save(chimie);
		
		Filiere eco = new Filiere();
		eco.setNomFiliere("Sciences Economiques de Gestion");
		filiereDao.save(eco);
		
		Filiere ltf = new Filiere();
		ltf.setNomFiliere("Langue et Littérature Françaises");
		filiereDao.save(ltf);
		
		Filiere ps = new Filiere();
		ps.setNomFiliere("Psychologie");
		filiereDao.save(ps);
		
		Filiere dp = new Filiere();
		dp.setNomFiliere("Droit Public");
		filiereDao.save(dp);
		
		// Ajouter une departement
		
		Departement dep_info = new Departement();
		dep_info.setNomDepartement("Département d'Informatique");
		departementDao.save(dep_info);
		
		Departement dep_biologie = new Departement();
		dep_biologie.setNomDepartement("Département de Biologie");
		departementDao.save(dep_biologie);
		
		Departement dep_math = new Departement();
		dep_math.setNomDepartement("Département de Mathématiques");
		departementDao.save(dep_math);
		
		Departement dep_physique = new Departement();
		dep_physique.setNomDepartement("Département de Physique");
		departementDao.save(dep_physique);
		
		Departement dep_chimie = new Departement();
		dep_chimie.setNomDepartement("Département de Chimie");
		departementDao.save(dep_chimie);
		
		Departement dep_eco = new Departement();
		dep_eco.setNomDepartement("Département d'Économie");
		departementDao.save(dep_eco);
		
		Departement dep_fr = new Departement();
		dep_fr.setNomDepartement("Département de Français");
		departementDao.save(dep_fr);
		
		//////////////// Les encadrants //////////////////////
		
		Set<Role> encadrantRoles = new HashSet<>();
		encadrantRoles.add(encadrantRole);
		
		// Ajouter un encadrant	n1
		
		Encadrant encadrant1 = new Encadrant();
		encadrant1.setNom("KAWTARI");
		encadrant1.setPrenom("Souad");
		encadrant1.setEmail("souad.kawtari@uae.ac.ma");
		encadrant1.setPassword(getEncodedPassword("souad123"));
		encadrant1.setDepartement(dep_info);
		encadrant1.setRoles(encadrantRoles);
		encadrantDao.save(encadrant1);
		
		// Ajouter un encadrant	n2
		
		Encadrant encadrant2 = new Encadrant();
		encadrant2.setNom("DOUKALI");
		encadrant2.setPrenom("Rachid");
		encadrant2.setEmail("rachid.doukali@uae.ac.ma");
		encadrant2.setPassword(getEncodedPassword("rachid123"));
		encadrant2.setDepartement(dep_info);
		encadrant2.setRoles(encadrantRoles);
		encadrantDao.save(encadrant2);
		
        // Ajouter un encadrant	n3
		
		Encadrant encadrant3 = new Encadrant();
		encadrant3.setNom("AKSISSOU");
		encadrant3.setPrenom("Mustapha");
		encadrant3.setEmail("mustapha.aksissou@uae.ac.ma");
		encadrant3.setPassword(getEncodedPassword("mustapha123"));
		encadrant3.setDepartement(dep_biologie);
		Set<Role> encadrantRoles3 = new HashSet<>();
		encadrantRoles3.add(encadrantRole);
		encadrant3.setRoles(encadrantRoles3);
		encadrantDao.save(encadrant3);
		
		         // Ajouter un encadrant n4
		
		Encadrant encadrant4 = new Encadrant();
		encadrant4.setNom("AGHMIZ");
		encadrant4.setPrenom("Ali ");
		encadrant4.setEmail("ali.aghmiz@uae.ac.ma");
		encadrant4.setPassword(getEncodedPassword("ali123"));
		encadrant4.setDepartement(dep_chimie);
		Set<Role> encadrantRoles4 = new HashSet<>();
		encadrantRoles4.add(encadrantRole);
		encadrant4.setRoles(encadrantRoles4);
		encadrantDao.save(encadrant4);
		
		
		// Ajouter un encadrant n6
		
		Encadrant encadrant6 = new Encadrant();
		encadrant6.setNom("YEBARI");
		encadrant6.setPrenom("Naji");
		encadrant6.setEmail("naji.yebari@uae.ac.ma");
		encadrant6.setPassword(getEncodedPassword("naji123"));
		encadrant6.setDepartement(dep_math);
		Set<Role> encadrantRoles6 = new HashSet<>();
		encadrantRoles6.add(encadrantRole);
		encadrant6.setRoles(encadrantRoles6);
		encadrantDao.save(encadrant6);
		
		
		        // Ajouter un encadrant n7
		
		Encadrant encadrant7 = new Encadrant();
		encadrant7.setNom("EL MOUTCHOU");
		encadrant7.setPrenom("Brahim");
		encadrant7.setEmail("brahim.elmoutchou@uae.ac.ma");
		encadrant7.setPassword(getEncodedPassword("brahim123"));
		encadrant7.setDepartement(dep_biologie);
		Set<Role> encadrantRoles7 = new HashSet<>();
		encadrantRoles7.add(encadrantRole);
		encadrant7.setRoles(encadrantRoles7);
		encadrantDao.save(encadrant7);
		
		
		        // Ajouter un encadrant n8
		
		Encadrant encadrant8 = new Encadrant();
		encadrant8.setNom("LAHLAOUTI");
		encadrant8.setPrenom("MOHAMMED");
		encadrant8.setEmail("mohammed.lahlaouti@uae.ac.ma");
		encadrant8.setPassword(getEncodedPassword("mohammed123"));
		encadrant8.setDepartement(dep_physique);
		Set<Role> encadrantRoles8 = new HashSet<>();
		encadrantRoles8.add(encadrantRole);
		encadrant8.setRoles(encadrantRoles8);
		encadrantDao.save(encadrant8);
		
		        // Ajouter un encadrant n9
		
		Encadrant encadrant9 = new Encadrant();
		encadrant9.setNom("AMMARI");
		encadrant9.setPrenom("Laila");
		encadrant9.setEmail("laila.ammari@uae.ac.ma");
		encadrant9.setPassword(getEncodedPassword("laila123"));
		encadrant9.setDepartement(dep_fr);
		Set<Role> encadrantRoles9 = new HashSet<>();
		encadrantRoles9.add(encadrantRole);
		encadrant9.setRoles(encadrantRoles9);
		encadrantDao.save(encadrant9);

		
		// Ajouter sujet 1
		Sujet sujet1 = new Sujet();
		sujet1.setTitre("Application mobile gestion de temps");
		sujet1.setDescription("Developpement Application mobile gestion de temps");
		sujet1.setNombreMembres(3);
		sujet1.setChoisie("non");
		sujet1.setEncadrant(encadrant1);
		sujetDao.save(sujet1);
		
		// Ajouter sujet 2
		Sujet sujet2 = new Sujet();
		sujet2.setTitre("Application web gestion du stock");
		sujet2.setDescription("Developpement Application web gestion du stock");
		sujet2.setNombreMembres(3);
		sujet2.setChoisie("non");
		sujet2.setEncadrant(encadrant1);
		sujetDao.save(sujet2);
		
		// Ajouter sujet 3
		Sujet sujet3 = new Sujet();
		sujet3.setTitre("Application mobile gestion de pharmacie");
		sujet3.setDescription("Developpement Application mobile gestion de pharmacie");
		sujet3.setNombreMembres(2);
		sujet3.setChoisie("non");
		sujet3.setEncadrant(encadrant2);
		sujetDao.save(sujet3);
		
		// Ajouter sujet 4
		Sujet sujet4 = new Sujet();
		sujet4.setTitre("Application desktop gestion de hospital");
		sujet4.setDescription("Developpement Application desktop gestion de hospital");
		sujet4.setNombreMembres(4);
		sujet4.setChoisie("non");
		sujet4.setEncadrant(encadrant1);
		sujetDao.save(sujet4);
		
        // Ajouter sujet 5
		Sujet sujet5 = new Sujet();
		sujet5.setTitre("Amélioration de la prise en charge des patients en soins palliatifs aux urgences");
		sujet5.setDescription("Améliorer de la prise en charge des patients en soins palliatifs aux urgences");
		sujet5.setNombreMembres(3);
		sujet5.setChoisie("non");
		sujet5.setEncadrant(encadrant3);
		sujetDao.save(sujet5);

        // Ajouter sujet 6
		Sujet sujet6 = new Sujet();
		sujet6.setTitre("Approches d’intégrations mixtes monolithique/hybride de convertisseurs – puces multi‐pôles et assemblages");
		sujet6.setDescription("Approches d’intégrations mixtes monolithique/hybride de convertisseurs – puces multi‐pôles et assemblages");
		sujet6.setNombreMembres(3);
		sujet6.setChoisie("non");
		sujet6.setEncadrant(encadrant3);
		sujetDao.save(sujet6);

        // Ajouter sujet 7
		Sujet sujet7 = new Sujet();
		sujet7.setTitre("Etude d’un modèle dissymétrique au niveau des atomes de bore ");
		sujet7.setDescription("Etude d’un modèle dissymétrique au niveau des atomes de bore ");
		sujet7.setNombreMembres(2);
		sujet7.setChoisie("non");
		sujet7.setEncadrant(encadrant4);
		sujetDao.save(sujet7);

        // Ajouter sujet 8
		Sujet sujet8 = new Sujet();
		sujet8.setTitre("Étude théorique de propriétés photophysiques et photochimiques de complexes de ruthénium");
		sujet8.setDescription("Étude théorique de propriétés photophysiques et photochimiques de complexes de ruthénium");
		sujet8.setNombreMembres(3);
		sujet8.setChoisie("non");
		sujet8.setEncadrant(encadrant4);
		sujetDao.save(sujet8);

        // Ajouter sujet 9
		Sujet sujet9= new Sujet();
		sujet9.setTitre("Étude semiclassique des modes de pression chaotiques ");
		sujet9.setDescription("Étude semiclassique des modes de pression chaotiques ");
		sujet9.setNombreMembres(2);
		sujet9.setChoisie("non");
		sujet9.setEncadrant(encadrant6);
		sujetDao.save(sujet9);

		// Ajouter sujet 10
		Sujet sujet10 = new Sujet();
		sujet10.setTitre("Retour sur le comportement mécanique réversible du ballast – Modélisation et expérimentation");
		sujet10.setDescription("Retour sur le comportement mécanique réversible du ballast – Modélisation et expérimentation");
		sujet10.setNombreMembres(3);
		sujet10.setChoisie("non");
		sujet10.setEncadrant(encadrant6);
		sujetDao.save(sujet10);
		
        // Ajouter sujet 11
		Sujet sujet11 = new Sujet();
		sujet11.setTitre("Etude des invertebres et microfossiles");
		sujet11.setDescription("Etude des invertebres et microfossiles");
		sujet11.setNombreMembres(4);
		sujet11.setChoisie("non");
		sujet11.setEncadrant(encadrant7);
		sujetDao.save(sujet11);
		
        // Ajouter sujet 12
		Sujet sujet12 = new Sujet();
		sujet12.setTitre("Caractérisation de la résistance au cisaillement et comportement des interfaces entre béton et fondation rocheuse des structures hydrauliques");
		sujet12.setDescription("Caractérisation de la résistance au cisaillement et comportement des interfaces entre béton et fondation rocheuse des structures hydrauliques");
		sujet12.setNombreMembres(2);
		sujet12.setChoisie("non");
		sujet12.setEncadrant(encadrant7);
		sujetDao.save(sujet12);
		
		
        // Ajouter sujet 13
		Sujet sujet13 = new Sujet();
		sujet13.setTitre("Etude de La Consommation de L'énergie Électrique");
		sujet13.setDescription("Etude de La Consommation de L'énergie Électrique");
		sujet13.setNombreMembres(3);
		sujet13.setChoisie("non");
		sujet13.setEncadrant(encadrant8);
		sujetDao.save(sujet12);
		
		
        // Ajouter sujet 14
		Sujet sujet14 = new Sujet();
		sujet14.setTitre("La diffraction des rayons X");
		sujet14.setDescription("La diffraction des rayons X");
		sujet14.setNombreMembres(2);
		sujet14.setChoisie("non");
		sujet14.setEncadrant(encadrant8);
		sujetDao.save(sujet14);
		
		// Ajouter une Groupe
		
		Groupe groupe1 = new Groupe();
		groupe1.setNombreMembresGroupe(3);
		groupe1.setEncadre("non");
		List<Sujet> groupe1Sujets = new ArrayList<Sujet>();
		groupe1Sujets.add(sujet1);
		groupe1Sujets.add(sujet2);
		groupe1.setSujetsList(groupe1Sujets);
		groupeDao.save(groupe1);
		
		Groupe groupe2 = new Groupe();
		groupe2.setNombreMembresGroupe(2);
		groupe2.setEncadre("non");
		List<Sujet> groupe2Sujets = new ArrayList<Sujet>();
		groupe2Sujets.add(sujet1);
		groupe2Sujets.add(sujet3);
		groupe2.setSujetsList(groupe2Sujets);
		groupeDao.save(groupe2);
		
		Groupe groupe3 = new Groupe();
		groupe3.setNombreMembresGroupe(4);
		groupe3.setEncadre("non");
		List<Sujet> groupe3Sujets = new ArrayList<Sujet>();
		groupe3Sujets.add(sujet1);
		groupe3Sujets.add(sujet2);
		groupe3Sujets.add(sujet4);
		groupe3.setSujetsList(groupe3Sujets);
		groupeDao.save(groupe3);
		
		Groupe groupe4 = new Groupe();
		groupe4.setNombreMembresGroupe(3);
		groupe4.setEncadre("non");
		List<Sujet> groupe4Sujets = new ArrayList<Sujet>();
		groupe4Sujets.add(sujet1);
		groupe4Sujets.add(sujet4);
		groupe4.setSujetsList(groupe4Sujets);
		groupeDao.save(groupe4);
		
		// Les etudiants
		Set<Role> etudiantRoles = new HashSet<>();
		etudiantRoles.add(etudiantRole);
		
		// Etudiant 1
		Etudiant etudiant1 = new Etudiant();
		etudiant1.setCodeApogee(20181203);
		etudiant1.setNom("NAJJI");
		etudiant1.setPrenom("Karim");
		etudiant1.setFiliere(smi);
		etudiant1.setEmail("karim.najji@etu.uae.ac.ma");
		etudiant1.setPassword(getEncodedPassword("karim123"));
		etudiant1.setRoles(etudiantRoles);
		etudiant1.setGroupe(groupe1);
		etudiantDao.save(etudiant1);
		
		// Etudiant 2
		Etudiant etudiant2 = new Etudiant();
		etudiant2.setCodeApogee(20193636);
		etudiant2.setNom("SELLAMI");
		etudiant2.setPrenom("Amine");
		etudiant2.setFiliere(smi);
		etudiant2.setEmail("amine.sellami@etu.uae.ac.ma");
		etudiant2.setPassword(getEncodedPassword("amine123"));
		etudiant2.setRoles(etudiantRoles);
		etudiant2.setGroupe(groupe1);
		etudiantDao.save(etudiant2);
		
		// Etudiant 3
		Etudiant etudiant3 = new Etudiant();
		etudiant3.setCodeApogee(20186326);
		etudiant3.setNom("SAGRA");
		etudiant3.setPrenom("Soufiane");
		etudiant3.setFiliere(smi);
		etudiant3.setEmail("soufiane.sagra@etu.uae.ac.ma");
		etudiant3.setPassword(getEncodedPassword("soufiane123"));
		etudiant3.setRoles(etudiantRoles);
		etudiant3.setGroupe(groupe2);
		etudiantDao.save(etudiant3);

		
		// Etudiant 4
		Etudiant etudiant4 = new Etudiant();
		etudiant4.setCodeApogee(20181113);
		etudiant4.setNom("ALLAMI");
		etudiant4.setPrenom("Aymane");
		etudiant4.setFiliere(smi);
		etudiant4.setEmail("aymane.allami@etu.uae.ac.ma");
		etudiant4.setPassword(getEncodedPassword("aymane123"));
		etudiant4.setRoles(etudiantRoles);
		etudiant4.setGroupe(groupe2);
		etudiantDao.save(etudiant4);
		
		// Etudiant 5
		Etudiant etudiant5 = new Etudiant();
		etudiant5.setCodeApogee(20189966);
		etudiant5.setNom("JABRANE");
		etudiant5.setPrenom("Ali");
		etudiant5.setFiliere(smi);
		etudiant5.setEmail("ali.jabrane@etu.uae.ac.ma");
		etudiant5.setPassword(getEncodedPassword("ali123"));
		etudiant5.setRoles(etudiantRoles);
		etudiant5.setGroupe(groupe3);
		etudiantDao.save(etudiant5);
		
		// Etudiant 6
		Etudiant etudiant6 = new Etudiant();
		etudiant6.setCodeApogee(20183333);
		etudiant6.setNom("ELGAZI");
		etudiant6.setPrenom("Mohamed");
		etudiant6.setFiliere(smi);
		etudiant6.setEmail("elgazi.mohamed@etu.uae.ac.ma");
		etudiant6.setPassword(getEncodedPassword("ghazi123"));
		etudiant6.setRoles(etudiantRoles);
		etudiant6.setGroupe(groupe3);
		etudiantDao.save(etudiant6);
		
		// Etudiant 7
		Etudiant etudiant7 = new Etudiant();
		etudiant7.setCodeApogee(20184466);
		etudiant7.setNom("FADALI");
		etudiant7.setPrenom("Mounir");
		etudiant7.setFiliere(smi);
		etudiant7.setEmail("mounir.fadali@etu.uae.ac.ma");
		etudiant7.setPassword(getEncodedPassword("mounir123"));
		etudiant7.setRoles(etudiantRoles);
		etudiant7.setGroupe(groupe3);
		etudiantDao.save(etudiant7);
		
		// Etudiant 8
		Etudiant etudiant8 = new Etudiant();
		etudiant8.setCodeApogee(20193758);
		etudiant8.setNom("BAKALI");
		etudiant8.setPrenom("Mohsen");
		etudiant8.setFiliere(smi);
		etudiant8.setEmail("mohsen.bakali@etu.uae.ac.ma");
		etudiant8.setPassword(getEncodedPassword("mohsen123"));
		etudiant8.setRoles(etudiantRoles);
		etudiant8.setGroupe(null);
		etudiantDao.save(etudiant8);
		
		// Etudiant 9
		Etudiant etudiant9 = new Etudiant();
		etudiant9.setCodeApogee(20193759);
		etudiant9.setNom("KADARI");
		etudiant9.setPrenom("Ahmed");
		etudiant9.setFiliere(smi);
		etudiant9.setEmail("ahmed.kadari@etu.uae.ac.ma");
		etudiant9.setPassword(getEncodedPassword("ahmed123"));
		etudiant9.setRoles(etudiantRoles);
		etudiant9.setGroupe(groupe4);
		etudiantDao.save(etudiant9);
		
		// Etudiant 10
		Etudiant etudiant10 = new Etudiant();
		etudiant10.setCodeApogee(20193765);
		etudiant10.setNom("SAMARI");
		etudiant10.setPrenom("Soufiane");
		etudiant10.setFiliere(smi);
		etudiant10.setEmail("soufiane.samari@etu.uae.ac.ma");
		etudiant10.setPassword(getEncodedPassword("soufiane123"));
		etudiant10.setRoles(etudiantRoles);
		etudiant10.setGroupe(groupe4);
		etudiantDao.save(etudiant10);
		
		// Etudiant 11
		Etudiant etudiant11 = new Etudiant();
		etudiant11.setCodeApogee(20152523);
		etudiant11.setNom("GROUNI");
		etudiant11.setPrenom("Samira");
		etudiant11.setFiliere(smi);
		etudiant11.setEmail("samira.grouni@etu.uae.ac.ma");
		etudiant11.setPassword(getEncodedPassword("samira123"));
		etudiant11.setRoles(etudiantRoles);
		etudiant11.setGroupe(groupe4);
		etudiantDao.save(etudiant11);
	}
	
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
	
}
