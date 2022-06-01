package com.presensikeun.controller;

import com.presensikeun.swing.Combobox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataReport extends Koneksi {

	public static Connection con = getKoneksi();
	public static String query = "select k.nik, weekday(p.tanggal), dj.jam as \"jam mulai\", addtime(dj.jam, dj.durasi) as \"jam selesai\", k.nama, m.nama as \"mapel\", r.nama as \"ruangan\", p.tanggal as \"masuk\" from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id join tb_karyawan as k on k.id = dj.id_karyawan join tb_jadwal as j on j.id = dj.id_jadwal join tb_mapel as m on j.id_mapel = m.id join tb_kelas as kls on kls.id = j.id_kelas join tb_ruang as r on kls.id_ruang = r.id";

	public static JTable table = null;

	public static void setTable(JTable t) {
		table = t;
	}

	public static void getTable(String dari, String sampai, String nik) {

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("NIK");
		model.addColumn("Hari");
		model.addColumn("Jam Mulai");
		model.addColumn("Jam Selesai");
		model.addColumn("Nama");
		model.addColumn("Mapel");
		model.addColumn("Ruangan");
		model.addColumn("Masuk");

		try {

			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), DoStuff.getDay(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
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
		query = "select k.nik, weekday(p.tanggal), dj.jam as \"jam mulai\", addtime(dj.jam, dj.durasi) as \"jam selesai\", k.nama, m.nama as \"mapel\", r.nama as \"ruangan\", p.tanggal as \"masuk\" from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id join tb_karyawan as k on k.id = dj.id_karyawan join tb_jadwal as j on j.id = dj.id_jadwal join tb_mapel as m on j.id_mapel = m.id join tb_kelas as kls on kls.id = j.id_kelas join tb_ruang as r on kls.id_ruang = r.id";
	}

	public static void getNIK(Combobox c) {
		c.setLabeText("NIK");
		c.removeAllItems();
		c.addItem("Semua");
		try {
			String sql = "select nik from tb_karyawan order by nik desc";
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
