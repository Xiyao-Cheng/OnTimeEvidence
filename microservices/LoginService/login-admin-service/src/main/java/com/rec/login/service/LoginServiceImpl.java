package com.rec.login.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rec.login.model.User;
import com.rec.login.repo.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginRepository loginRepository;
	
	@Override
	public User getUserDetails(String userName, String githubId) {
		
		User user = loginRepository.find( userName,  githubId);
		return user;
	}

	@Override
	public User insertUserDetails(User user) {
		//user.setPassword(encryptPassword(user.getPassword()));
		User user_ = loginRepository.save( user);
		return user_;
		
	}
	
	@Override
	public User authenticateUser(String userName, String password) {
		User user_ = loginRepository.findByUserIDPwd(userName, encryptPassword(password));
		return user_;
		
	}
	
	private String encryptPassword(String password)
	{
	        String encryptedPassword = null;
	        try {
	            // Create MessageDigest instance for MD5
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            //Add password bytes to digest
	            md.update(password.getBytes());
	            //Get the hash's bytes 
	            byte[] bytes = md.digest();
	            //This bytes[] has bytes in decimal format;
	            //Convert it to hexadecimal format
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            //Get complete hashed password in hex format
	            encryptedPassword = sb.toString();
	        } 
	        catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        }
	        return encryptedPassword;
	}
	

}
