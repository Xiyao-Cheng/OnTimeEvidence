package com.rec.orch.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rec.orch.model.RecOrchestratorClient;
import com.rec.orch.model.RecOrchestratorProcess;
import com.rec.orch.model.RecProcessData;
import com.rec.orch.repo.RecOrchestratorClientRepository;
import com.rec.orch.repo.RecOrchestratorProcessRepository;
import com.rec.orch.repo.RecProcessRepository;

@Service
public class RecOrchestratorServiceImpl implements RecOrchestratorService {
	
	@Autowired
	RecOrchestratorClientRepository recOrchClientRepo;
	
	@Autowired
	RecOrchestratorProcessRepository recOrchProcessRepo;
	
	@Autowired
	RecProcessRepository recPrcRepository;
	
	@Override
	public List<RecOrchestratorClient> getAllClientsWithRecommender() {
		
		List<RecOrchestratorClient> orchClients = (List<RecOrchestratorClient>)recOrchClientRepo.findAll();
		return orchClients;
	}
	
	@Override
	public RecOrchestratorClient addRecommenderForClient(RecOrchestratorClient recOrchestratorClient) {
		
		recOrchestratorClient= recOrchClientRepo.save(recOrchestratorClient);
		
		return recOrchestratorClient;
		
	}

	@Override
	public List<RecOrchestratorProcess> getProcessConfigDetails(Long recId, Long clientId) {
		
		List<RecOrchestratorProcess> orchestratorProcesses = recOrchProcessRepo.findAllByRecIdClientId( recId,  clientId);
		
		
		return orchestratorProcesses;
	}

	@Override
	public List<RecProcessData> getProcessDetails(Long recId) {
		List<RecProcessData> processDatas=  recPrcRepository.findByRecId(recId);
		return processDatas;
	}
	
	@Override
	public RecOrchestratorProcess saveProcessOrch(RecOrchestratorProcess orchestratorProcess) {
		orchestratorProcess = recOrchProcessRepo.save(orchestratorProcess);
		return orchestratorProcess;
	}

	@Override
	public void updateProcessStatus(Long processOrchId, String status) {
		recOrchProcessRepo.updateProcessStatus(processOrchId, status);		
	}

	@Override
	public void updateProcessStatus(Long processOrchId, String status, String output, Timestamp timestamp) {
		recOrchProcessRepo.updateProcessStatusDone(processOrchId, status, output, timestamp);		
	}

	@Override
	public void deleteProcessOrch(Long deleteProcessOrch) {
		recOrchProcessRepo.deleteById(deleteProcessOrch);
	}

	@Override
	public Long[] getRecommenderListClient(Long clientId){
		return recOrchClientRepo.getRecommenderListClient(clientId);
	}

	@Override
	public void updateRecommenderOrchClient(Long recId, Long clientId, String status, Timestamp startdt, Timestamp enddt) {
		recOrchClientRepo.updateRecommenderOrchClient(recId, clientId,status , startdt , enddt);
		
		recOrchProcessRepo.updateRecommenderOrchProcess(recId, clientId);

		
	}
	
}
