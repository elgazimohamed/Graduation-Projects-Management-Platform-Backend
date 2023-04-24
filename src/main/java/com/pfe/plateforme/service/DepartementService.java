package com.pfe.plateforme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.DepartementDao;
import com.pfe.plateforme.entity.Departement;

@Service
public class DepartementService {

	@Autowired
	private DepartementDao departementDao;
	
	
	public List<Departement> getAllDepartement(){
		return departementDao.findAll();
	}
}
