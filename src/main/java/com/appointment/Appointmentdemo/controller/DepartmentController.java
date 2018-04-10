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
import org.springframework.web.bind.annotation.RestController;

import com.appointment.Appointmentdemo.dto.DepartmentDto;
import com.appointment.Appointmentdemo.response.DepartmentResponse;
import com.appointment.Appointmentdemo.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> postDepartment(@RequestBody DepartmentDto departmentDto){
		departmentService.addDepartment(departmentDto);
		return new ResponseEntity<Object>("Department Added", HttpStatus.OK);
	}
	
	@RequestMapping(value ="/{did}", method = RequestMethod.GET)
	public ResponseEntity<Object> getDepartment(@PathVariable Long did){
		List<DepartmentResponse> departmentResponse = departmentService.readDepartment(did);
		Map<Object,Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("Department Detail", departmentResponse);
		return new ResponseEntity(responseMap, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{did}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteDepartment(@PathVariable Long did){
		departmentService.deleteDepartment(did);
		return new ResponseEntity<Object>("Department Deleted",HttpStatus.OK);
	}
	
	@RequestMapping(value="/{did}", method = RequestMethod.PUT)
	public ResponseEntity<Object> putMethod(@RequestBody DepartmentDto departmentDto, @PathVariable Long did){
		departmentService.updateDepartment(departmentDto, did);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllDepartment(){
		List<DepartmentResponse> departmentResponse = departmentService.readAllDepartment();
		Map<Object, Object> responseMap = new HashMap<Object, Object>();
		responseMap.put("All Department data", departmentResponse);
		return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
	}
	
}
