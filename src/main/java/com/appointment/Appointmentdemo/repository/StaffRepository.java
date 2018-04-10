package com.appointment.Appointmentdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.Appointmentdemo.model.Department;
import com.appointment.Appointmentdemo.model.Staff;
import com.appointment.Appointmentdemo.response.DepartmentResponse;

public interface StaffRepository extends JpaRepository<Staff, Long>{

	Staff findByEmailAndPassword(String email, String doEncode);

	List<Staff> findByDepartment(Department department);

	Staff findByEmail(String email);

	

	




}
