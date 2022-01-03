package com.rec.orch.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rec.orch.model.RecProcessData;

@Repository
public interface RecProcessRepository extends CrudRepository<RecProcessData, Long>{

	@Query("SELECT u FROM RecProcessData u WHERE u.recommendApps.recId=:recId ") 
	List<RecProcessData> findByRecId(@Param("recId") Long recId);

}
