package com.mehdi.auth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mehdi.auth.models.Reference;
import com.mehdi.auth.repository.ReferenceRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(com.mehdi.auth.constants.WebConstants.REFS)
public class ReferenceController {
	@Autowired
	ReferenceRepository referenceRepository;
	
	@GetMapping("/references/{idChantier}/{nameRef}")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public List<Reference> getReference(@PathVariable("idChantier") Long id,
			@PathVariable("nameRef") String ref){
		return referenceRepository.findByNameAndChantier(ref, id);
	}
	
}
