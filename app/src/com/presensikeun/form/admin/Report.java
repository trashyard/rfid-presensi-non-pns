package com.presensikeun.form.admin;

import com.presensikeun.controller.Koneksi;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public final class Report extends javax.swing.JPanel {

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public Report() {
		this.con = Koneksi.getKoneksi();
		initComponents();
		tableReport();
		table.scroll(ScrollReport);
	}

	public void tableReport() {
		DefaultTableModel model = new DefaultTableModel();
		int number = 1;
		model.addColumn("NO");
		model.addColumn("NIK");
		model.addColumn("NAMA");
		model.addColumn("STATUS");
		model.addColumn("JABATAN");
		model.addColumn("TANGGAL");
		try {

			String sql = "select tbk.id, tbk.nama, tdp.keterangan, tbj.nama, jadwal.tanggal from tb_detail_presensi as tdp join tb_karyawan as tbk on tbk.id = tdp.id_karyawan join tb_jabatan as tbj on tbk.id_jabatan = tbj.id join tb_jadwal as jadwal on tdp.id_jadwal = jadwal.id";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[]{number++, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
			}
			table.setModel(model);
		} catch (SQLException ex) {
			model.addRow(new Object[]{});
			table.setModel(model);
		}
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                panelShadow5 = new com.presensikeun.swing.PanelShadow();
                ScrollReport = new javax.swing.JScrollPane();
                table = new com.presensikeun.swing.Table();
                panelShadow4 = new com.presensikeun.swing.PanelShadow();
                jLabel2 = new javax.swing.JLabel();

                setBackground(new java.awt.Color(242, 246, 253));

                jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setText("Report");
                jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

                panelShadow5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                ScrollReport.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                table.setBackground(new java.awt.Color(242, 246, 253));
                table.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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
                ScrollReport.setViewportView(table);

                javax.swing.GroupLayout panelShadow5Layout = new javax.swing.GroupLayout(panelShadow5);
                panelShadow5.setLayout(panelShadow5Layout);
                panelShadow5Layout.setHorizontalGroup(
                        panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ScrollReport, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
                                .addContainerGap())
                );
                panelShadow5Layout.setVerticalGroup(
                        panelShadow5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ScrollReport, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                                .addContainerGap())
                );

                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/add.png"))); // NOI18N

                javax.swing.GroupLayout panelShadow4Layout = new javax.swing.GroupLayout(panelShadow4);
                panelShadow4.setLayout(panelShadow4Layout);
                panelShadow4Layout.setHorizontalGroup(
                        panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                );
                panelShadow4Layout.setVerticalGroup(
                        panelShadow4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelShadow5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1))
                                        .addComponent(panelShadow4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelShadow5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JScrollPane ScrollReport;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private com.presensikeun.swing.PanelShadow panelShadow4;
        private com.presensikeun.swing.PanelShadow panelShadow5;
        private com.presensikeun.swing.Table table;
        // End of variables declaration//GEN-END:variables
}
