package com.rec.rwc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rec.rwc.model.RecommendApps;

@Repository
public interface RecommenderRepository extends CrudRepository<RecommendApps, Long>{

	@Query("SELECT u FROM RecommendApps u WHERE u.isDeployed = 'Y' ")
	List<RecommendApps> findAllDeployedRecommend();

	@Query("SELECT u FROM RecommendApps u WHERE u.isDeployed = 'N' ")
	List<RecommendApps> findAllRecommenderToBeDeployed();

	@Query("SELECT u FROM RecommendApps u WHERE u.recId in :recId ")
	List<RecommendApps> getRecommenderListClient(@Param("recId") Long[] recId);


}
