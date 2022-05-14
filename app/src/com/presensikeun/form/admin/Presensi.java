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
import javax.swing.table.DefaultTableModel;

public final class Presensi extends javax.swing.JPanel {

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	String barcode = "";

	public Presensi() {
		this.con = Koneksi.getKoneksi();
		initComponents();
		table1.scroll(jScrollPane1);
		tablePresensi("");
	}

	public void tablePresensi(String query) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("NIK");
		model.addColumn("Nama");
		model.addColumn("Keterangan");
		model.addColumn("Mata Pelajaran");
		model.addColumn("Tanggal");
		try {

			String sql = "select k.nik, k.nama, p.keterangan, mp.nama, p.tanggal from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id join tb_jadwal as j on j.id = dj.id_jadwal join tb_mapel as mp on j.id_mapel = mp.id join tb_karyawan as k on dj.id_karyawan = k.id where k.nama like '%" + query + "%' and date(p.tanggal) = current_date order by p.tanggal desc";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
			}
			table1.setModel(model);
		} catch (SQLException ex) {
			model.addRow(new Object[]{});
			table1.setModel(model);
		}
	}

	public void automaticAttendance(String input) {

		try {
			String sql = "select dj.id, k.nama, dj.hari as \"Date\", dj.jam as \"Jam Mulai\", ADDTIME(dj.jam, dj.durasi) as \"Jam Selesai\", m.nama as \"Nama Mapel\" from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id where k.nik = '" + input + "' and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi) && dj.hari = WEEKDAY(CURRENT_DATE) && dj.id not in (select p.id_detail_jadwal from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id where weekday(p.tanggal) = weekday(current_date) and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi)))) order by ADDTIME(dj.jam, dj.durasi) asc limit 1;";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				submitAttendance(rs.getString(1));
				tablePresensi("");
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
			System.out.println(sql);
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
                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                nik = new com.presensikeun.swing.TextField();
                button1 = new com.presensikeun.swing.Button();
                panelShadow2 = new com.presensikeun.swing.PanelShadow();
                jScrollPane1 = new javax.swing.JScrollPane();
                table1 = new com.presensikeun.swing.Table();

                setBackground(new java.awt.Color(250, 250, 250));

                jPanel1.setBackground(new java.awt.Color(85, 65, 118));
                jPanel1.setMinimumSize(new java.awt.Dimension(100, 90));
                jPanel1.setPreferredSize(new java.awt.Dimension(173, 100));

                jLabel1.setBackground(new java.awt.Color(240, 236, 227));
                jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(240, 236, 227));
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-attendance-30.png"))); // NOI18N
                jLabel1.setText("Presensi");
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
                                                .addGap(0, 0, Short.MAX_VALUE)))
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

                panelShadow1.setBackground(new java.awt.Color(252, 254, 255));

                nik.setBackground(new java.awt.Color(252, 254, 255));
                nik.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                nik.setToolTipText("");
                nik.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                nik.setLabelText("NIK");
                nik.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                nikKeyReleased(evt);
                        }
                });

                button1.setBackground(new java.awt.Color(78, 148, 79));
                button1.setForeground(new java.awt.Color(255, 255, 255));
                button1.setText("Scan Barcode");
                button1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                button1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                button1ActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(nik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                        .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(0, 0, 0))
                );

                panelShadow2.setBackground(new java.awt.Color(252, 254, 255));

                jScrollPane1.setBackground(new java.awt.Color(252, 254, 255));
                jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                table1.setBackground(new java.awt.Color(252, 254, 255));
                table1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                table1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null}
                        },
                        new String [] {
                                "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                ));
                table1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                table1MousePressed(evt);
                        }
                });
                jScrollPane1.setViewportView(table1);

                javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
                panelShadow2.setLayout(panelShadow2Layout);
                panelShadow2Layout.setHorizontalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
                                .addContainerGap())
                );
                panelShadow2Layout.setVerticalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                                .addContainerGap())
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1011, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents

        private void table1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MousePressed
		// TODO add your handling code here:
        }//GEN-LAST:event_table1MousePressed

        private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
		// TODO add your handling code here:
		nik.requestFocus();
        }//GEN-LAST:event_button1ActionPerformed

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
        private com.presensikeun.swing.Button button1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane jScrollPane1;
        private com.presensikeun.swing.TextField nik;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        private com.presensikeun.swing.PanelShadow panelShadow2;
        private com.presensikeun.swing.Table table1;
        // End of variables declaration//GEN-END:variables
}
