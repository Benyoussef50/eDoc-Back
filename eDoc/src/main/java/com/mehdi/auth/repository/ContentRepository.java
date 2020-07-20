package com.mehdi.auth.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mehdi.auth.models.Content;;

public interface ContentRepository extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content>{
	 
    @Query(value="SELECT c.* from `bgcp_plan_appro` p\n" + 
			   "	INNER JOIN `dmr_content` c\n" + 
			   "	ON p.id = c.parent_id where p.id =?1"
					, nativeQuery = true)
	public List<Content> findAllByIdPlan(Long idplan);
    
    @Query(value="SELECT p.id,c.set_file from `bgcp_plan_appro` p INNER JOIN `dmr_content` c ON p.id = c.parent_id where c.full_format='pdf'AND bgcp_code_chantier = ?1"
					, nativeQuery = true)
	public List<Map<String, Object>> findAllByIdPlanPdf(String codechantier);
    
    @Modifying
    @Transactional
    @Query(value="DELETE FROM `dmr_content` WHERE `parent_id`=?1", nativeQuery = true)
    public void deleteByIdPlan(Long idplan);
}
