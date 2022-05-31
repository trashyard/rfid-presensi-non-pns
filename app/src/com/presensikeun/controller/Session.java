package com.presensikeun.controller;

public class Session {

	public static boolean isLoggedIn = false;
	public static String name = null;
	public static String username = null;

	public static void setSession(String nameuser, String user) {
		name = nameuser;
		username = user;
	}

	public static void logout() {
		isLoggedIn = false;
		name = null;
		username = null;
	}

	public static String getName() {
		return name;
	}

	public static String getUsername() {
		return username;
	}

}
