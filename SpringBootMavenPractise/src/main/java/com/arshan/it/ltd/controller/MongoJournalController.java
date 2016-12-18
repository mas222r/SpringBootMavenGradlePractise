package com.arshan.it.ltd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.arshan.it.ltd.service.MongoJournalRepository;

@RestController
public class MongoJournalController {
	
	private static final String VIEW_INDEX = "index";
	
	@Autowired
	private MongoJournalRepository mongoJournalRepository;
	
	@RequestMapping(value="/retrieve/journal/all",method=RequestMethod.GET)
	public ModelAndView retrieveData(ModelAndView modelAndView)
	{
		modelAndView.setViewName(VIEW_INDEX);
		modelAndView.addObject("mongoJournalData", mongoJournalRepository.findAll());
		return modelAndView;
	}

}
