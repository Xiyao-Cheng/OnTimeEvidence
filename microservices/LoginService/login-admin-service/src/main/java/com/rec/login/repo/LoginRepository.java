package com.rec.login.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rec.login.model.User;

@Repository
public interface LoginRepository extends CrudRepository<User, Long>{

	@Query("SELECT u FROM User u WHERE LOWER(u.userName) = :userName and u.githubId = :githubId ")
	public User find(@Param("userName") String userName, @Param("githubId") String githubId);
	
	@Query("SELECT u FROM User u WHERE LOWER(u.userName) = :userName and u.password = :password ")
	public User findByUserIDPwd(@Param("userName") String userName, @Param("password") String githubId);

}
