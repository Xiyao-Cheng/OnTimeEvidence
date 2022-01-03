package com.web.cyneuro;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SpringBootApplication
public class Application {
	
	
	public static void main(String[] args) {
		System.setProperty("server.contextPath", "/CyNeuro");

		SpringApplication.run(Application.class, args);
		
	
	}
	
	@RequestMapping("/")
	public ModelAndView welcomePage(HttpServletRequest request) {
		
		
    	ModelAndView mav =new ModelAndView("index");

    
    	
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
