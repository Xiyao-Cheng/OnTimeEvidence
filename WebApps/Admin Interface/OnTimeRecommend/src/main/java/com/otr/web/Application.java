package com.otr.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.otr.web.bean.UserSessionVO;
import com.otr.web.utility.JSONtoObject;

@Controller
@SpringBootApplication
public class Application {
	
	
	public static void main(String[] args) {
		System.setProperty("server.contextPath", "/OnTimeRecommend");

		SpringApplication.run(Application.class, args);
		
	
	}

	@RequestMapping("/")
	public ModelAndView welcomePage(HttpServletRequest request) {
		UserSessionVO user = (UserSessionVO) request.getSession().getAttribute("UserSessionVO");

		/*
		 * if(null == ApplicationSetup.appHashMap) { String props =
		 * RestClient.callRestAPI(null, "/api/otr/setApp/getOTRProperties",
		 * HttpMethod.GET); ApplicationSetup.appHashMap =
		 * JSONtoObject.jsonToObject(props, HashMap.class);
		 * 
		 * }
		 */
		
    	ModelAndView mav = null; 
    	if(null != user) {
    		
        	mav = new ModelAndView("indexAdmin");

    		String userSessionVO = JSONtoObject.ObjecttoJson(user);
            mav.addObject("userSessionVO", userSessionVO);
    	}else {
        	mav = new ModelAndView("index");

    	}
    	
    	return mav;
		
		
	}
	
	@RequestMapping("/getSurvay")
	public ModelAndView getSurvay(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("survay");; 
		mav.addObject("id", request.getParameter("id"));
		return mav;
		
		
	}
	
	@RequestMapping("/getSurvayBio")
	public ModelAndView getSurvayBio(HttpServletRequest request) {

		
    	
		ModelAndView mav = new ModelAndView("survayBio");; 
		mav.addObject("id", request.getParameter("id"));
		return mav;
		
		
	}

}
