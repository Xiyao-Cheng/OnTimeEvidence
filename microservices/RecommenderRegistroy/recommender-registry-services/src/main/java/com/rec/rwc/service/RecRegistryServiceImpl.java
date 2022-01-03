package com.rec.rwc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rec.rwc.model.RecommendApps;
import com.rec.rwc.repo.RecommenderRepository;

@Service
public class RecRegistryServiceImpl implements RecRegistryService {
	
	@Autowired
	RecommenderRepository recRepository;

	@Override
	public List<RecommendApps> getAllRecommenderList() {
	
		List<RecommendApps> recommendApps = (List<RecommendApps>) recRepository.findAll();
		return recommendApps;
	
	}

	@Override
	public RecommendApps addRecommenderDetails(RecommendApps recommendApps) throws Exception {

		try {
			String baseDirectoryName = recommendApps.getAppName().replace(" ", "-").toLowerCase(); 
			recommendApps.setRecKey(baseDirectoryName);
			recommendApps.setOutputTemplate(baseDirectoryName+"_output.html");
			recommendApps= recRepository.save(recommendApps);
		
		} catch (Exception e) {
			throw e;
		}
		return recommendApps;
	}
	
	@Override
	public RecommendApps getRecommenderDetails(Long recId) throws Exception{
		RecommendApps recommendApps = recRepository.findById(recId).get();
		return recommendApps;
	}
	
	@Override
	public void updateRecommenderDetails(RecommendApps recommendApps) throws Exception{
		 recRepository.save(recommendApps);
				
	}

	@Override
	public List<RecommendApps> getPublishedRecommenderList() {
		
		return recRepository.findAllDeployedRecommend();
	}
	
	@Override
	public List<RecommendApps> getRecommenderListClient(Long[] recIds){
		return recRepository.getRecommenderListClient(recIds);

	}

}
