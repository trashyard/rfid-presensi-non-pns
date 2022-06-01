package com.presensikeun.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataDashboard extends Koneksi {

	public static Connection con = getKoneksi();

	public static double getWeekly(int month, int week) {
		double tot = 0;
		try {

			String sql = "SELECT count(*) from tb_presensi where month(tanggal) = " + month + " && FLOOR((DayOfMonth(tanggal)-1)/7)+1 = " + week + " && year(tanggal) = year(current_date)";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tot = rs.getDouble(1);
			}
		} catch (SQLException ex) {
			tot = 0;
		}
		return tot;
	}

	public static int getMonth(String first, String second) {
		int tot = 0;
		try {

			String sql = "select count(*) from tb_presensi where month(tanggal) between " + first + " and " + second + " && year(tanggal) = year(current_date)";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tot = rs.getInt(1);
			}
		} catch (SQLException ex) {
			tot = 0;
		}
		return tot;
	}

	public static String getTotalPresensi() {
		String tot = "";
		try {
			String sql = "select count(*) from tb_presensi where year(tanggal) = year(current_date)";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tot = rs.getString(1);
			}
		} catch (SQLException ex) {
			tot = "0";
		}
		return tot;
	}

	public static String getUser(String type) {
		String tot = "";
		try {
			String sql = "select count(*) from tb_karyawan where status = '" + type + "'";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tot = rs.getString(1);
			}
		} catch (SQLException ex) {
			tot = "0";
		}
		return tot;
	}

}
