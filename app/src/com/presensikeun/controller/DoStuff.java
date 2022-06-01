package com.presensikeun.controller;

public class DoStuff {

	public static String getDay(int day) {
		String string;

		switch (day) {
			case 0:
				string = "Senin";
				break;
			case 1:
				string = "Selasa";
				break;
			case 2:
				string = "Rabu";
				break;
			case 3:
				string = "Kamis";
				break;
			case 4:
				string = "Jumat";
				break;
			case 5:
				string = "Sabtu";
				break;
			case 6:
				string = "Minggu";
				break;
			default:
				string = "?";
				break;
		}

		return string;
	}
}
