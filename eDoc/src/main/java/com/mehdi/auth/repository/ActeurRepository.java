package com.mehdi.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehdi.auth.models.Acteur;



public interface ActeurRepository extends JpaRepository<Acteur, Long>{
	Optional<Acteur> findByUsername(String username);

	Boolean existsByUsername(String username);
}
