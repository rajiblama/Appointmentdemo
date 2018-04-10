package com.appointment.Appointmentdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.Appointmentdemo.dto.DepartmentDto;
import com.appointment.Appointmentdemo.exception.HandleException;
import com.appointment.Appointmentdemo.model.Department;
import com.appointment.Appointmentdemo.model.Staff;
import com.appointment.Appointmentdemo.repository.DepartmentRepository;
import com.appointment.Appointmentdemo.repository.StaffRepository;
import com.appointment.Appointmentdemo.response.DepartmentResponse;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	StaffRepository staffRepository;
	
	public void addDepartment(DepartmentDto departmentDto) {
		
		Department department = departmentRepository.findByName(departmentDto.getName());
		if(department != null) {
			System.out.println("Error");
			throw new HandleException("Department name already exist");
		}
		department= new Department();
		department.setName(departmentDto.getName());
		department.setWork(departmentDto.getWork());
		department.setDescription(departmentDto.getDescription());
		System.out.println("ending...");
		departmentRepository.save(department);
	}
	public List<DepartmentResponse> readDepartment(Long did) {
		Department department = departmentRepository.getOne(did);
		if(department== null) {
			throw new HandleException("Department id not found.");
		}
		List<Staff> staff = staffRepository.findByDepartment(department);
		if(staff.isEmpty()) {
			throw new HandleException("No data found.");
		}
		List<DepartmentResponse> departmentResponse = new ArrayList<>();
		for(Staff sf:staff) {
			DepartmentResponse response = new DepartmentResponse();
			response.setdName(department.getName());
			response.setDid(department.getId());
			response.setName(sf.getName());
			response.setEmail(sf.getEmail());
			response.setPhoneNumber(sf.getPhoneNumber());
			response.setSid(sf.getId());
			departmentResponse.add(response);
		}
		return departmentResponse;
	}
	public void deleteDepartment(Long did) {
		Department department = departmentRepository.getOne(did);
		if(department== null) {
			throw new HandleException("Department id not found.");
		}
		departmentRepository.deleteById(did);
		
	}
	public void updateDepartment(DepartmentDto departmentDto, Long did) {
		Department department = departmentRepository.getOne(did);
		if(department== null) {
			throw new HandleException("Department id not found.");
		}
		department.setName(departmentDto.getName());
		department.setWork(departmentDto.getWork());
		department.setDescription(departmentDto.getDescription());
		departmentRepository.save(department);
	}
	public List<DepartmentResponse> readAllDepartment() {
		List<Department> department = departmentRepository.findAll();
		if(department.isEmpty()) {
			throw new HandleException("No data found");
		}
		List<DepartmentResponse> departmentResponse = new ArrayList<>();
		for(Department dp:department) {
			List<Staff> staff =staffRepository.findByDepartment(dp);
			if(staff.isEmpty()) {
				throw new HandleException("No data found");
			}
			List<DepartmentResponse> tempdepartmentResponse =  new ArrayList<>();
			for(Staff sf:staff) {
				DepartmentResponse response = new DepartmentResponse();
				response.setDid(dp.getId());
				response.setdName(dp.getName());
				response.setSid(sf.getId());
				response.setName(sf.getName());
				response.setEmail(sf.getEmail());
				response.setPhoneNumber(sf.getPhoneNumber());
				
				tempdepartmentResponse.add(response);
			}
			departmentResponse.addAll(tempdepartmentResponse);
		}
		return departmentResponse;
	}

}
