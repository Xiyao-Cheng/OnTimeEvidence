package com.rec.rwc.service;

import java.util.List;

import com.rec.rwc.model.RecProcessData;

public interface RecWorkflowService {

	public List<RecProcessData> getAllProcessDetails(Long recId);

	public RecProcessData saveProcess(RecProcessData request);

	public void deleteProcess(Long recProcessId);

	public RecProcessData getProcessDetails(Long processId);

	public int updateProcessStatus(Long processId, String status);

	public RecProcessData editProcess(RecProcessData process);

}
