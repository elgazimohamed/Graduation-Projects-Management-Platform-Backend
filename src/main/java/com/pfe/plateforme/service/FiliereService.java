package com.pfe.plateforme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.FiliereDao;
import com.pfe.plateforme.entity.Filiere;

@Service
public class FiliereService {

	@Autowired
	private FiliereDao filiereDao;
	
	public List<Filiere> getAllFilieres(){
		return filiereDao.findAll();
	}
}
