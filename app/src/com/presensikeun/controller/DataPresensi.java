package com.presensikeun.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataPresensi extends Koneksi {

	public static Connection con = getKoneksi();
	public static String query = "select k.nik, k.nama, p.id, p.keterangan, mp.nama, p.tanggal from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id join tb_jadwal as j on j.id = dj.id_jadwal join tb_mapel as mp on j.id_mapel = mp.id join tb_karyawan as k on dj.id_karyawan = k.id where date(p.tanggal) = current_date order by p.tanggal desc";
	public static JTable table = null;

	public static void setTable(JTable t) {
		table = t;
	}

	public static void getTable() {
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("NIK");
		model.addColumn("Nama");
		model.addColumn("ID Presensi");
		model.addColumn("Keterangan");
		model.addColumn("Mata Pelajaran");
		model.addColumn("Tanggal");

		try {

			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
			}

			table.setModel(model);

		} catch (SQLException ex) {

			model.addRow(new Object[]{});
			table.setModel(model);

		}
	}

	public static void addTableQuery(String sql) {
		query = query + sql;
	}

	public static void resetQuery() {
		query = "select k.nik, k.nama, p.id, p.keterangan, mp.nama, p.tanggal from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id join tb_jadwal as j on j.id = dj.id_jadwal join tb_mapel as mp on j.id_mapel = mp.id join tb_karyawan as k on dj.id_karyawan = k.id where date(p.tanggal) = current_date order by p.tanggal desc";
	}
}
