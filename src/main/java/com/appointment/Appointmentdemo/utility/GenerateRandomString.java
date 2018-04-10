package com.appointment.Appointmentdemo.utility;

import java.util.Random;

public class GenerateRandomString {
	
	public static String generateString() {
		char[] characters = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
		        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
		        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		        'w', 'x', 'y', 'z' };
		Random random = new Random();
		char[] stringArray = new char[20];
		for(int i = 0; i<20; i++) {
			stringArray[i] = characters[random.nextInt(characters.length)];
		}
		String randomString = new String(stringArray);
		return randomString;
	}
}
