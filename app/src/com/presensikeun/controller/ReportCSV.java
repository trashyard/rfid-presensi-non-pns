package com.presensikeun.controller;

import com.presensikeun.model.WhatOS;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportCSV extends Koneksi {

	public static Connection con = getKoneksi();

	public static void getPDF(String type, String dari, String sampai, String nik) {

		String filename = null;
		String sql = "select k.nik, weekday(p.tanggal), dj.jam as \"jam mulai\", addtime(dj.jam, dj.durasi) as \"jam selesai\", k.nama, m.nama as \"mapel\", r.nama as \"ruangan\", p.tanggal as \"masuk\" from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id join tb_karyawan as k on k.id = dj.id_karyawan join tb_jadwal as j on j.id = dj.id_jadwal join tb_mapel as m on j.id_mapel = m.id join tb_kelas as kls on kls.id = j.id_kelas join tb_ruang as r on kls.id_ruang = r.id";

		if (WhatOS.isWindows()) {
			filename = "C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\Presensi.csv";
		} else if (WhatOS.isUnix()) {
			filename = "/home/" + System.getProperty("user.name") + "/Documents/Presensi.csv";
		}

		try {
			try ( FileWriter fw = new FileWriter(filename)) {
				fw.append("NIK");
				fw.append(',');
				fw.append("Hari");
				fw.append(',');
				fw.append("Jam Mulai");
				fw.append(',');
				fw.append("Jam Selesai");
				fw.append(',');
				fw.append("Nama");
				fw.append(',');
				fw.append("Mata Pelajaran");
				fw.append(',');
				fw.append("Ruangan");
				fw.append(',');
				fw.append("Tanggal Masuk");
				fw.append('\n');

				switch (type) {
					case "all":
						sql = sql + " order by p.tanggal desc";
						break;
					case "filtered":
						if (!nik.equals("Semua")) {
							sql = sql + " where p.tanggal between '" + dari + "' and DATE_ADD('" + sampai + "', INTERVAL 1 DAY) and k.nik = '" + nik + "'";
						} else {
							sql = sql + " where p.tanggal between '" + dari + "' and DATE_ADD('" + sampai + "', INTERVAL 1 DAY)";

						}
						break;
					default:
						break;
				}

				System.out.println(type);
				System.out.println(sql);
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					fw.append("'" + rs.getString(1) + "'");
					fw.append(',');
					fw.append(DoStuff.getDay(rs.getInt(2)));
					fw.append(',');
					fw.append(rs.getString(3));
					fw.append(',');
					fw.append(rs.getString(4));
					fw.append(',');
					fw.append(rs.getString(5));
					fw.append(',');
					fw.append(rs.getString(6));
					fw.append(',');
					fw.append(rs.getString(7));
					fw.append(',');
					fw.append(rs.getString(8));
					fw.append('\n');
				}
				fw.flush();
			}
		} catch (SQLException | IOException e) {
		}
	}
}
