package com.appointment.Appointmentdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getHello")
public class Test {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object>getUsers(){
		//LOG.info("Test Again");
		return new ResponseEntity<Object>("Hello nanayo",HttpStatus.OK);
		
	}

}
