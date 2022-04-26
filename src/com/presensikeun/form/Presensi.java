package com.presensikeun.form;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.popup.PopUpEditPresensi;
import com.presensikeun.popup.PopUpPresensi;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public final class Presensi extends javax.swing.JPanel {

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public Presensi() {
		this.con = Koneksi.getKoneksi();
		initComponents();
		tablePresensi();
		table1.scroll(jScrollPane1);
	}

	public void tablePresensi() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("NIK");
		model.addColumn("NAMA");
		model.addColumn("STATUS");
		model.addColumn("JABATAN");
		model.addColumn("TANGGAL");
		try {

			String sql = "select tbk.id, tbk.nama, tdp.keterangan, tbj.nama, tdp.tanggal from tb_detail_presensi as tdp join tb_karyawan as tbk on tbk.id = tdp.id_karyawan join tb_jabatan as tbj on tbk.id_jabatan = tbj.id join tb_jadwal as jadwal on tdp.id_jadwal = jadwal.id order by tdp.tanggal desc";
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

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                jScrollPane1 = new javax.swing.JScrollPane();
                table1 = new com.presensikeun.swing.Table();
                panelShadow2 = new com.presensikeun.swing.PanelShadow();
                jLabel2 = new javax.swing.JLabel();

                setBackground(new java.awt.Color(242, 246, 253));
                setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setText("Presensi");
                jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

                panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                table1.setBackground(new java.awt.Color(242, 246, 253));
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
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                table1MouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(table1);

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
                                .addContainerGap())
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addContainerGap())
                );

                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/add.png"))); // NOI18N
                jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jLabel2MousePressed(evt);
                        }
                });

                javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
                panelShadow2.setLayout(panelShadow2Layout);
                panelShadow2Layout.setHorizontalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                );
                panelShadow2Layout.setVerticalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1))
                                        .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents

        private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
		// TODO add your handling code here:
		new PopUpPresensi().setVisible(true);
        }//GEN-LAST:event_jLabel2MousePressed

        private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
		// TODO add your handling code here:
		int row = table1.rowAtPoint(evt.getPoint());
		String id = table1.getValueAt(row, 0).toString();
		new PopUpEditPresensi(id).setVisible(true);
        }//GEN-LAST:event_table1MouseClicked

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JScrollPane jScrollPane1;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        private com.presensikeun.swing.PanelShadow panelShadow2;
        private com.presensikeun.swing.Table table1;
        // End of variables declaration//GEN-END:variables
}
