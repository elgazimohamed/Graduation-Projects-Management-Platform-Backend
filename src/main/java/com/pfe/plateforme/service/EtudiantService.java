package com.pfe.plateforme.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.EtudiantDao;
import com.pfe.plateforme.dao.FiliereDao;
import com.pfe.plateforme.dao.RoleDao;
import com.pfe.plateforme.entity.Etudiant;
import com.pfe.plateforme.entity.EtudiantRegistrationRequest;
import com.pfe.plateforme.entity.Filiere;
import com.pfe.plateforme.entity.Groupe;
import com.pfe.plateforme.entity.Role;
import com.pfe.plateforme.entity.Sujet;

@Service
public class EtudiantService {
		
	@Autowired
	private EtudiantDao etudiantDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private GroupeService groupeService;
	
	@Autowired
	private FiliereDao filiereDao;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
//    public List<Etudiant> getEtudiantsInGroupe(Long idGroupe) {
//        return etudiantDao
//        		.findAll()
//        		.stream()
//        		.filter(etudiant -> etudiant.getGroupe().getId().equals(idGroupe)).collect(Collectors.toList());
//    }
    
    public Etudiant getEtudiant(Long id) {
    	return etudiantDao.findById(id).get();
    }
    
    public List<Etudiant> getEtudiantsByGroupe(Long idGroupe) {
        List<Etudiant> result = new ArrayList<>();
        for (Etudiant etudiant : etudiantDao.findAll()) {
            Groupe groupe = etudiant.getGroupe();
            if (groupe != null && groupe.getId().equals(idGroupe)) {
                result.add(etudiant);
            }
        }
        return result;
    }
    
    public void addRolePourEtudiants(Long idGroupe) {
    	List<Etudiant> etudiants = getEtudiantsByGroupe(idGroupe);
    	
    	for (Etudiant etud : etudiants) {
    		System.out.println(etud.getNom() + " " + etud.getPrenom());
    	}
    	
    	for (Etudiant etudiant : etudiants) {
    		Role role1 = roleDao.findById(1l).get();
    		Role role2 = roleDao.findById(3l).get();
    		Set<Role> roles = new HashSet<Role>();
    		roles.add(role1);
    		roles.add(role2);
    		etudiantDao.save(etudiant);
    		etudiant.setRoles(roles);
    	}
    }
    
    public List<List<Etudiant>> getAllEtudiantsByGroupesBySujet(Long idSujet) {
        List<Groupe> groupes = groupeService.getAllGroupesBySujet(idSujet);
        //System.out.println("groupes " + groupes);
        List<List<Etudiant>> allEtudiants = new ArrayList<>();
        groupes.forEach(groupe -> allEtudiants.add(getEtudiantsByGroupe(groupe.getId())));
        return allEtudiants;
    }
    
    // Register Etudiant
    public Etudiant registerEtudiant(EtudiantRegistrationRequest request) {
    	
    	Etudiant etudiant = new Etudiant();
    	etudiant.setCodeApogee(request.getCodeApogee());
    	etudiant.setNom(request.getNom());
    	etudiant.setPrenom(request.getPrenom());
    	etudiant.setEmail(request.getEmail());
    	
    	String encodedPassword = getEncodedPassword(request.getPassword());
    	etudiant.setPassword(encodedPassword);
    	
    	Filiere filiere = filiereDao.findById(request.getIdFiliere()).get();
    	etudiant.setFiliere(filiere);
    	
		Set<Role> roles = new HashSet<>();
    	Role etudiantRole = roleDao.findByRoleName("etudiant");
    	roles.add(etudiantRole);
    	etudiant.setRoles(roles);
    	
    	Etudiant savedEtudiant = etudiantDao.save(etudiant);
    	
    	return savedEtudiant;
    }
    
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
}
