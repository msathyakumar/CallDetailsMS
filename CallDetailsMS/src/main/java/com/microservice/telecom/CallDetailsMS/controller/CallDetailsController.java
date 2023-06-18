package com.microservice.telecom.CallDetailsMS.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.telecom.CallDetailsMS.dto.CallDetailsDTO;
import com.microservice.telecom.CallDetailsMS.service.CallDetailsService;

@RestController
@RequestMapping
public class CallDetailsController {
	@Autowired
	CallDetailsService callDetailsService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Fetches call details of a specific customer
	
	@GetMapping(value = "/customers/{phoneNo}/calldetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {
		logger.info("Calldetails request for customer {}", phoneNo);
		return callDetailsService.getCustomerCallDetails(phoneNo);
	}

	@PostMapping(value = "/customers/{phoneNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void getCustomerCallDetails(@PathVariable long phoneNo,@RequestBody CallDetailsDTO callDetailsDto) {
		logger.info("Calldetails request for customer {}", phoneNo);
		callDetailsService.saveCustomerCallDetails(phoneNo,callDetailsDto);
	}
}
