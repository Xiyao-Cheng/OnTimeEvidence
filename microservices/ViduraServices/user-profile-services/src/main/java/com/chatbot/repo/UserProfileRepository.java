package com.chatbot.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chatbot.model.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Long>{

	@Query("SELECT u FROM UserProfile u WHERE upper(u.userName)=:userName and upper(u.usecase)=:usecase ") 
	UserProfile findUserName(@Param("userName") String userName, @Param("usecase") String usecase);

	
	
}
