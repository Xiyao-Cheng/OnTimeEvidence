package com.rec.rwc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rec.rwc.model.RecProcessData;
import com.rec.rwc.repo.RecProcessRepository;

@Service
public class RecWorkflowServiceImpl implements RecWorkflowService {
	
	@Autowired
	RecProcessRepository recProcessRepository;
	

	@Override
	public List<RecProcessData> getAllProcessDetails(Long recId) {
		List<RecProcessData> recProcessDatas = recProcessRepository.findRecId(recId);
		return recProcessDatas;
	}

	@Override
	public RecProcessData saveProcess(RecProcessData request) {
		request = recProcessRepository.save(request);
		return request;
	}
	
	@Override
	public RecProcessData editProcess(RecProcessData request) {
		recProcessRepository.editProcess(request.getInputParamater(),
				request.getApiUrl(), request.getProcessDetails(), request.getProcessName(), request.getRecProcessId());
		return request;
	}
	
	@Override
	public void deleteProcess(Long recProcessId) {
		recProcessRepository.deleteById(recProcessId);
	}

	@Override
	public RecProcessData getProcessDetails(Long processId) {
		RecProcessData processData = recProcessRepository.findById(processId).get();
		return processData;
	}

	public int updateProcessStatus(Long processId, String status) {
		
		return 0;
	}


}
