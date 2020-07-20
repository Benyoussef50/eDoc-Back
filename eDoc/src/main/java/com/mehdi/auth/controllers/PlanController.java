package com.mehdi.auth.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mehdi.auth.models.Content;
import com.mehdi.auth.models.Plan;
import com.mehdi.auth.repository.ChantierRepository;
import com.mehdi.auth.repository.ContentRepository;
import com.mehdi.auth.repository.PlanRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(com.mehdi.auth.constants.WebConstants.CRUD)
public class PlanController {
	@Autowired
	PlanRepository planRepository;

	@Autowired
	ChantierRepository chantierRepository;

	@Autowired
	ContentRepository contentRepo;

	@GetMapping("/plans/{codechantier}")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public List<Plan> getAllPlans(@PathVariable("codechantier") String codechantier) {
		List<Plan> pl = planRepository.findPlanByCodeChantier(codechantier);
		return pl;
	}

	@GetMapping("/plans/content/{id}")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public List<Content> getAllPla(@PathVariable("id") Long id) {
		List<Content> ct = contentRepo.findAllByIdPlan(id);
		return ct;
	}

	@GetMapping("/plans/{codechantier}/contentPdf")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public List<Map<String, Object>> getAllPlaPdf(@PathVariable("codechantier") String codechantier) {
		List<Map<String, Object>> ct = contentRepo.findAllByIdPlanPdf(codechantier);
		return ct;
	}

	@GetMapping("/plan/{id}")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public Optional<Plan> getPlanById(@PathVariable("id") Long id) {
		Optional<Plan> ct = planRepository.findById(id);
		return ct;
	}

	@PostMapping("/addPlan/{codeChantier}")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public Optional<Plan> createComment(@PathVariable(value = "codeChantier") Long chantierId,
			@Valid @RequestBody Plan plan) {
		return chantierRepository.findById(chantierId).map(c -> {
			plan.setChantier(c);
			return planRepository.save(plan);
		});

	}

	/*
	 * @PutMapping("/updatePlan/{planId}/content/")
	 * 
	 * @PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')") public
	 * Optional<Plan>
	 */

	@PutMapping("/updatePlan/{planId}/content/")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')") 
	public Optional<Plan> updatePlan(@PathVariable(value = "planId") Long id, @Valid @RequestBody Plan plan){
		return planRepository.findById(id).map(p -> {
			p.setBgcp_titre1(plan.getBgcp_titre1());
		    p.setBgcp_lib_indice( plan.getBgcp_lib_indice());
		    p.setBgc_nom_affaire(plan.getBgc_nom_affaire());
		    p.setSpec_phase(plan.getSpec_phase());
		    p.setSpec_emetteur(plan.getSpec_emetteur());
		    p.setSpec_zone(plan.getSpec_zone());
		    p.setSpec_lot(plan.getSpec_lot());
		    p.setSpec_niveau(plan.getSpec_niveau());
		    p.setSpec_type(plan.getSpec_type());
		    p.setSpec_conb(plan.getSpec_conb());
		    p.setSpec_numero(plan.getSpec_numero());
		    p.setBgcp_indice(plan.getBgcp_indice());
		    p.setBgcp_date_indice_(plan.getBgcp_date_indice_());
			p.setBgcp_statut(plan.getBgcp_statut());
			p.setBgcp_date_reel_emission(plan.getBgcp_date_reel_emission());
			
			return planRepository.save(p);
		});
	}
	
	@DeleteMapping("/deletePlan/{planId}")
	@PreAuthorize("hasRole('ROLE_UT') or hasRole('ROLE_ADM')")
	public void deletePlan(@PathVariable Long planId) {
		contentRepo.deleteByIdPlan(planId);
		planRepository.deleteByIdPlan(planId);
	}
}
