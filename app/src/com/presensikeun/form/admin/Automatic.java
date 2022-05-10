package com.presensikeun.form.admin;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.swing.Notification;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SwingUtilities;

public final class Automatic extends javax.swing.JPanel {

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	String barcode = "";

	public Automatic() {
		this.con = Koneksi.getKoneksi();
		initComponents();
	}

	public void automaticAttendance(String input) {

		try {
			String sql = "select dj.id, k.nama, dj.hari as \"Date\", dj.jam as \"Jam Mulai\", ADDTIME(dj.jam, dj.durasi) as \"Jam Selesai\", m.nama as \"Nama Mapel\" from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id where k.nik = " + input + " and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi) && dj.hari = WEEKDAY(CURRENT_DATE) && dj.id not in (select p.id_detail_jadwal from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id where weekday(p.tanggal) = weekday(current_date) and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi)))) order by ADDTIME(dj.jam, dj.durasi) asc limit 1;";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				lastAttendance.setText("Last Attendance: " + rs.getString(2) + ":" + rs.getString(5) + ":" + rs.getString(6));
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

                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                panelShadow2 = new com.presensikeun.swing.PanelShadow();
                nik = new com.presensikeun.swing.TextField();
                lastAttendance = new javax.swing.JLabel();

                setBackground(new java.awt.Color(250, 250, 250));

                jPanel1.setBackground(new java.awt.Color(85, 65, 118));
                jPanel1.setMinimumSize(new java.awt.Dimension(100, 90));
                jPanel1.setPreferredSize(new java.awt.Dimension(173, 100));

                jLabel1.setBackground(new java.awt.Color(240, 236, 227));
                jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(240, 236, 227));
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-automation-36.png"))); // NOI18N
                jLabel1.setText("Automatic");
                jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 8, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 816, Short.MAX_VALUE)))
                                .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                );

                panelShadow2.setBackground(new java.awt.Color(252, 254, 255));

                nik.setBackground(new java.awt.Color(252, 254, 255));
                nik.setLabelText("NIK");
                nik.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                nikKeyReleased(evt);
                        }
                });

                lastAttendance.setText("Last Attendance:");

                javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
                panelShadow2.setLayout(panelShadow2Layout);
                panelShadow2Layout.setHorizontalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow2Layout.createSequentialGroup()
                                .addGap(317, 317, 317)
                                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nik, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                                        .addComponent(lastAttendance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panelShadow2Layout.setVerticalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow2Layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(lastAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(316, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents

        private void nikKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nikKeyReleased
		// TODO add your handling code here:
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			// your code is scanned and you can access it using frame.getBarCode()
			// now clean the bar code so the next one can be read
			Pattern p = Pattern.compile("-?\\d+");
			Matcher m = p.matcher(getBarcode());
			while (m.find()) {
				setBarcode(m.group());
			}
			afterEnter();
		} else {
			// some character has been read, append it to your "barcode cache"
			setBarcode(getBarcode() + evt.getKeyChar());
		}
        }//GEN-LAST:event_nikKeyReleased

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabel1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JLabel lastAttendance;
        private com.presensikeun.swing.TextField nik;
        private com.presensikeun.swing.PanelShadow panelShadow2;
        // End of variables declaration//GEN-END:variables
}
