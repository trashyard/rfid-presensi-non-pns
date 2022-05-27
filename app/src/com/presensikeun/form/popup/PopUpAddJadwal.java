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

public class PopUpAddJadwal extends javax.swing.JDialog {

	private Animator animator;
	private Glass glass;
	private boolean show;
	private final MessageType messageType = MessageType.CANCEL;
	private final JFrame fram;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public PopUpAddJadwal(JFrame fram) {
		super(fram, true);
		this.fram = fram;
		this.con = Koneksi.getKoneksi();
		initComponents();
		init();

		// initialize
		getMapel();
		getKelas("");
		getJurusan();

		panel.requestFocusInWindow();
	}

	private void getMapel() {
		mapel.removeAllItems();
		try {
			String sql = "select id from tb_mapel where id != '001' and id != '002'";
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

	private void getKelas(String jurusan) {
		String sql = null;
		kelas.removeAllItems();
		try {
			if (jurusan != null) {
				sql = "select k.id from tb_kelas as k join tb_jurusan as j on k.id_jurusan = j.id where k.nama like '%" + jurusan + "%' and k.nama != 'NONE';";
			} else {
				sql = "select k.id from tb_kelas as k join tb_jurusan as j on k.id_jurusan = j.id where k.nama != 'NONE'";
			}
			System.out.println(sql);
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

	private void getJurusan() {
		jurusan.removeAllItems();
		try {
			String sql = "select id_jurusan from tb_jurusan where id_jurusan != 'NONE'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				jurusan.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		jurusan.setSelectedIndex(-1);
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

	private void insertAndLeave() {
		try {
			String sql = "insert into tb_jadwal values ('', '" + mapel.getSelectedItem() + "', '" + kelas.getSelectedItem() + "', 'mengajar')";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
			notify("success", "Insert Berhasil!");
			closeMessage();
		} catch (SQLException ex) {
			notify("warning", "Jadwal sudah ada");
			System.out.println("error: " + ex.getMessage());
		}
	}

	private void verifyFields() {
		if (mapel.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi ID Mapel");
		} else if (kelas.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi ID Kelas");
		} else {
			insertAndLeave();
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
                panel = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                mapel = new com.presensikeun.swing.Combobox();
                kelas = new com.presensikeun.swing.Combobox();
                button2 = new com.presensikeun.swing.Button();
                button1 = new com.presensikeun.swing.Button();
                detailMapel = new com.presensikeun.swing.Button();
                detailKelas = new com.presensikeun.swing.Button();
                jurusan = new com.presensikeun.swing.Combobox();

                timePicker1.setForeground(new java.awt.Color(85, 65, 118));

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setUndecorated(true);

                panel.setBackground(new java.awt.Color(252, 254, 255));

                jPanel2.setBackground(new java.awt.Color(85, 65, 118));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
                );

                mapel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", " " }));
                mapel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                mapel.setLabeText("ID Mapel");

                kelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", " " }));
                kelas.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                kelas.setLabeText("ID Kelas");

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

                button1.setBackground(new java.awt.Color(0, 204, 0));
                button1.setForeground(new java.awt.Color(255, 255, 255));
                button1.setText("Submit");
                button1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                button1.setPreferredSize(new java.awt.Dimension(80, 30));
                button1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                button1ActionPerformed(evt);
                        }
                });

                detailMapel.setBackground(new java.awt.Color(85, 65, 118));
                detailMapel.setForeground(new java.awt.Color(255, 255, 255));
                detailMapel.setText("D");
                detailMapel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                detailMapel.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                detailMapelActionPerformed(evt);
                        }
                });

                detailKelas.setBackground(new java.awt.Color(85, 65, 118));
                detailKelas.setForeground(new java.awt.Color(255, 255, 255));
                detailKelas.setText("D");
                detailKelas.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                detailKelas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                detailKelasActionPerformed(evt);
                        }
                });

                jurusan.setForeground(new java.awt.Color(85, 65, 118));
                jurusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", " " }));
                jurusan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                jurusan.setLabeText("Cari Jurusan");
                jurusan.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
                        public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                        }
                        public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                                jurusanPopupMenuWillBecomeInvisible(evt);
                        }
                        public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                        }
                });

                javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
                panel.setLayout(panelLayout);
                panelLayout.setHorizontalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(jurusan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(detailMapel, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                                        .addComponent(detailKelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(34, 34, 34))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(60, 60, 60))))
                );
                panelLayout.setVerticalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(detailMapel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(detailKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
		closeMessage();
		// TODO add your handling code here:
        }//GEN-LAST:event_button2ActionPerformed

        private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
		// TODO add your handling code here:
		verifyFields();
        }//GEN-LAST:event_button1ActionPerformed

        private void detailMapelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailMapelActionPerformed
		// TODO add your handling code here:
		getInfo("mapel");
        }//GEN-LAST:event_detailMapelActionPerformed

        private void detailKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailKelasActionPerformed
		// TODO add your handling code here:
		getInfo("kelas");
        }//GEN-LAST:event_detailKelasActionPerformed

        private void jurusanPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jurusanPopupMenuWillBecomeInvisible
		// TODO add your handling code here:
		getKelas((String) jurusan.getSelectedItem());
        }//GEN-LAST:event_jurusanPopupMenuWillBecomeInvisible

	public static enum MessageType {
		CANCEL, OK
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.Button button1;
        private com.presensikeun.swing.Button button2;
        private com.presensikeun.swing.Button detailKelas;
        private com.presensikeun.swing.Button detailMapel;
        private javax.swing.JPanel jPanel2;
        private com.presensikeun.swing.Combobox jurusan;
        private com.presensikeun.swing.Combobox kelas;
        private com.presensikeun.swing.Combobox mapel;
        private javax.swing.JPanel panel;
        private com.presensikeun.swing.TimePicker timePicker1;
        // End of variables declaration//GEN-END:variables
}
