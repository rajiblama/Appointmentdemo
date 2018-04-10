package com.appointment.Appointmentdemo.exception;

import java.util.Base64;

public class EncodeDecode {
	
	public String doEncode(String toEncode) {
		byte [] encodedCode = Base64.getEncoder().encode(toEncode.getBytes());
		return new String(encodedCode);
	}
	public String doDecode(String toDecode) {
		byte [] decodedCode = Base64.getDecoder().decode(toDecode.getBytes());
		return new String(decodedCode);
	}
}
