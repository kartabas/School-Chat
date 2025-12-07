package com.schoolchat.school.chat.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptHashing {

	public static String hashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	public static boolean verifyPassword(String inputPassword, String storedHash) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(inputPassword, storedHash);
	}

}
