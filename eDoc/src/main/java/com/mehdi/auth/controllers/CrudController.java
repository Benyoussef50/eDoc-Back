package com.mehdi.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mehdi.auth.models.Chantier;
import com.mehdi.auth.repository.ChantierRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(com.mehdi.auth.constants.WebConstants.CRUD)
public class CrudController {

	@Autowired
	ChantierRepository chantierRepository;
	
	@GetMapping("/chantiers/{username}")
    @PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public List<Chantier> getAll(@PathVariable("username") String username) {
		List<Chantier> lc = chantierRepository.findChantierByUsername(username);
		return  lc;
			}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADM')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
