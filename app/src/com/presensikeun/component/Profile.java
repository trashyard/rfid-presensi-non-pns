package com.presensikeun.component;

import com.presensikeun.controller.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Profile extends javax.swing.JPanel {

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public Profile() {
		this.con = Koneksi.getKoneksi();
		initComponents();
		setOpaque(false);
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                imageAvatar1 = new com.presensikeun.swing.ImageAvatar();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();

                setBackground(new java.awt.Color(53, 33, 89));

                imageAvatar1.setBackground(new java.awt.Color(255, 255, 255));
                imageAvatar1.setForeground(new java.awt.Color(255, 255, 255));
                imageAvatar1.setBorderSize(2);
                imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/urmom.jpg"))); // NOI18N

                jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("SMKN 8 Jember");

                jLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                jLabel2.setText("Dashboard Presensi");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, 0)
                                                .addComponent(jLabel2))
                                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                );
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.ImageAvatar imageAvatar1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        // End of variables declaration//GEN-END:variables
}
