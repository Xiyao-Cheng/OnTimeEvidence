package com.rec.chain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rec.chain.model.ChainApp;
import com.rec.chain.repo.ChainAppRepo;

@Service
public class ChainAppServiceImpl implements ChainAppService {
	
	@Autowired
	ChainAppRepo chainRepository;

	@Override
	public ChainApp getRecommendationSuggestion(String intentId, String clientId, String recKey, Long userProfile) {
		return chainRepository.getRecommendationSuggestion(intentId, clientId, recKey, userProfile);
	}

	

	

}
