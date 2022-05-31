package com.presensikeun.controller;

import com.presensikeun.swing.Combobox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataJadwal extends Koneksi {

	public static Connection con = getKoneksi();
	public static String query = "select j.id, m.id, m.nama, r.nama, concat(k.id, \": Kelas \", k.angkatan, \" - \", k.nama)  from tb_jadwal as j join tb_mapel as m on j.id_mapel = m.id join tb_kelas as k on j.id_kelas = k.id join tb_ruang as r on k.id_ruang = r.id";
	public static JTable table = null;

	public static void setTable(JTable t) {
		table = t;
	}

	public static void getTable() {
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Kode");
		model.addColumn("Nama Mapel");
		model.addColumn("Ruangan");
		model.addColumn("Kelas");

		try {

			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
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
		query = "select j.id, m.id, m.nama, r.nama, concat(k.id, \": Kelas \", k.angkatan, \" - \", k.nama)  from tb_jadwal as j join tb_mapel as m on j.id_mapel = m.id join tb_kelas as k on j.id_kelas = k.id join tb_ruang as r on k.id_ruang = r.id";
	}

	public static void getMapel(Combobox c) {
		c.setLabeText("Mapel");
		c.removeAllItems();
		try {
			String sql = "select id from tb_mapel where nama != 'Datang' and nama != 'Pulang'";
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

	public static void getAngkatan(Combobox c) {
		c.setLabeText("Angkatan");
		c.removeAllItems();
		c.addItem("1");
		c.addItem("2");
		c.addItem("3");
		c.setSelectedIndex(-1);
	}

}
