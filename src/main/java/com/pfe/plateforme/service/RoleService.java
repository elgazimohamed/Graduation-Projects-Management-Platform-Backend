package com.pfe.plateforme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.RoleDao;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
}
