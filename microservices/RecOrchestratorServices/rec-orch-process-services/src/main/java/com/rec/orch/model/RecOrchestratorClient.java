package com.rec.orch.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;

@Entity
@Table(name = "rec_orchestrator_client")
public class RecOrchestratorClient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orch_id")
	private Long id;
	
	@Column(name = "client_id")
	private Long clientId;
	
	@Column(name = "rec_id")
	private Long recId;

	@Column(name = "queue_execution_status")
	private String queueExecutionStatus;
	
	@Column(name = "queue_start_date")
	private Timestamp queueStartDt;
	
	@Column(name = "queue_end_date")
	private Timestamp queueEndDt;
	
	
	public String getQueueExecutionStatus() {
		return queueExecutionStatus;
	}

	public void setQueueExecutionStatus(String queueExecutionStatus) {
		this.queueExecutionStatus = queueExecutionStatus;
	}



	public Timestamp getQueueStartDt() {
		return queueStartDt;
	}

	public void setQueueStartDt(Timestamp queueStartDt) {
		this.queueStartDt = queueStartDt;
	}

	public Timestamp getQueueEndDt() {
		return queueEndDt;
	}

	public void setQueueEndDt(Timestamp queueEndDt) {
		this.queueEndDt = queueEndDt;
	}

	public Long getId() {
		return id;
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
	
	
	
}
