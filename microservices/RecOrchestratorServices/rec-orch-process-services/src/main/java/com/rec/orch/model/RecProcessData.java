package com.rec.orch.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rec_process")
public class RecProcessData {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long recProcessId;
	
	@ManyToOne(cascade = CascadeType.MERGE )
	@JoinColumn(name = "rec_id")
	@JsonIgnore
	private RecommendApps recommendApps;
	
	@Transient
	private Long recId;
	
	@Column(name = "process_name")
	private String processName;
	
	@Column(name = "process_details")
	private String processDetails;
	
	@Column(name = "api_url")
	private String apiUrl;
	
	@Column(name = "input_paramater")
	private String inputParamater;

	
	public String getInputParamater() {
		return inputParamater;
	}

	public void setInputParamater(String inputParamater) {
		this.inputParamater = inputParamater;
	}

	

	public Long getRecProcessId() {
		return recProcessId;
	}

	public void setRecProcessId(Long recProcessId) {
		this.recProcessId = recProcessId;
	}

	public RecommendApps getRecommendApps() {
		return recommendApps;
	}

	public void setRecommendApps(RecommendApps recommendApps) {
		this.recommendApps = recommendApps;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getProcessDetails() {
		return processDetails;
	}

	public void setProcessDetails(String processDetails) {
		this.processDetails = processDetails;
	}

	public Long getRecId() {
		return recId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}

	
	
}
