package com.mehdi.auth.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.mehdi.auth.models.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>, JpaSpecificationExecutor<Plan> {
	@Query(value = "SELECT p.* from `bgcp_plan_appro` p " + "INNER JOIN `bgc__fiche_chantier` c "
			+ "ON p.code_chantier = c.id where bgcp_code_chantier = ?1", nativeQuery = true)
	public List<Plan> findPlanByCodeChantier(String codechantier);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM `bgcp_plan_appro` WHERE `id`=?1", nativeQuery = true)
	public void deleteByIdPlan(Long idplan);
}
