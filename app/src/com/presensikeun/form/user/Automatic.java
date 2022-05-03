package com.presensikeun.form.user;

import com.presensikeun.controller.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public final class Automatic extends javax.swing.JPanel {

	String id = null;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

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

	public void automaticAttendance(String id) {

		try {
			String sql = "select dj.id, dj.hari as \"Date\", dj.jam as \"Jam Mulai\", ADDTIME(dj.jam, dj.durasi) as \"Jam Selesai\", m.nama as \"Nama Mapel\" from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id where k.id = " + id + " and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi) && dj.hari = WEEKDAY(CURRENT_DATE) && dj.id not in (select p.id_detail_jadwal from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id where weekday(p.tanggal) = weekday(current_date) and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi)))) order by ADDTIME(dj.jam, dj.durasi) asc limit 1;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(this, "ok dek ketemu: " + rs.getString(3) + " Mapel: " + rs.getString(5), "UR MOM GAYYY", JOptionPane.INFORMATION_MESSAGE);
				submitAttendance(rs.getString(1));
			} else {
				JOptionPane.showMessageDialog(this, "ga ada presensi dek, turu aja", "UR MOM GAYYY", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException ex) {

		}

	}

	public void submitAttendance(String id) {
		try {
			String sql = "INSERT INTO tb_presensi (id, tanggal, keterangan, id_detail_jadwal) VALUES (NULL, current_timestamp(), '?', " + id + ")";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "dah mashok datamu", "UR MOM GAYYY", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException ex) {

		}

	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                panelBorder1 = new com.presensikeun.swing.PanelBorder();
                submit = new javax.swing.JButton();

                submit.setText("CEK AND SUBMIT NGAB");
                submit.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                submitMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
                panelBorder1.setLayout(panelBorder1Layout);
                panelBorder1Layout.setHorizontalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(submit)
                                .addContainerGap(391, Short.MAX_VALUE))
                );
                panelBorder1Layout.setVerticalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(249, 249, 249)
                                .addComponent(submit)
                                .addContainerGap(169, Short.MAX_VALUE))
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

        private void submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseClicked
		// TODO add your handling code here:
		automaticAttendance(id);
        }//GEN-LAST:event_submitMouseClicked

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.PanelBorder panelBorder1;
        private javax.swing.JButton submit;
        // End of variables declaration//GEN-END:variables
}
