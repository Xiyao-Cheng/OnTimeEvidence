package com.rec.orch.repo;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rec.orch.model.RecOrchestratorProcess;

@Repository
public interface RecOrchestratorProcessRepository extends CrudRepository<RecOrchestratorProcess, Long>{

	@Query("SELECT u FROM RecOrchestratorProcess u WHERE u.recId = :recId and u.clientId= :clientId")
	List<RecOrchestratorProcess> findAllByRecIdClientId(@Param("recId") Long recId, @Param("clientId") Long clientId);

	
	@Modifying
	@Transactional
	@Query("update RecOrchestratorProcess u set u.status= :status WHERE u.id= :id")
	void updateProcessStatus(Long id, String status);
	
	@Modifying
	@Transactional
	@Query("update RecOrchestratorProcess u set u.status= :status, u.output=:output,"
			+ " u.lastExecuted=:lastExecuted WHERE u.id= :id")
	void updateProcessStatusDone(Long id, String status, String output, Timestamp lastExecuted);

	@Modifying
	@Transactional
	@Query("update RecOrchestratorProcess u set u.status='N' WHERE "
			+ " u.clientId=:clientId and  u.recId= :recId")
	void updateRecommenderOrchProcess(Long recId, Long clientId);
	
}
