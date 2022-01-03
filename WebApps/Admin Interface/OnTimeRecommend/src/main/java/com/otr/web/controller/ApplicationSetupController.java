package com.otr.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ApplicationSetupController {

	@RequestMapping(value = "firstTimeLogin",method = RequestMethod.GET)
	public String logoutUser( HttpServletRequest request){
		
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("UserSessionVO");
		
		return "redirect:/";
		
	}
	
}
