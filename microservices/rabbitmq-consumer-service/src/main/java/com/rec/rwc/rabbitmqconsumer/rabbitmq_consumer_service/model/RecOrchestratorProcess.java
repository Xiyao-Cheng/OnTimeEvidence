package com.rec.rwc.rabbitmqconsumer.rabbitmq_consumer_service.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rec_orchestrator_process")
public class RecOrchestratorProcess implements Comparable< RecOrchestratorProcess > {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "client_id")
	private Long clientId;
	
	@Column(name = "rec_id")
	private Long recId;
	
	@Column(name = "process_id")
	private Long processId;
	
	@Transient
	private String processName;
	
	
	@Transient
	private String processUrl;
	
	
	@Column(name = "last_executed")
	private Timestamp lastExecuted;
	
	@Column(name = "status")
	private String status;
	
	
	public Timestamp getLastExecuted() {
		return lastExecuted;
	}





	public void setLastExecuted(Timestamp lastExecuted) {
		this.lastExecuted = lastExecuted;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "input_config")
	private String inputConfig;
	
	@Column(name = "seq_queue_number")
	private Long seqQueueNumber;

	public Long getId() {
		return id;
	}

	
	


	public String getProcessName() {
		return processName;
	}





	public void setProcessName(String processName) {
		this.processName = processName;
	}





	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getRecId() {
		return recId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getInputConfig() {
		return inputConfig;
	}

	public void setInputConfig(String inputConfig) {
		this.inputConfig = inputConfig;
	}

	public Long getSeqQueueNumber() {
		return seqQueueNumber;
	}

	public void setSeqQueueNumber(Long seqQueueNumber) {
		this.seqQueueNumber = seqQueueNumber;
	}
	
	
	
	public String getProcessUrl() {
		return processUrl;
	}
	@Column(name = "output")
	private String output;
	
	public String getOutput() {
		return output;
	}
	
	public void setOutput(String output) {
		this.output = output;
	}





	public void setProcessUrl(String processUrl) {
		this.processUrl = processUrl;
	}





	@Override
    public int compareTo(RecOrchestratorProcess o) {
        return this.getSeqQueueNumber().compareTo(o.getSeqQueueNumber());
    }
}

