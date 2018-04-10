package com.appointment.Appointmentdemo.dto;

import java.io.Serializable;

public class DepartmentDto implements Serializable{
	private String name;
	private String work;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
