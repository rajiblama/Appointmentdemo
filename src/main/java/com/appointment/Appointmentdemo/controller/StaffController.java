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

import com.appointment.Appointmentdemo.dto.StaffDto;
import com.appointment.Appointmentdemo.model.Staff;
import com.appointment.Appointmentdemo.response.StaffResponse;
import com.appointment.Appointmentdemo.service.StaffService;

@RestController
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	StaffService staffService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> putStaff(@RequestBody StaffDto staffDto, @RequestParam Long did){
		Staff staff =staffService.addStaff(staffDto, did);
		/*Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("Staff Added", staff);*/
		return new ResponseEntity<Object>("Staff data added", HttpStatus.OK);
	}
	@RequestMapping(value="/{sid}", method = RequestMethod.GET)
	public ResponseEntity<Object> getStaff(@PathVariable Long sid){
		List<StaffResponse> staffResponse = staffService.readStaff(sid);
		Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("Staff detail", staffResponse);
		return new ResponseEntity<Object>(responseMap,HttpStatus.OK);
	}
	@RequestMapping(value= "/{sid}", method = RequestMethod.PUT)
	public ResponseEntity<Object> putStaff(@PathVariable Long sid, @RequestBody StaffDto staffDto,@RequestParam Long did){
		staffService.updateStaff(sid, staffDto, did);
		return new ResponseEntity<Object>("Staff's data updated",HttpStatus.OK);
	}
	@RequestMapping(value="/{sid}",method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteStaff(@PathVariable Long sid){
		staffService.deleteStaff(sid);
		return new ResponseEntity<Object>("Staff's data deleted",HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAllStaff(){
		List<StaffResponse> staffResponse = staffService.readAllStaff();
		Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("Staff's Appointments", staffResponse);
		
		return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
		
	}
}
