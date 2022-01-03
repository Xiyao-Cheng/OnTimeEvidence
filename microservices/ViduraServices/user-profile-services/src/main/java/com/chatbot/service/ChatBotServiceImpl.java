package com.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatbot.model.UserProfile;
import com.chatbot.repo.UserProfileRepository;

@Service
public class ChatBotServiceImpl implements ChatBotService{

	@Autowired
	UserProfileRepository userProfileRepository;
	
	
	@Override
	public UserProfile getUserDtls(String userName, String domain) {

		return userProfileRepository.findUserName(userName, domain);
	}


	@Override
	public UserProfile insertUserDtls(UserProfile userProfile) {
		
		userProfile = userProfileRepository.save(userProfile);
		return userProfile;
	}


	@Override
	public UserProfile getUserProfile(Long parseLong) {
		return 	userProfileRepository.findById(parseLong).get();
	}


}
