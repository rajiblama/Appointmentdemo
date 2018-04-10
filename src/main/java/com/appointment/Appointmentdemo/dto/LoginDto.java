package com.appointment.Appointmentdemo.dto;

import java.io.Serializable;

public class LoginDto implements Serializable{
	
	private String email;
	private String Password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
