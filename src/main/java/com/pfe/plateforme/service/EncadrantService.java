package com.pfe.plateforme.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.DepartementDao;
import com.pfe.plateforme.dao.EncadrantDao;
import com.pfe.plateforme.dao.EtudiantDao;
import com.pfe.plateforme.dao.GroupeDao;
import com.pfe.plateforme.dao.RoleDao;
import com.pfe.plateforme.dao.SujetDao;
import com.pfe.plateforme.entity.AccepteGroupeRequest;
import com.pfe.plateforme.entity.Departement;
import com.pfe.plateforme.entity.Encadrant;
import com.pfe.plateforme.entity.EncadrantRegistrationRequest;
import com.pfe.plateforme.entity.Etudiant;
import com.pfe.plateforme.entity.Groupe;
import com.pfe.plateforme.entity.GroupeSujet;
import com.pfe.plateforme.entity.Role;
import com.pfe.plateforme.entity.Sujet;

@Service
public class EncadrantService {

	@Autowired
	private EncadrantDao encadrantDao;
	
	@Autowired
	private DepartementDao departementDao;
	
	@Autowired
	private SujetDao sujetDao;
	
	@Autowired
	private GroupeDao groupeDao;
	
	@Autowired
	private GroupeSujetService groupeSujetService;
	
	@Autowired
	private GroupeService groupeService;
	
	@Autowired
	private EtudiantService etudiantService;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleDao roleDao;
	
    public Encadrant getEncadrant(Long id) {
    	return encadrantDao.findById(id).get();
    }
    
    public List<Etudiant> getEtudiantByGroupeAccepte(Long idSujet){
    	List<Etudiant> etudiants = null;
    	
    	System.out.println(idSujet);
    	
    	GroupeSujet groupeSujet = groupeSujetService.getGroupeSujet(idSujet);
    	
    	System.out.println(groupeSujet);    	
    	
    	Groupe groupe = groupeSujet.getGroupe();
    	
    	etudiants = etudiantService.getEtudiantsByGroupe(groupe.getId());
    	
    	System.out.println("Etudiant : " + etudiants.get(0).getNom() + " " + etudiants.get(0).getPrenom());
    	
    	if(etudiants == null)
    		return null;
    	return etudiants;
    }
    
    public void accepterGroupe(AccepteGroupeRequest request) {

    	
    	Sujet sujet = sujetDao.findById(request.getIdSujet()).get();
    	sujet.setChoisie("oui");
    	sujetDao.save(sujet);
    	
    	Groupe groupeAccepte = groupeDao.findById(request.getIdGroupe()).get();
    	groupeAccepte.setEncadre("oui");
    	groupeDao.save(groupeAccepte);
    	
    	groupeSujetService.updateStatut(request.getIdGroupe(), request.getIdSujet(), "accepte");
    	
    	List<Groupe> listGroupes = groupeService.getAllGroupesBySujet(request.getIdSujet());
    	
    	for(Groupe groupe: listGroupes) {
    		if(groupe.getId() != request.getIdGroupe()) {
    			groupeSujetService.updateStatut(groupe.getId(), request.getIdSujet(), "refuse");
    		}
    	}
    	
    	//etudiantService.addRolePourEtudiants(request.getIdGroupe());
    }
    
    // Enregistrer un nouveau encadrant 
    public Encadrant registerEncadrant(EncadrantRegistrationRequest request) {
    	
    	Encadrant encadrant = new Encadrant();
    	
    	encadrant.setNom(request.getNom());
    	encadrant.setPrenom(request.getPrenom());
    	encadrant.setEmail(request.getEmail());
    	
    	String encodedPassword = getEncodedPassword(request.getPassword());
    	encadrant.setPassword(encodedPassword);
    	
    	Departement departement = departementDao.findById(request.getIdDepartement()).get();
    	encadrant.setDepartement(departement);
    	
		Set<Role> roles = new HashSet<>();
    	Role encadrantRole = roleDao.findByRoleName("encadrant");
    	roles.add(encadrantRole);
    	encadrant.setRoles(roles);
    	
    	Encadrant savedEncadrant = encadrantDao.save(encadrant);
    	
    	return savedEncadrant;
    }
	
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
}
