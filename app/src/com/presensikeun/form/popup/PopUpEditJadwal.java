package com.presensikeun.form.popup;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.swing.Glass;
import com.presensikeun.swing.Notification;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class PopUpEditJadwal extends javax.swing.JDialog {

	private Animator animator;
	private Glass glass;
	private boolean show;
	private final MessageType messageType = MessageType.CANCEL;
	private final JFrame fram;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	String id = null;

	public PopUpEditJadwal(JFrame fram, String id) {
		super(fram, true);
		this.fram = fram;
		this.con = Koneksi.getKoneksi();
		this.id = id;
		initComponents();
		init();

		// initialize
		getMapel();
		getKelas();

		// set for update
		setMapel();
		setKelas();
	}

	private void getMapel() {
		mapel.removeAllItems();
		try {
			String sql = "select id from tb_mapel";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				mapel.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		mapel.setSelectedIndex(-1);
	}

	private void setMapel() {
		try {
			String sql = "select m.id from tb_mapel as m join tb_jadwal as j on m.id = j.id_mapel where j.id = '" + id + "'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				mapel.setSelectedItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
	}

	private void getKelas() {
		kelas.removeAllItems();
		try {
			String sql = "select id from tb_kelas";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				kelas.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		kelas.setSelectedIndex(-1);
	}

	private void setKelas() {
		try {
			String sql = "select k.id from tb_kelas as k join tb_jadwal as j on k.id = j.id_kelas where j.id = '" + id + "'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				kelas.setSelectedItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
	}

	private void getInfo(String type) {
		String sql = null;
		try {
			switch (type) {
				case "mapel":
					sql = "select nama from tb_mapel where id = '" + mapel.getSelectedItem() + "'";
					pst = con.prepareStatement(sql);
					rs = pst.executeQuery();
					if (rs.next()) {
						notify("info", "Mata Pelajaran" + ": " + rs.getString(1));
					}
					break;
				case "kelas":
					sql = "select nama, angkatan from tb_kelas where id = '" + kelas.getSelectedItem() + "'";
					pst = con.prepareStatement(sql);
					rs = pst.executeQuery();
					if (rs.next()) {
						notify("info", rs.getString(1) + ", Kelas " + rs.getString(2));
					}
					break;
				default:
					break;
			}
		} catch (SQLException ex) {
			notify("info", "Mohon isi terlebih dahulu. err: " + ex.getMessage());
		}

	}

	private void updateAndLeave() {
		try {
			String sql = "update tb_jadwal set id_mapel = '" + mapel.getSelectedItem() + "', id_kelas = '" + kelas.getSelectedItem() + "' where id = '" + id + "'";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
			notify("success", "Update Berhasil!");
			closeMessage();
		} catch (SQLException ex) {
			notify("info", "Data duplikat: " + ex.getMessage());
			System.out.println("error: " + ex.getMessage());
		}
	}

	private void deleteAndLeave() {
		try {
			String sql = "delete from tb_jadwal where id = '" + id + "'";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
			notify("success", "Delete Berhasil!");
			closeMessage();
		} catch (SQLException ex) {
			System.out.println("error: " + ex.getMessage());
		}
	}

	private void verifyFields() {
		if (mapel.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi ID Mapel");
		} else if (kelas.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi ID Kelas");
		} else {
			updateAndLeave();
		}
	}

	private void notify(String version, String msg) {
		Notification panel;
		switch (version) {
			case "success":
				panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, msg);
				break;
			case "warning":
				panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, msg);
				break;
			case "info":
				panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.INFO, Notification.Location.TOP_CENTER, msg);
				break;
			default:
				panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.INFO, Notification.Location.TOP_CENTER, "Maksud?");
				break;
		}
		panel.showNotification();
	}

	private void init() {
		setBackground(new Color(0, 0, 0, 0));
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeMessage();
			}
		});
		animator = new Animator(350, new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				float f = show ? fraction : 1f - fraction;
				glass.setAlpha(f - f * 0.3f);
			}

			@Override
			public void end() {
				if (show == false) {
					dispose();
					glass.setVisible(false);
				}
			}
		});
		animator.setResolution(0);
		animator.setAcceleration(.5f);
		animator.setDeceleration(.5f);
		glass = new Glass();
	}

	private void startAnimator(boolean show) {
		if (animator.isRunning()) {
			float f = animator.getTimingFraction();
			animator.stop();
			animator.setStartFraction(1f - f);
		} else {
			animator.setStartFraction(0f);
		}
		this.show = show;
		animator.start();
	}

	public void showMessage(String id) {
		fram.setGlassPane(glass);
		glass.setVisible(true);
		setLocationRelativeTo(fram);
		startAnimator(true);
		setVisible(true);
	}

	public void closeMessage() {
		this.setVisible(false);
		startAnimator(false);
	}

	public MessageType getMessageType() {
		return messageType;
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                timePicker1 = new com.presensikeun.swing.TimePicker();
                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                mapel = new com.presensikeun.swing.Combobox();
                detailMapel = new com.presensikeun.swing.Button();
                kelas = new com.presensikeun.swing.Combobox();
                detailKelas = new com.presensikeun.swing.Button();
                button2 = new com.presensikeun.swing.Button();
                delete = new com.presensikeun.swing.Button();
                button1 = new com.presensikeun.swing.Button();

                timePicker1.setForeground(new java.awt.Color(85, 65, 118));

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setUndecorated(true);

                jPanel1.setBackground(new java.awt.Color(252, 254, 255));

                jPanel2.setBackground(new java.awt.Color(85, 65, 118));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 355, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
                );

                mapel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", " " }));
                mapel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                mapel.setLabeText("ID Mapel");

                detailMapel.setBackground(new java.awt.Color(85, 65, 118));
                detailMapel.setForeground(new java.awt.Color(255, 255, 255));
                detailMapel.setText("D");
                detailMapel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                detailMapel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                detailMapelActionPerformed(evt);
                        }
                });

                kelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", " " }));
                kelas.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                kelas.setLabeText("ID Kelas");

                detailKelas.setBackground(new java.awt.Color(85, 65, 118));
                detailKelas.setForeground(new java.awt.Color(255, 255, 255));
                detailKelas.setText("D");
                detailKelas.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                detailKelas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                detailKelasActionPerformed(evt);
                        }
                });

                button2.setBackground(new java.awt.Color(255, 0, 51));
                button2.setForeground(new java.awt.Color(255, 255, 255));
                button2.setText("Cancel");
                button2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                button2.setPreferredSize(new java.awt.Dimension(80, 30));
                button2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                button2ActionPerformed(evt);
                        }
                });

                delete.setBackground(new java.awt.Color(255, 102, 102));
                delete.setForeground(new java.awt.Color(255, 255, 255));
                delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-remove-24.png"))); // NOI18N
                delete.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                delete.setPreferredSize(new java.awt.Dimension(80, 30));
                delete.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                deleteActionPerformed(evt);
                        }
                });

                button1.setBackground(new java.awt.Color(0, 204, 0));
                button1.setForeground(new java.awt.Color(255, 255, 255));
                button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-edit-24.png"))); // NOI18N
                button1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                button1.setPreferredSize(new java.awt.Dimension(80, 30));
                button1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                button1ActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(detailMapel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(detailKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(38, 38, 38))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(detailMapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(detailKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(45, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void detailMapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailMapelActionPerformed
		// TODO add your handling code here:
		getInfo("mapel");
        }//GEN-LAST:event_detailMapelActionPerformed

        private void detailKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailKelasActionPerformed
		// TODO add your handling code here:
		getInfo("kelas");
        }//GEN-LAST:event_detailKelasActionPerformed

        private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
		closeMessage();
		// TODO add your handling code here:
        }//GEN-LAST:event_button2ActionPerformed

        private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
		// TODO add your handling code here:
		deleteAndLeave();
        }//GEN-LAST:event_deleteActionPerformed

        private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
		// TODO add your handling code here:
		//		insertAndLeave();
		verifyFields();
        }//GEN-LAST:event_button1ActionPerformed

	public static enum MessageType {
		CANCEL, OK
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.Button button1;
        private com.presensikeun.swing.Button button2;
        private com.presensikeun.swing.Button delete;
        private com.presensikeun.swing.Button detailKelas;
        private com.presensikeun.swing.Button detailMapel;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private com.presensikeun.swing.Combobox kelas;
        private com.presensikeun.swing.Combobox mapel;
        private com.presensikeun.swing.TimePicker timePicker1;
        // End of variables declaration//GEN-END:variables
}
