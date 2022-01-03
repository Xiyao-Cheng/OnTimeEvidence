package com.rec.rwc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rec.rwc.model.RecProcessData;

@Repository
public interface RecProcessRepository extends CrudRepository<RecProcessData, Long>{

	
	@Query("SELECT u FROM RecProcessData u WHERE u.recommendApps.recId=:recId ") //and (isDefault  is null or isDefault <> 'Y')")
	List<RecProcessData> findRecId(@Param("recId") Long recId);

	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("update RecProcessData u set u.status= :status WHERE u.recProcessId= :recProcessId"
	 * ) int updateProcessStatus(@Param("recProcessId") Long
	 * recProcessId,@Param("status") String status);
	 */
	@Modifying
	@Transactional
	@Query("update RecProcessData u set u.inputParamater=:inputParamater,u.apiUrl=:apiUrl,u.processDetails=:processDetails,u.processName=:processName WHERE u.recProcessId= :recProcessId")
	void editProcess(@Param("inputParamater") String inputParamater, @Param("apiUrl") String apiUrl, @Param("processDetails") String processDetails,
			@Param("processName") String processName,	@Param("recProcessId") Long recProcessId);

	
}
