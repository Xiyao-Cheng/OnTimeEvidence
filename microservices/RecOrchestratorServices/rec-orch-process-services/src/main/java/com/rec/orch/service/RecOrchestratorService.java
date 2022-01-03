package com.rec.orch.service;

import java.sql.Timestamp;
import java.util.List;

import com.rec.orch.model.RecOrchestratorClient;
import com.rec.orch.model.RecOrchestratorProcess;
import com.rec.orch.model.RecProcessData;

public interface RecOrchestratorService {

	public List<RecOrchestratorClient> getAllClientsWithRecommender();

	public RecOrchestratorClient addRecommenderForClient(RecOrchestratorClient recOrchestratorClient);

	public List<RecOrchestratorProcess> getProcessConfigDetails(Long recId, Long clientId);

	public List<RecProcessData> getProcessDetails(Long recId);

	public RecOrchestratorProcess saveProcessOrch(RecOrchestratorProcess orchestratorProcess);

	public void updateProcessStatus(Long processOrchId, String status);

	public void deleteProcessOrch(Long deleteProcessOrch);

	public Long[] getRecommenderListClient(Long clientId);

	public void updateRecommenderOrchClient(Long recId, Long clientId, String status, Timestamp startdt, Timestamp enddt) ;

	public void updateProcessStatus(Long processOrchId, String status, String output, Timestamp timestamp);
	
}
