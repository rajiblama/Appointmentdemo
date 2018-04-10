package com.appointment.Appointmentdemo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.Appointmentdemo.dto.LoginDto;
import com.appointment.Appointmentdemo.response.StaffLoginResponse;
import com.appointment.Appointmentdemo.service.StaffLoginService;

@RestController
@RequestMapping("/stafflogin")
public class StaffLoginController {
	@Autowired
	StaffLoginService staffLoginService;
	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<Object> postStaffLogin(@RequestBody LoginDto loginDto){
		StaffLoginResponse staffloginResponse = staffLoginService.checklogin(loginDto);
		Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("Login result", staffloginResponse);
		return new ResponseEntity<Object>(responseMap,HttpStatus.OK);
	}
}
