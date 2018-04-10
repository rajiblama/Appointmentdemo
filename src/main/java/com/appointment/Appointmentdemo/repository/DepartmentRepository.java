package com.appointment.Appointmentdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.Appointmentdemo.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Department findByName(String name);

}
