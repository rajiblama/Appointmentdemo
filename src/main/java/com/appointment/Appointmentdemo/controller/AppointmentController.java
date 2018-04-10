package com.appointment.Appointmentdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.Appointmentdemo.dto.AppointmentDto;
import com.appointment.Appointmentdemo.response.AppointmentResponse;
import com.appointment.Appointmentdemo.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postAppointment(@RequestBody AppointmentDto appointmentDto, @RequestParam Long sid){
		appointmentService.addAppointment(appointmentDto, sid);
		return new ResponseEntity<Object>("Appointment booked", HttpStatus.OK);
	}
	@RequestMapping(value="/{aid}", method= RequestMethod.GET)
	public ResponseEntity<Object> getAppointment(@PathVariable Long aid){
		AppointmentResponse appointmentResponse = appointmentService.readAppointment(aid);
		Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put(appointmentResponse.getName()+"'s appointment Detail", appointmentResponse);
		return new ResponseEntity<Object>(responseMap,HttpStatus.OK);
	}
	@RequestMapping(value="/{aid}", method=RequestMethod.PUT)
	public ResponseEntity<Object> putAppointment(@PathVariable Long aid, @RequestBody AppointmentDto appointmentDto, @RequestParam Long sid){
		appointmentService.updateAppointment(aid, appointmentDto, sid);
		return new ResponseEntity<Object>("Appointment updated",HttpStatus.OK);
	}
	@RequestMapping(value="/{aid}",method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAppointment(@PathVariable Long aid){
		appointmentService.deleteAppointment(aid);
		return new ResponseEntity<Object>("Appointment deleted",HttpStatus.OK);
	}
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<Object> getAllAppointment(){
		List<AppointmentResponse> appointmentResponse = appointmentService.readAllAppointment();
		Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("Appointment List", appointmentResponse);
		return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
	}
}
