package com.rec.login.service;

import com.rec.login.model.User;

public interface LoginService {

	public User getUserDetails(String userName, String githubId);
	public User insertUserDetails(User user);
	User authenticateUser(String userName, String password);

}
