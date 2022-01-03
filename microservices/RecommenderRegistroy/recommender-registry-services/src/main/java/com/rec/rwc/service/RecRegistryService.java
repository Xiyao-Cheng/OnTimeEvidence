package com.rec.rwc.service;

import java.util.List;

import com.rec.rwc.model.RecommendApps;

public interface RecRegistryService {

	public List<RecommendApps> getAllRecommenderList();
	public RecommendApps addRecommenderDetails(RecommendApps recommendApps) throws Exception;
	public RecommendApps getRecommenderDetails(Long recId) throws Exception;
	public void updateRecommenderDetails(RecommendApps recommendApps) throws Exception;
	public List<RecommendApps> getPublishedRecommenderList();
	public List<RecommendApps> getRecommenderListClient(Long[] recIds);

}
