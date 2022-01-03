package com.rec.chain.service;

import com.rec.chain.model.ChainApp;

public interface ChainAppService {

	ChainApp getRecommendationSuggestion(String intentId, String clientId, String recKey, Long userProfile);

	

}
