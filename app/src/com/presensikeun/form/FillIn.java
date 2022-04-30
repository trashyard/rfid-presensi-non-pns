package com.presensikeun.form;

import com.presensikeun.controller.Koneksi;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class FillIn extends javax.swing.JFrame {

	String id = null;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public FillIn() {
		initComponents();
	}

	public FillIn(String id) {
		initComponents();
		initialize();
		getMapel(id);
		this.id = id;
	}

	public void initialize() {
		this.con = Koneksi.getKoneksi();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
		hadir.setActionCommand("hadir");
		izin.setActionCommand("izin");
		sakit.setActionCommand("sakit");
		alpa.setActionCommand("alpa");
		presensi.add(hadir);
		presensi.add(izin);
		presensi.add(alpa);
		presensi.add(sakit);
	}

	public void getMapel(String id) {
		try {

			String sql = "select m.nama from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_mapel as m on j.id_mapel = m.id where dj.id = " + id;
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				mapel.setText(rs.getString(1));
			}
		} catch (SQLException ex) {
		}
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                presensi = new javax.swing.ButtonGroup();
                panelBorder1 = new com.presensikeun.swing.PanelBorder();
                mapel = new javax.swing.JLabel();
                hadir = new javax.swing.JRadioButton();
                izin = new javax.swing.JRadioButton();
                sakit = new javax.swing.JRadioButton();
                alpa = new javax.swing.JRadioButton();
                jButton1 = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                mapel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                mapel.setText("Cause i thought it was ovaaaahhh");

                hadir.setBackground(new java.awt.Color(242, 246, 253));
                hadir.setText("Hadir");
                hadir.setOpaque(true);

                izin.setBackground(new java.awt.Color(242, 246, 253));
                izin.setText("Izin");
                izin.setOpaque(true);
                izin.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                izinActionPerformed(evt);
                        }
                });

                sakit.setBackground(new java.awt.Color(242, 246, 253));
                sakit.setText("Sakit");
                sakit.setOpaque(true);

                alpa.setBackground(new java.awt.Color(242, 246, 253));
                alpa.setText("Alpa");
                alpa.setOpaque(true);

                jButton1.setText("cancel");
                jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jButton1MouseClicked(evt);
                        }
                });

                jButton2.setText("boom");
                jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jButton2MouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
                panelBorder1.setLayout(panelBorder1Layout);
                panelBorder1Layout.setHorizontalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGap(195, 195, 195)
                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(mapel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                .addComponent(hadir)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(izin)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(sakit)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(alpa))))
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGap(228, 228, 228)
                                                .addComponent(jButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton2)))
                                .addContainerGap(231, Short.MAX_VALUE))
                );
                panelBorder1Layout.setVerticalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(hadir)
                                        .addComponent(izin)
                                        .addComponent(sakit)
                                        .addComponent(alpa))
                                .addGap(18, 18, 18)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(139, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void izinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izinActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_izinActionPerformed

        private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
		// TODO add your handling code here:
		this.setVisible(false);
		new Presensi().tablePresensi();
        }//GEN-LAST:event_jButton1MouseClicked

        private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
		// TODO add your handling code here:
		this.setVisible(false);
		String status = presensi.getSelection().getActionCommand();
		try {
			String sql = "update tb_detail_jadwal set status = '" + status + "' where id = " + id;
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("message: " + ex.getMessage());
		}

        }//GEN-LAST:event_jButton2MouseClicked

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
			java.util.logging.Logger.getLogger(FillIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FillIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FillIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FillIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FillIn().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JRadioButton alpa;
        private javax.swing.JRadioButton hadir;
        private javax.swing.JRadioButton izin;
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JLabel mapel;
        private com.presensikeun.swing.PanelBorder panelBorder1;
        private javax.swing.ButtonGroup presensi;
        private javax.swing.JRadioButton sakit;
        // End of variables declaration//GEN-END:variables
}
