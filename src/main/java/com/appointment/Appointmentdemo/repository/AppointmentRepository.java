package com.appointment.Appointmentdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.Appointmentdemo.model.Appointment;
import com.appointment.Appointmentdemo.model.Staff;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	List<Appointment> getByStaff(Staff staff);

	List<Appointment> findByStaff(Staff staff);




}
