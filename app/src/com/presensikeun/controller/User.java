package com.presensikeun.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Koneksi {

	public static String username = null;

	public static void setUsername(String text) {
		username = text;
	}

	public static String getSome(String type) {

		String sql = null;
		String value = null;

		try {

			switch (type) {
				case "name":
					sql = "select nama from tb_karyawan where username = '" + username + "'";
					break;
				case "password":
					sql = "select password from tb_karyawan where username = '" + username + "'";
					break;
				case "type":
					sql = "select status from tb_karyawan where username = '" + username + "'";
					break;
				default:
					break;
			}

			Connection con = getKoneksi();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				value = rs.getString(1);
			}

		} catch (SQLException ex) {

		}
		return value;
	}

}
