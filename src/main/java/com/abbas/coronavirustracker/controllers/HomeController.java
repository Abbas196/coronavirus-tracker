package com.abbas.coronavirustracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abbas.coronavirustracker.services.CoronaVirusDataService;

// we are using controller not spring controller because we are not returning rest responses.

@Controller
public class HomeController {
	
      @Autowired
	  CoronaVirusDataService coronaData;
	  @GetMapping("/")
      public String home(Model model) {
		  model.addAttribute("locationsStat", coronaData.getStatistics());
    	  return "home";
      }
}
