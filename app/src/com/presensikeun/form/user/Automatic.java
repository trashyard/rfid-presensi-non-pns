package com.presensikeun.form.user;

import com.presensikeun.controller.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Automatic extends javax.swing.JPanel {

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
	}

	public void init() {
		this.con = Koneksi.getKoneksi();
		listKaryawan();
	}

	public void listKaryawan() {
		combobox.removeAllItems();

		try {
			String sql = "select id from tb_karyawan";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				combobox.addItem(rs.getString(1));
			}

		} catch (SQLException ex) {

		}
	}

	public String getKaryawan() {
		String string = null;

		try {
			String sql = "select nama from tb_karyawan where id = " + combobox.getSelectedItem();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				string = rs.getString(1);
			}

		} catch (SQLException ex) {
			string = "error dek";
		}

		return string;
	}

	public void automaticPresence(String id) {

		try {
			String sql = "select dj.id, dj.hari as \"Date\", dj.jam as \"Jam Mulai\", ADDTIME(dj.jam, dj.durasi) as \"Jam Selesai\", dj.status as \"Status\", m.nama as \"Nama Mapel\" from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id where k.id = " + id + " and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi) && dj.hari = WEEKDAY(CURRENT_DATE)) and dj.status = \"?\" order by ADDTIME(dj.jam, dj.durasi) asc limit 1";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				System.out.println("dapet ngab jadwalnya:" + rs.getString(1) + " " + rs.getString(2));
				sql = "update tb_detail_jadwal set status = 'recorded' where id = " + rs.getString(1);
				pst = con.prepareStatement(sql);
				pst.executeUpdate();
			} else {
				System.out.println("gada presensi dek");
			}

		} catch (SQLException ex) {

		}

	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                panelBorder1 = new com.presensikeun.swing.PanelBorder();
                combobox = new javax.swing.JComboBox<>();
                submit = new javax.swing.JButton();
                label = new javax.swing.JLabel();

                combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                combobox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
                        public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                        }
                        public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                                comboboxPopupMenuWillBecomeInvisible(evt);
                        }
                        public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                        }
                });

                submit.setText("CEK AND SUBMIT NGAB");
                submit.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                submitMouseClicked(evt);
                        }
                });

                label.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
                label.setText("AWOKWKOWKOW");

                javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
                panelBorder1.setLayout(panelBorder1Layout);
                panelBorder1Layout.setHorizontalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                                .addComponent(label)
                                .addGap(30, 30, 30))
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(submit)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panelBorder1Layout.setVerticalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGap(157, 157, 157)
                                                .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(32, 32, 32)
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

        private void comboboxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboboxPopupMenuWillBecomeInvisible
		// TODO add your handling code here:
		label.setText(getKaryawan());
        }//GEN-LAST:event_comboboxPopupMenuWillBecomeInvisible

        private void submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitMouseClicked
		// TODO add your handling code here:
		automaticPresence((String) combobox.getSelectedItem());
        }//GEN-LAST:event_submitMouseClicked

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JComboBox<String> combobox;
        private javax.swing.JLabel label;
        private com.presensikeun.swing.PanelBorder panelBorder1;
        private javax.swing.JButton submit;
        // End of variables declaration//GEN-END:variables
}
