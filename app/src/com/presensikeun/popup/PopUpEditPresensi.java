package com.presensikeun.popup;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.form.Presensi;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class PopUpEditPresensi extends javax.swing.JFrame {

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String id;

	public PopUpEditPresensi(String id) {
		this.con = Koneksi.getKoneksi();
		this.id = id;
		initComponents();
		disableTextFields();
		getData(id);
		System.out.println(id);
		nik.setText(id);
	}

	private PopUpEditPresensi() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	public void disableTextFields() {
		nama.disable();
	}

	public void getData(String id) {
		try {
			String sql = "select tbk.nama, tbj.nama as jabatan from tb_karyawan as tbk join tb_jabatan as tbj on tbk.id_jabatan = tbj.id where tbk.id = " + id;
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				nama.setText(rs.getString(1));
			}
		} catch (SQLException ex) {
		}
	}

	public void setData(String id) {
		try {
			String sql = "insert into tb_detail_presensi values('" + (String) statusbox.getSelectedItem() + "', CURRENT_TIMESTAMP, 1," + id + ");";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (SQLException ex) {
		}
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                panelBorder1 = new com.presensikeun.swing.PanelBorder();
                nik = new javax.swing.JTextField();
                statusbox = new javax.swing.JComboBox<>();
                jabatanbox = new javax.swing.JComboBox<>();
                nama = new javax.swing.JTextField();
                close = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();
                ok = new javax.swing.JPanel();
                jLabel7 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setUndecorated(true);

                panelBorder1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

                nik.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
                nik.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                nik.setBorder(null);
                nik.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusGained(java.awt.event.FocusEvent evt) {
                                nikFocusGained(evt);
                        }
                        public void focusLost(java.awt.event.FocusEvent evt) {
                                nikFocusLost(evt);
                        }
                });
                nik.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                nikMouseClicked(evt);
                        }
                });

                statusbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hadir", "Alpa", "Sakit", "Izin" }));
                statusbox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
                        public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                        }
                        public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                                statusboxPopupMenuWillBecomeInvisible(evt);
                        }
                        public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                        }
                });
                statusbox.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                statusboxActionPerformed(evt);
                        }
                });

                jabatanbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                jabatanbox.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jabatanboxActionPerformed(evt);
                        }
                });

                nama.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
                nama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                nama.setBorder(null);
                nama.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusGained(java.awt.event.FocusEvent evt) {
                                namaFocusGained(evt);
                        }
                        public void focusLost(java.awt.event.FocusEvent evt) {
                                namaFocusLost(evt);
                        }
                });
                nama.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                namaMouseClicked(evt);
                        }
                });

                close.setBackground(new java.awt.Color(244, 67, 54));
                close.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                closeMousePressed(evt);
                        }
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                closeMouseExited(evt);
                        }
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                closeMouseEntered(evt);
                        }
                });

                jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                jLabel6.setText("CLOSE");

                javax.swing.GroupLayout closeLayout = new javax.swing.GroupLayout(close);
                close.setLayout(closeLayout);
                closeLayout.setHorizontalGroup(
                        closeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, closeLayout.createSequentialGroup()
                                .addContainerGap(17, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                );
                closeLayout.setVerticalGroup(
                        closeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                );

                ok.setBackground(new java.awt.Color(31, 177, 65));
                ok.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                okMousePressed(evt);
                        }
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                okMouseExited(evt);
                        }
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                okMouseEntered(evt);
                        }
                });

                jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
                jLabel7.setText("OK");
                jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jLabel7MouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout okLayout = new javax.swing.GroupLayout(ok);
                ok.setLayout(okLayout);
                okLayout.setHorizontalGroup(
                        okLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, okLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                okLayout.setVerticalGroup(
                        okLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
                panelBorder1.setLayout(panelBorder1Layout);
                panelBorder1Layout.setHorizontalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nik, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(nama, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                                .addComponent(jabatanbox, 0, 210, Short.MAX_VALUE)
                                                .addComponent(statusbox, 0, 210, Short.MAX_VALUE)
                                                .addGroup(panelBorder1Layout.createSequentialGroup()
                                                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(10, 10, 10)
                                                        .addComponent(ok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(39, Short.MAX_VALUE))
                );
                panelBorder1Layout.setVerticalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addContainerGap(56, Short.MAX_VALUE)
                                .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jabatanbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(statusbox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        private void okMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMouseEntered
		ok.setBackground(new Color(6, 152, 40));
        }//GEN-LAST:event_okMouseEntered

        private void okMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMouseExited
		ok.setBackground(new Color(31, 177, 65));
        }//GEN-LAST:event_okMouseExited

        private void okMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMousePressed
		// TODO add your handling code here:
		this.setVisible(false);
        }//GEN-LAST:event_okMousePressed

        private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
		// TODO add your handling code here:
		this.setVisible(false);
		setData(id);
		Presensi p = new Presensi();
		p.tablePresensi();
        }//GEN-LAST:event_jLabel7MouseClicked

        private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
		close.setBackground(new Color(219, 42, 29));
        }//GEN-LAST:event_closeMouseEntered

        private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
		close.setBackground(new Color(244, 67, 54));
        }//GEN-LAST:event_closeMouseExited

        private void closeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMousePressed
		this.setVisible(false);
        }//GEN-LAST:event_closeMousePressed

        private void namaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_namaMouseClicked

        }//GEN-LAST:event_namaMouseClicked

        private void namaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namaFocusLost
		if (nama.getText().equals("")) {
			nama.setText("Masukkan Nama");
			nama.setForeground(new Color(204, 204, 204));
		}
        }//GEN-LAST:event_namaFocusLost

        private void namaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namaFocusGained
		if (nama.getText().equals("Masukkan Nama")) {
			nama.setText("");
			nama.setForeground(new Color(0, 0, 0));
		}
        }//GEN-LAST:event_namaFocusGained

        private void statusboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusboxActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_statusboxActionPerformed

        private void statusboxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_statusboxPopupMenuWillBecomeInvisible
		// TODO add your handling code here:
        }//GEN-LAST:event_statusboxPopupMenuWillBecomeInvisible

        private void jabatanboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanboxActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_jabatanboxActionPerformed

        private void nikFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nikFocusGained
		// TODO add your handling code here:
        }//GEN-LAST:event_nikFocusGained

        private void nikFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nikFocusLost
		// TODO add your handling code here:
        }//GEN-LAST:event_nikFocusLost

        private void nikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nikMouseClicked
		// TODO add your handling code here:
        }//GEN-LAST:event_nikMouseClicked

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(PopUpEditPresensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(PopUpEditPresensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(PopUpEditPresensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(PopUpEditPresensi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new PopUpEditPresensi().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel close;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JComboBox<String> jabatanbox;
        private javax.swing.JTextField nama;
        private javax.swing.JTextField nik;
        private javax.swing.JPanel ok;
        private com.presensikeun.swing.PanelBorder panelBorder1;
        private javax.swing.JComboBox<String> statusbox;
        // End of variables declaration//GEN-END:variables
}
