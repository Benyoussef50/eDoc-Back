package com.mehdi.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mehdi.auth.models.Chantier;



public interface ChantierRepository extends JpaRepository<Chantier, Long> {

	
	
	@Query(value="SELECT c.* from `bgc__fiche_chantier` c\n" + 
			"INNER JOIN `bgc__acteur_chantier` ac\n" + 
			"INNER JOIN `bgc__acteur` a \n" + 
			"ON c.id = ac.chantier_id AND ac.acteur_id = a.id \n" + 
			"where username = ?1", nativeQuery = true)
	public List<Chantier> findChantierByUsername(String username);
}
