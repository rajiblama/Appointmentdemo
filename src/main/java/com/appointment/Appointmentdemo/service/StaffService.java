package com.appointment.Appointmentdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.Appointmentdemo.dto.StaffDto;
import com.appointment.Appointmentdemo.exception.EncodeDecode;
import com.appointment.Appointmentdemo.exception.HandleException;
import com.appointment.Appointmentdemo.model.Appointment;
import com.appointment.Appointmentdemo.model.Department;
import com.appointment.Appointmentdemo.model.Staff;
import com.appointment.Appointmentdemo.repository.AppointmentRepository;
import com.appointment.Appointmentdemo.repository.DepartmentRepository;
import com.appointment.Appointmentdemo.repository.StaffRepository;
import com.appointment.Appointmentdemo.response.AppointmentResponse;
import com.appointment.Appointmentdemo.response.StaffResponse;
import com.appointment.Appointmentdemo.utility.GenerateRandomString;
import com.appointment.Appointmentdemo.utility.LoginStatus;

@Service
public class StaffService {
	@Autowired
	StaffRepository staffRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	AppointmentRepository appointmentRepository;
	
	public Staff addStaff(StaffDto staffDto, Long did) {
		/*Staff staff = new Staff();*/
		EncodeDecode ed = new EncodeDecode();
		Department department = departmentRepository.getOne(did);
		if(department == null) {
			System.out.println("error");
			throw new HandleException("Department not found.");
		}
		Staff staff = staffRepository.findByEmail(staffDto.getEmail());
		if(staff != null) {
			throw new HandleException("Email already registered");
		}
		staff = new Staff();
		staff.setEmail(staffDto.getEmail());
		staff.setName(staffDto.getName());
		staff.setPhoneNumber(staffDto.getPhoneNumber());
		staff.setPassword(ed.doEncode(staffDto.getPassword()));
		staff.setStatus(LoginStatus.LOGGED_OUT);
		staff.setToken(GenerateRandomString.generateString());
		staff.setDepartment(department);
		return staffRepository.save(staff);
	}
	public List<StaffResponse> readStaff(Long sid) {
		Staff staff = staffRepository.getOne(sid);
		/*StaffResponse staffResponse = new StaffResponse();
		staffResponse.setName(staff.getName());
		staffResponse.setEmail(staff.getEmail());
		staffResponse.setPhoneNumber(staff.getPhoneNumber());*/
		List<Appointment> appointment = appointmentRepository.getByStaff(staff);
		if(appointment.isEmpty()) {
			throw new HandleException("No data found");
		}
		List<StaffResponse> staffResponse = new ArrayList<>();
		for(Appointment ap:appointment) {
			StaffResponse tempStaffResponse = new StaffResponse();
			tempStaffResponse.setSid(staff.getId());
			tempStaffResponse.setAid(ap.getId());
			tempStaffResponse.setName(ap.getName());
			tempStaffResponse.setTime(ap.getTime());
			tempStaffResponse.setDate(ap.getDate());
			tempStaffResponse.setDescription(ap.getDescription());
			tempStaffResponse.setEmail(ap.getEmail());
			tempStaffResponse.setPhoneNumber(ap.getPhoneNumber());
			staffResponse.add(tempStaffResponse);
		}
		
		return staffResponse;
		
	}
	public List<StaffResponse> updateStaff(Long sid, StaffDto staffDto, Long did) {
		Staff staff = staffRepository.getOne(sid);
		if(staff == null) {
			throw new HandleException("Staff id not found");
		}
		List<Appointment> appointment =appointmentRepository.findByStaff(staff);
		List<StaffResponse> staffResponse = new ArrayList<>();
		for(Appointment ap:appointment) {
			StaffResponse tempStaffResponse = new StaffResponse();
			tempStaffResponse.setAid(ap.getId());
			tempStaffResponse.setName(ap.getName());
			tempStaffResponse.setTime(ap.getTime());
			tempStaffResponse.setDate(ap.getDate());
			tempStaffResponse.setDescription(ap.getDescription());
			tempStaffResponse.setEmail(ap.getEmail());
			tempStaffResponse.setPhoneNumber(ap.getPhoneNumber());
			staffResponse.add(tempStaffResponse);
		}
		return staffResponse;
		
	}
	public void deleteStaff(Long sid) {
		Staff staff = staffRepository.getOne(sid);
		if(staff == null) {
			throw new HandleException("Staff id not found");
		}
		staffRepository.deleteById(sid);
		
		
	}
	public List<StaffResponse> readAllStaff() {
		List<Staff> staff = staffRepository.findAll();
		if(staff.isEmpty()) {
			throw new HandleException("No data found.");
		}
		List<StaffResponse> staffResponse = new ArrayList<>();
		for(Staff sf:staff) {
			List<Appointment> appointment = appointmentRepository.findByStaff(sf);
			if(appointment.isEmpty()) {
				throw new HandleException("No data found");
			}
			List<StaffResponse> tempStaffResponse = new ArrayList<>();
			for(Appointment ap:appointment) {
		    StaffResponse response = new StaffResponse();
			response.setEmail(ap.getEmail());
			response.setName(ap.getEmail());
			response.setPhoneNumber(ap.getPhoneNumber());
			response.setAid(ap.getId());
			response.setDate(ap.getDate());
			response.setTime(ap.getTime());
			response.setDescription(ap.getDescription());
			response.setSid(sf.getId());
			tempStaffResponse.add(response);
			}
			staffResponse.addAll(tempStaffResponse);
			}
		return staffResponse;
	}
}
