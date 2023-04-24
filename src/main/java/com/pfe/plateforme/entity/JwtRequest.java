package com.pfe.plateforme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor
public class JwtRequest {
	
    private String email;
    private String password;
	
}
