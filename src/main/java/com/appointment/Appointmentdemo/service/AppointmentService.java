package com.appointment.Appointmentdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.Appointmentdemo.dto.AppointmentDto;
import com.appointment.Appointmentdemo.exception.HandleException;
import com.appointment.Appointmentdemo.model.Appointment;
import com.appointment.Appointmentdemo.model.Staff;
import com.appointment.Appointmentdemo.repository.AppointmentRepository;
import com.appointment.Appointmentdemo.repository.StaffRepository;
import com.appointment.Appointmentdemo.response.AppointmentResponse;

@Service
public class AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	AppointmentService appointmentService;
	@Autowired
	StaffRepository staffRepository;
	public void addAppointment(AppointmentDto appointmentDto, Long sid) {
		Appointment appointment = new Appointment();
		Staff staff = staffRepository.getOne(sid);
		if(staff==null) {
			throw new HandleException("Staff id not found");
		}
		
		appointment.setName(appointmentDto.getName());
		appointment.setTime(appointmentDto.getTime());
		appointment.setDate(appointmentDto.getDate());
		appointment.setDescription(appointmentDto.getDescription());
		appointment.setEmail(appointmentDto.getEmail());
		appointment.setPhoneNumber(appointmentDto.getPhoneNumber());
		appointment.setStaff(staff);
		appointmentRepository.save(appointment);
		
	}
	public AppointmentResponse readAppointment(Long aid) {
		Appointment appointment = appointmentRepository.getOne(aid);
		if(appointment == null) {
			throw new HandleException("Appointment id not found");
		}
		AppointmentResponse appointmentResponse= new AppointmentResponse();
		appointmentResponse.setName(appointment.getName());
		appointmentResponse.setTime(appointment.getTime());
		appointmentResponse.setDate(appointment.getDate());
		appointmentResponse.setEmail(appointment.getEmail());
		appointmentResponse.setPhoneNumber(appointment.getPhoneNumber());
		appointmentResponse.setDescription(appointment.getDescription());
		return appointmentResponse;
	}
	public void updateAppointment(Long aid, AppointmentDto appointmentDto, Long sid) {
		Appointment appointment = appointmentRepository.getOne(aid);
		if(appointment == null) {
			throw new HandleException("Appointment id not found");
		}
		appointment.setName(appointmentDto.getName());
		appointment.setTime(appointmentDto.getTime());
		appointment.setDate(appointmentDto.getDate());
		appointment.setDescription(appointmentDto.getDescription());
		appointment.setEmail(appointmentDto.getEmail());
		appointment.setPhoneNumber(appointmentDto.getPhoneNumber());
		Staff staff = staffRepository.getOne(sid);
		appointment.setStaff(staff);
		appointmentRepository.save(appointment);
		
	}
	public void deleteAppointment(Long aid) {
		Appointment appointment= appointmentRepository.getOne(aid);
		if(appointment == null) {
			throw new HandleException("Appointment id not found");
		}
		appointmentRepository.deleteById(aid);
		
	}
	public List<AppointmentResponse> readAllAppointment() {
		List<Appointment> appointment = appointmentRepository.findAll();
		if(appointment.isEmpty()) {
			throw new HandleException("No data found");
		}
		List<AppointmentResponse> appointmentResponse = new ArrayList<>();
		for(Appointment ap:appointment) {
			AppointmentResponse response = new AppointmentResponse();
			response.setName(ap.getName());
			response.setTime(ap.getTime());
			response.setDate(ap.getDate());
			response.setDescription(ap.getDescription());
			response.setEmail(ap.getEmail());
			response.setPhoneNumber(ap.getPhoneNumber());
			response.setAid(ap.getId());
			appointmentResponse.add(response);
		}
		return appointmentResponse;
	}

}
