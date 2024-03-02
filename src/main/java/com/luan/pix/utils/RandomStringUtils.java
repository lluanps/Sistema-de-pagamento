package com.luan.pix.utils;

import java.security.SecureRandom;

public class RandomStringUtils {
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String generateString(int lenght) {
		SecureRandom secureRandom = new SecureRandom();
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i< lenght; i++) {
			int index = secureRandom.nextInt(CHARACTERS.length());
			stringBuilder.append(CHARACTERS.charAt(index));
		}
		
		return stringBuilder.toString();
	}
	
}
