package com.presensikeun.form.user;

import com.presensikeun.controller.Koneksi;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public final class Presensi extends javax.swing.JPanel {

	String id = null;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public Presensi() {
		this.con = Koneksi.getKoneksi();
		initComponents();
	}

	public Presensi(String id) {
		this.con = Koneksi.getKoneksi();
		this.id = id;
		initComponents();
		tablePresensiUser();
	}

	public void tablePresensiUser() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Date");
		model.addColumn("Jam Mengajar");
		model.addColumn("Status");
		model.addColumn("Nama");
		model.addColumn("Mata Pelajaran");
		try {

			String sql = "select dj.tanggal as \"Date\", CONCAT(dj.jam, \" Jam\") as \"Jam Mengajar\", dj.status as \"Status\", k.nama as \"Nama Guru\", m.nama as \"Nama Mapel\" from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id where k.id = " + id;
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2), getStatus(rs.getString(3)), rs.getString(4), rs.getString(5)});
			}
			table.setModel(model);
		} catch (SQLException ex) {
			model.addRow(new Object[]{});
			table.setModel(model);
		}
	}

	public String getStatus(String status) {
		String string;
		if (status.equals("?")) {
			string = "Submit Attendance";
		} else {
			string = status;
		}
		return string;
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                panelBorder1 = new com.presensikeun.swing.PanelBorder();
                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                jScrollPane1 = new javax.swing.JScrollPane();
                table = new com.presensikeun.swing.Table();
                panelShadow2 = new com.presensikeun.swing.PanelShadow();

                jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                table.setModel(new javax.swing.table.DefaultTableModel(
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
                table.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tableMouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(table);

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
                panelShadow2.setLayout(panelShadow2Layout);
                panelShadow2Layout.setHorizontalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 68, Short.MAX_VALUE)
                );
                panelShadow2Layout.setVerticalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 34, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
                panelBorder1.setLayout(panelBorder1Layout);
                panelBorder1Layout.setHorizontalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelShadow1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                panelBorder1Layout.setVerticalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
		// TODO add your handling code here:
		int row = table.rowAtPoint(evt.getPoint());
		String status = table.getValueAt(row, 2).toString();
		if (status.equals("?")) {
			System.out.println("yoi");
		} else {
			System.out.println("ga boleh ngabsss");
		}
        }//GEN-LAST:event_tableMouseClicked

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JScrollPane jScrollPane1;
        private com.presensikeun.swing.PanelBorder panelBorder1;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        private com.presensikeun.swing.PanelShadow panelShadow2;
        private com.presensikeun.swing.Table table;
        // End of variables declaration//GEN-END:variables
}
