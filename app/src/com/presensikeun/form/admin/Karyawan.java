package com.presensikeun.form.admin;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.event.EventCallBack;
import com.presensikeun.event.EventTextField;
import com.presensikeun.form.popup.PopUpAddKaryawan;
import com.presensikeun.form.popup.PopUpEditKaryawan;
import com.presensikeun.model.WhatOS;
import com.presensikeun.model.WindowButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public final class Karyawan extends javax.swing.JPanel {

	WindowButton w = new WindowButton();

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public Karyawan() {
		this.con = Koneksi.getKoneksi();
		initComponents();

		// init
		showWinButton();
		searchBar();
		table1.scroll(jScrollPane1);
		getJabatan();
		getGender();
		tableKaryawan("");
	}

	private void showWinButton() {
		if (WhatOS.isWindows()) {
			min.setVisible(true);
			max.setVisible(true);
		} else {
			// i use arch btw + wm hahahahahahahahhahahahahha
			min.setVisible(false);
			max.setVisible(false);
		}
	}

	private void reset() {
		first.setSelectedIndex(-1);
		kelas.setSelectedIndex(-1);
		kelas.setSelectedIndex(-1);
		searchNama.setText("");

		tableKaryawan("");
	}

	private void getGender() {
		kelas.removeAllItems();
		kelas.addItem("Laki-Laki");
		kelas.addItem("Perempuan");
		kelas.setSelectedIndex(-1);
	}

	private String getSelectedGender() {
		String selected = "";
		if (kelas.getSelectedItem().equals("Laki-Laki")) {
			selected = "L";
		} else {
			selected = "P";
		}
		return selected;
	}

	private void getJabatan() {
		first.removeAllItems();
		try {
			String sql = "select nama from tb_jabatan";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				first.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		first.setSelectedIndex(-1);
	}

	public void tableKaryawan(String src) {
		String sql = "select tbk.nik, tbk.nama, jenis_kelamin, tbj.nama as jabatan from tb_karyawan as tbk join tb_jabatan as tbj on tbk.id_jabatan = tbj.id where tbk.nama like '%" + src + "%'";
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("NIK");
		model.addColumn("Nama");
		model.addColumn("Jenis Kelamin");
		model.addColumn("Jabatan");

		// all if elses kalo pas jabatan check, jenis kelamin check, ama dua duanya
		if (first.getSelectedIndex() != -1) {
			sql = sql + "&& tbj.id = (select id from tb_jabatan where nama = '" + first.getSelectedItem() + "')";
		}
		if (kelas.getSelectedIndex() != -1) {
			sql = sql + "&& tbk.jenis_kelamin = '" + getSelectedGender() + "'";
		}
		if (first.getSelectedIndex() != -1 && kelas.getSelectedIndex() != -1) {
			sql = sql + "&& tbk.jenis_kelamin = '" + getSelectedGender() + "' && tbj.id = (select id from tb_jabatan where nama = '" + first.getSelectedItem() + "')";
		}

		// order by nik dari bawah
		sql = sql + " order by tbk.nik desc";

		try {
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
			}
			table1.setModel(model);
		} catch (SQLException ex) {
			model.addRow(new Object[]{});
			table1.setModel(model);
		}
	}

	private void searchBar() {
		searchNama.addEvent(new EventTextField() {
			@Override
			public void onPressed(EventCallBack call) {
				//  Test
				try {
					Thread.sleep(500);
					call.done();
					searchStuff();
				} catch (InterruptedException e) {
					System.err.println(e);
				}
			}

			@Override
			public void onCancel() {

			}
		});

	}

	private void searchStuff() {
		tableKaryawan(searchNama.getText());
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jTextField1 = new javax.swing.JTextField();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                min = new javax.swing.JLabel();
                max = new javax.swing.JLabel();
                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                searchNama = new com.presensikeun.swing.Searchbar();
                jLabel2 = new javax.swing.JLabel();
                labelTable1 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                first = new com.presensikeun.swing.Combobox();
                kelas = new com.presensikeun.swing.Combobox();
                panelShadow2 = new com.presensikeun.swing.PanelShadow();
                jScrollPane1 = new javax.swing.JScrollPane();
                table1 = new com.presensikeun.swing.Table();
                labelTable = new javax.swing.JLabel();

                jTextField1.setText("jTextField1");

                setBackground(new java.awt.Color(250, 250, 250));

                jPanel1.setBackground(new java.awt.Color(85, 65, 118));
                jPanel1.setMinimumSize(new java.awt.Dimension(100, 90));
                jPanel1.setPreferredSize(new java.awt.Dimension(173, 100));

                jLabel1.setBackground(new java.awt.Color(240, 236, 227));
                jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(240, 236, 227));
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-businessman-36.png"))); // NOI18N
                jLabel1.setText("Karyawan");
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

                min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/windows-button/icons8-minus-18.png"))); // NOI18N
                min.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                minMouseClicked(evt);
                        }
                });

                max.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/windows-button/icons8-maximized-18.png"))); // NOI18N
                max.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                maxMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(max, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(22, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(max, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)))
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                );

                panelShadow1.setBackground(new java.awt.Color(252, 254, 255));
                panelShadow1.setPreferredSize(new java.awt.Dimension(234, 72));

                searchNama.setAnimationColor(new java.awt.Color(85, 65, 118));
                searchNama.setHintText("Cari Nama...");
                searchNama.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                searchNamaActionPerformed(evt);
                        }
                });

                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/add.png"))); // NOI18N
                jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jLabel2MousePressed(evt);
                        }
                });

                labelTable1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
                labelTable1.setForeground(new java.awt.Color(85, 65, 118));
                labelTable1.setText("Filter:");

                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/icons8-reset-24.png"))); // NOI18N
                jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jLabel3MouseClicked(evt);
                        }
                });

                first.setLabeText("Jabatan");

                kelas.setLabeText("Jenis Kelamin");
                kelas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                kelasActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTable1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addContainerGap())
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                                        .addComponent(searchNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                );

                panelShadow2.setBackground(new java.awt.Color(252, 254, 255));
                panelShadow2.setPreferredSize(new java.awt.Dimension(496, 386));

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

                labelTable.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
                labelTable.setForeground(new java.awt.Color(85, 65, 118));
                labelTable.setText("Karyawan");

                javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
                panelShadow2.setLayout(panelShadow2Layout);
                panelShadow2Layout.setHorizontalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                                        .addComponent(labelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                );
                panelShadow2Layout.setVerticalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(labelTable)
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                .addContainerGap())
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
                                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents

        private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
		// TODO add your handling code here:
		PopUpAddKaryawan p = new PopUpAddKaryawan((JFrame) SwingUtilities.getWindowAncestor(this));
		p.showMessage(null);
		tableKaryawan("");
        }//GEN-LAST:event_jLabel2MousePressed

        private void table1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MousePressed
		// TODO add your handling code here:
		int row = table1.rowAtPoint(evt.getPoint());
		String nik = table1.getValueAt(row, 0).toString();

		if (evt.getClickCount() == 2 && table1.getSelectedRow() != -1) {
			// your valueChanged overridden method 
			PopUpEditKaryawan p = new PopUpEditKaryawan((JFrame) SwingUtilities.getWindowAncestor(this), nik);
			p.showMessage(null);
			tableKaryawan("");
		}
        }//GEN-LAST:event_table1MousePressed

        private void searchNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchNamaActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_searchNamaActionPerformed

        private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
		// TODO add your handling code here:
		w.setWindow("min", (JFrame) SwingUtilities.getWindowAncestor(this), null);
        }//GEN-LAST:event_minMouseClicked

        private void maxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseClicked
		// TODO add your handling code here:
		w.setWindow("max", (JFrame) SwingUtilities.getWindowAncestor(this), max);
        }//GEN-LAST:event_maxMouseClicked

        private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
		// TODO add your handling code here:
		reset();
        }//GEN-LAST:event_jLabel3MouseClicked

        private void kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kelasActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_kelasActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.Combobox first;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextField jTextField1;
        private com.presensikeun.swing.Combobox kelas;
        private javax.swing.JLabel labelTable;
        private javax.swing.JLabel labelTable1;
        private javax.swing.JLabel max;
        private javax.swing.JLabel min;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        private com.presensikeun.swing.PanelShadow panelShadow2;
        private com.presensikeun.swing.Searchbar searchNama;
        private com.presensikeun.swing.Table table1;
        // End of variables declaration//GEN-END:variables
}
