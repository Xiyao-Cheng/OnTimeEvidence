package com.rec.orch.repo;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rec.orch.model.RecOrchestratorClient;

@Repository
public interface RecOrchestratorClientRepository extends CrudRepository<RecOrchestratorClient, Long>{

	@Query("SELECT u.recId FROM RecOrchestratorClient u WHERE u.clientId = :clientId")
	Long[] getRecommenderListClient(@Param("clientId") Long clientId);

	
	@Modifying
	@Transactional
	@Query("update RecOrchestratorClient u set u.queueExecutionStatus= :queueExecutionStatus,"
			+ " u.queueStartDt=:queueStartDt, u.queueEndDt = :queueEndDt WHERE u.clientId= :clientId and u.recId = :recId")
	void updateRecommenderOrchClient(@Param("recId") Long recId, @Param("clientId") Long clientId,
			@Param("queueExecutionStatus") String queueExecutionStatus, @Param("queueStartDt") Timestamp queueStartDt,
			@Param("queueEndDt") Timestamp queueEndDt);

}
