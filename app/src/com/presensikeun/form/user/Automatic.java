package com.presensikeun.form.user;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.swing.Notification;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public final class Automatic extends javax.swing.JPanel {

	String id = null;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	String barcode = "";

	public Automatic() {
		initComponents();
		init();
	}

	public Automatic(String id) {
		initComponents();
		init();
		this.id = id;
	}

	public void init() {
		this.con = Koneksi.getKoneksi();
	}

	public void automaticAttendance(String input) {

		try {
			String sql = "select dj.id, dj.hari as \"Date\", dj.jam as \"Jam Mulai\", ADDTIME(dj.jam, dj.durasi) as \"Jam Selesai\", m.nama as \"Nama Mapel\" from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id where k.nik = " + input + " and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi) && dj.hari = WEEKDAY(CURRENT_DATE) && dj.id not in (select p.id_detail_jadwal from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id where weekday(p.tanggal) = weekday(current_date) and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi)))) order by ADDTIME(dj.jam, dj.durasi) asc limit 1;";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				submitAttendance(rs.getString(1));
			} else {
				Notification panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.CENTER, "Tidak ditemukan presensi");
				panel.showNotification();
			}

		} catch (SQLException ex) {

		}

	}

	public void submitAttendance(String id) {
		try {
			String sql = "INSERT INTO tb_presensi (id, tanggal, keterangan, id_detail_jadwal) VALUES (NULL, current_timestamp(), '?', " + id + ")";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
			Notification panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.CENTER, rs.getString(5) + ": " + rs.getString(3));
			panel.showNotification();
		} catch (SQLException ex) {
			Notification panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.CENTER, "Gagal memasukkan data");
			panel.showNotification();
		}

	}

	private String setBarcode(String barcode) {
		return this.barcode = barcode;
	}

	private String getBarcode() {
		return this.barcode;
	}

	private void afterEnter() {
		automaticAttendance(this.barcode);
		setBarcode("");
		nik.setText("");
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                panelBorder1 = new com.presensikeun.swing.PanelBorder();
                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                nik = new com.presensikeun.swing.TextField();

                addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                formKeyReleased(evt);
                        }
                });

                panelBorder1.setBackground(new java.awt.Color(250, 250, 250));

                panelShadow1.setBackground(new java.awt.Color(252, 254, 255));

                nik.setBackground(new java.awt.Color(252, 254, 255));
                nik.setLabelText("NIK");
                nik.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                nikKeyReleased(evt);
                        }
                });

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                .addContainerGap(24, Short.MAX_VALUE)
                                .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(52, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
                panelBorder1.setLayout(panelBorder1Layout);
                panelBorder1Layout.setHorizontalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(246, Short.MAX_VALUE))
                );
                panelBorder1Layout.setVerticalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(181, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
        }// </editor-fold>//GEN-END:initComponents

        private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
		// TODO add your handling code here:
        }//GEN-LAST:event_formKeyReleased

        private void nikKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nikKeyReleased
		// TODO add your handling code here:
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			// your code is scanned and you can access it using frame.getBarCode()
			// now clean the bar code so the next one can be read
			System.out.println("loldek: " + getBarcode());
			afterEnter();
		} else {
			// some character has been read, append it to your "barcode cache"
			setBarcode(getBarcode() + evt.getKeyChar());
		}
        }//GEN-LAST:event_nikKeyReleased

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.TextField nik;
        private com.presensikeun.swing.PanelBorder panelBorder1;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        // End of variables declaration//GEN-END:variables
}
