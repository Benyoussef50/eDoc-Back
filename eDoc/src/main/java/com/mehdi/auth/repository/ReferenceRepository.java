package com.mehdi.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.mehdi.auth.models.Reference;

public interface ReferenceRepository extends JpaRepository<Reference, Long>, JpaSpecificationExecutor<Reference>{

	@Query(value = "SELECT * FROM `bgc_table_ref` WHERE `bgc__nom`=?1 AND `id_chantier`=?2", nativeQuery = true)
	public List<Reference> findByNameAndChantier(String name, Long id);
}
