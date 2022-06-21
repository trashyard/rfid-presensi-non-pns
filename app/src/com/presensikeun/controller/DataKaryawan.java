package com.presensikeun.controller;

import com.presensikeun.swing.Combobox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataKaryawan extends Koneksi {

	public static Connection con = getKoneksi();
	public static String query = "select k.nik, k.nama, k.jenis_kelamin, j.nama as jabatan from tb_karyawan as k join tb_jabatan as j on k.id_jabatan = j.id ";
	public static JTable table = null;

	public static void setTable(JTable t) {
		table = t;
	}

	public static void getTable() {

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("NIK");
		model.addColumn("Nama");
		model.addColumn("Jenis Kelamin");
		model.addColumn("Status Kepegawaian");

		try {

			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
			}
table.setModel(model);

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
			model.addRow(new Object[]{});
			table.setModel(model);

		}
	}

	public static void addTableQuery(String sql) {
		query = query + sql;
	}

	public static void resetQuery() {
		query = "select k.nik, k.nama, k.jenis_kelamin, j.nama as jabatan from tb_karyawan as k join tb_jabatan as j on k.id_jabatan = j.id ";
	}

	public static void getGender(Combobox c) {
		c.removeAllItems();
		c.addItem("Laki-Laki");
		c.addItem("Perempuan");
		c.setSelectedIndex(-1);
	}

	public static void getJabatan(Combobox c) {
		c.removeAllItems();
		try {
			String sql = "select nama from tb_jabatan";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				c.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		c.setSelectedIndex(-1);
	}
}
