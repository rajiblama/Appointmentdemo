package com.appointment.Appointmentdemo.response;

import java.io.Serializable;

import com.appointment.Appointmentdemo.utility.LoginStatus;

public class StaffLoginResponse implements Serializable {

	private LoginStatus status;
	private String message;
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LoginStatus getStatus() {
		return status;
	}
	public void setStatus(LoginStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
