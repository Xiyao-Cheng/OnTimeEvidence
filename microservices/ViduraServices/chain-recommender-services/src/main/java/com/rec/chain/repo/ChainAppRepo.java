package com.rec.chain.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rec.chain.model.ChainApp;

@Repository
public interface ChainAppRepo extends CrudRepository<ChainApp, Long>{


	@Query("SELECT u FROM ChainApp u WHERE upper(u.intentId)=:intentId and upper(u.clientId)=:clientId and upper(u.recKey)=:recKey and u.userProfile=:userProfile ")
	ChainApp getRecommendationSuggestion(@Param("intentId") String intentId, @Param("clientId") String clientId, 
			@Param("recKey") String recKey, @Param("userProfile") Long userProfile);


}
