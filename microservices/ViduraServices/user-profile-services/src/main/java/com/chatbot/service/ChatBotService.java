package com.chatbot.service;

import com.chatbot.model.UserProfile;

public interface ChatBotService {

	UserProfile getUserDtls(String userName, String domain);

	UserProfile insertUserDtls(UserProfile userProfile);

	UserProfile getUserProfile(Long parseLong);

}
