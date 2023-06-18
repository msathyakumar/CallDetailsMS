package com.microservice.telecom.CallDetailsMS.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.telecom.CallDetailsMS.dto.CallDetailsDTO;
import com.microservice.telecom.CallDetailsMS.entity.CallDetails;
import com.microservice.telecom.CallDetailsMS.repository.CallDetailsRepository;

@Service
public class CallDetailsService {
	
	@Autowired
	CallDetailsRepository callDetailsRepo;
	
	Logger logger =LoggerFactory.getLogger(this.getClass());
	// Fetches call details of a specific customer
		public List<CallDetailsDTO> getCustomerCallDetails(long phoneNo) {

			logger.info("Calldetails request for customer {}", phoneNo);

			List<CallDetails> callDetails = callDetailsRepo.findByCalledBy(phoneNo);
			List<CallDetailsDTO> callsDTO = new ArrayList<>();

			for (CallDetails call : callDetails) {
				callsDTO.add(CallDetailsDTO.valueOf(call));
			}
			logger.info("Calldetails for customer : {}", callDetails);

			return callsDTO;
		}
		
		public void saveCustomerCallDetails(long phoneNo,CallDetailsDTO callDetailsDTO) {

			callDetailsDTO.setCalledBy(phoneNo);
				
				CallDetails callDetailsEntity = new CallDetails();
				callDetailsEntity.setCalledBy(callDetailsDTO.getCalledBy());
				callDetailsEntity.setCalledOn(callDetailsDTO.getCalledOn());
				callDetailsEntity.setCalledTo(callDetailsDTO.getCalledTo());
				callDetailsEntity.setDuration(callDetailsDTO.getDuration());
			logger.info("Calldetails request for customer {}", phoneNo);

			callDetailsRepo.save(callDetailsEntity);
		}

}
