package com.rec.rwc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "recommend_apps")
public class RecommendApps {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rec_id")
	private Long recId;
	
	@Column(name = "app_name")
	private String appName;
	
	@Column(name = "app_des")
	private String appDes;

	
	@Column(name = "app_input_parameter")
	private String inputParamter;
	
	@Column(name = "is_deployed")
	private String isDeployed;
	
	@Column(name = "app_output_template")
	private String outputTemplate;
	
	@Column(name = "port")
	private String port;
	
	@Column(name = "api_url")
	private String apiUrl;
	
	@Column(name = "rec_key")
	private String recKey;
	
	@Column(name = "host_name")
	private String hostName;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rec_id")
	private List<RecProcessData> recProcessDataLst;

	public Long getRecId() {
		return recId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDes() {
		return appDes;
	}

	public void setAppDes(String appDes) {
		this.appDes = appDes;
	}

	public String getInputParamter() {
		return inputParamter;
	}

	public void setInputParamter(String inputParamter) {
		this.inputParamter = inputParamter;
	}

	public String getIsDeployed() {
		return isDeployed;
	}

	public void setIsDeployed(String isDeployed) {
		this.isDeployed = isDeployed;
	}

	public String getOutputTemplate() {
		return outputTemplate;
	}

	public void setOutputTemplate(String outputTemplate) {
		this.outputTemplate = outputTemplate;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getRecKey() {
		return recKey;
	}

	public void setRecKey(String recKey) {
		this.recKey = recKey;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public List<RecProcessData> getRecProcessDataLst() {
		return recProcessDataLst;
	}

	public void setRecProcessDataLst(List<RecProcessData> recProcessDataLst) {
		this.recProcessDataLst = recProcessDataLst;
	} 
	
	
	
}
