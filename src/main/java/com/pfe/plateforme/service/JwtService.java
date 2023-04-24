package com.pfe.plateforme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfe.plateforme.dao.EncadrantDao;
import com.pfe.plateforme.dao.EtudiantDao;
import com.pfe.plateforme.entity.Encadrant;
import com.pfe.plateforme.entity.Etudiant;
import com.pfe.plateforme.entity.JwtRequest;
import com.pfe.plateforme.entity.JwtResponse;
import com.pfe.plateforme.util.JwtUtil;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
		
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EtudiantDao etudiantDao;
    
    @Autowired
    private EncadrantDao encadrantDao;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    
    
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
    	
    	String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();
        
        String emailDomainName = email.split("@")[1];
        
        authenticate(email, password);
        
        if(emailDomainName.equals("etu.uae.ac.ma")) {
            UserDetails userDetails = loadUserByUsername(email);
            String newGeneratedToken = jwtUtil.generateToken(userDetails);
            Etudiant etudiant = etudiantDao.findByEmail(email).get();
            return new JwtResponse((Etudiant)etudiant, newGeneratedToken);
        }
        else if (emailDomainName.equals("uae.ac.ma")) {
            UserDetails userDetails = loadUserByUsername(email);
            String newGeneratedToken = jwtUtil.generateToken(userDetails);
            Encadrant encadrant = encadrantDao.findByEmail(email).get();
            return new JwtResponse((Encadrant)encadrant, newGeneratedToken);        	
        }
		return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
    	String emailDomainName = email.split("@")[1];
    	
    	if(emailDomainName.equals("etu.uae.ac.ma")) {
    		
    		Etudiant etudiant = etudiantDao.findByEmail(email).get();
    		
            if (etudiant != null) {
                return new org.springframework.security.core.userdetails.User(
                        etudiant.getEmail(),
                        etudiant.getPassword(),
                        getAuthorityEtudiant(etudiant)
                );
            } else {
            	System.out.println("Etudiant introuvable avec cet email");
                throw new UsernameNotFoundException("Etudiant introuvable avec cet email");
            }
    	}
    	
    	else if (emailDomainName.equals("uae.ac.ma")) {
    		Encadrant encadrant = encadrantDao.findByEmail(email).get();
    		
            if (encadrant != null) {
                return new org.springframework.security.core.userdetails.User(
                		encadrant.getEmail(),
                		encadrant.getPassword(),
                		getAuthorityEncadrant(encadrant)
                );
            } else {
            	System.out.println("Encadrant introuvable avec cet email");
                throw new UsernameNotFoundException("Encadrant introuvable avec cet email");
            }
    	}
		return null;
    }

    private Set<SimpleGrantedAuthority> getAuthorityEtudiant(Etudiant etudiant) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        etudiant.getRoles().forEach( role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
    
    private Set<SimpleGrantedAuthority> getAuthorityEncadrant(Encadrant encadrant) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        encadrant.getRoles().forEach( role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
}
