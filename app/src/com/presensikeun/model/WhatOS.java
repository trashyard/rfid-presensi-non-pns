package com.presensikeun.model;

public class WhatOS {

	private static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean isUnix() {
		return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
	}

	public static boolean isWindows() {
		return OS.contains("win");
	}

}
