package com.appointment.Appointmentdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.Appointmentdemo.dto.LoginDto;
import com.appointment.Appointmentdemo.exception.EncodeDecode;
import com.appointment.Appointmentdemo.exception.HandleException;
import com.appointment.Appointmentdemo.model.Staff;
import com.appointment.Appointmentdemo.repository.StaffRepository;
import com.appointment.Appointmentdemo.response.StaffLoginResponse;
import com.appointment.Appointmentdemo.utility.GenerateRandomString;
import com.appointment.Appointmentdemo.utility.LoginStatus;

@Service
public class StaffLoginService {
	@Autowired
	StaffRepository staffRepository;
	public StaffLoginResponse checklogin(LoginDto loginDto) {
	StaffLoginResponse staffLoginResponse = new StaffLoginResponse();
	EncodeDecode ed = new EncodeDecode();
	Staff checkStaff= staffRepository.findByEmailAndPassword(loginDto.getEmail(),ed.doEncode(loginDto.getPassword()));
	if(checkStaff==null) {
		throw new HandleException("Login Unsuccessfull");
	}
	else {
		checkStaff.setStatus(LoginStatus.LOGGED_IN);
		checkStaff.setToken(GenerateRandomString.generateString());
		staffRepository.save(checkStaff);
		staffLoginResponse.setMessage("Login Successfull");
		staffLoginResponse.setStatus(checkStaff.getStatus());
		staffLoginResponse.setToken(checkStaff.getToken());
		return staffLoginResponse;
		}
	}
}
