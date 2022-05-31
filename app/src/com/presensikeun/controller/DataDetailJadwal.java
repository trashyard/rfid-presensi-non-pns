package com.presensikeun.controller;

import com.presensikeun.swing.Combobox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataDetailJadwal extends Koneksi {

	public static Connection con = getKoneksi();
	public static String query = "select dj.id, dj.hari as \"Date\", dj.jam as \"Jam Mulai\", ADDTIME(dj.jam, dj.durasi) as \"Jam Selesai\", m.nama as \"Nama Mapel\", k.nama as \"Nama Karyawan\", j.id from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id join tb_kelas as kls on kls.id = j.id_kelas join tb_ruang as r on r.id = kls.id_ruang ";
	public static JTable table = null;

	public static void setTable(JTable t) {
		table = t;
	}

	public static void getTable() {
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Hari");
		model.addColumn("Jam Mulai");
		model.addColumn("Jam Selesai");
		model.addColumn("Nama Mapel");
		model.addColumn("Nama Karyawan");
		model.addColumn("Jadwal");

		try {

			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), getDay(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
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
		query = "select dj.id, dj.hari as \"Date\", dj.jam as \"Jam Mulai\", ADDTIME(dj.jam, dj.durasi) as \"Jam Selesai\", m.nama as \"Nama Mapel\", k.nama as \"Nama Karyawan\", j.id from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id join tb_kelas as kls on kls.id = j.id_kelas join tb_ruang as r on r.id = kls.id_ruang ";
	}

	private static String getDay(int day) {
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

	public static void getHari(Combobox c) {
		c.setLabeText("Hari");
		c.removeAllItems();
		c.addItem("Senin");
		c.addItem("Selasa");
		c.addItem("Rabu");
		c.addItem("Kamis");
		c.addItem("Jumat");
		c.setSelectedIndex(-1);
	}

	public static void getKelas(Combobox c) {
		c.setLabeText("Kelas");
		c.removeAllItems();
		try {
			String sql = "select id from tb_kelas where nama != 'NONE'";
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
