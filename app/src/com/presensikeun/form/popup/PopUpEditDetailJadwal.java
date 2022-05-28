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

public class PopUpEditDetailJadwal extends javax.swing.JDialog {

	private Animator animator;
	private Glass glass;
	private boolean show;
	private final MessageType messageType = MessageType.CANCEL;
	private final JFrame fram;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	String id = null;

	public PopUpEditDetailJadwal(JFrame fram, String id) {
		super(fram, true);
		this.fram = fram;
		this.con = Koneksi.getKoneksi();
		this.id = id;
		initComponents();
		init();
		txtTime.disable();

		// initial get or fill all combobox with sql data
		getKaryawan();
		getJadwal("");
		getKelas();

		// set column with selected sql data
		setKaryawan();
		setJadwal();
		setHariJamAndDurasi();

		panel.requestFocusInWindow();
	}

	private void getKaryawan() {
		karyawan.removeAllItems();
		try {
			String sql = "select nik from tb_karyawan";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				karyawan.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		karyawan.setSelectedIndex(-1);
	}

	private void setKaryawan() {
		try {
			String sql = "select k.nik from tb_karyawan as k join tb_detail_jadwal as dj on k.id = dj.id_karyawan where dj.id = '" + id + "'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				karyawan.setSelectedItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
	}

	private void getJadwal(String kelas) {
		String sql = null;
		jadwal.removeAllItems();
		try {
			if (kelas != null) {
				sql = "select id from tb_jadwal where id_kelas like '%" + kelas + "%'";
			} else {
				sql = "select id from tb_jadwal ";
			}
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				jadwal.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		jadwal.setSelectedIndex(-1);
	}

	private void setJadwal() {
		try {
			String sql = "select j.id from tb_jadwal as j join tb_detail_jadwal as dj on j.id = dj.id_jadwal where dj.id = '" + id + "'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				jadwal.setSelectedItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
	}

	private void getKelas() {
		kelas.removeAllItems();
		try {
			String sql = "select id from tb_kelas where nama != 'NONE'";
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

	private void setHariJamAndDurasi() {
		try {
			String sql = "select hari,jam, hour(durasi) from tb_detail_jadwal where id = '" + id + "'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				hari.setSelectedItem(getSelectedHari(rs.getInt(1)));
				txtTime.setText(rs.getString(2));
				durasi.setSelectedItem(rs.getString(3));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
	}

	private void getInfo(String type) {
		String sql = null;
		try {
			switch (type) {
				case "karyawan":
					sql = "select k.nama, j.nama from tb_karyawan as k join tb_jabatan as j on k.id_jabatan = j.id where k.nik = " + karyawan.getSelectedItem();
					pst = con.prepareStatement(sql);
					rs = pst.executeQuery();
					if (rs.next()) {
						notify("info", rs.getString(1) + ": " + rs.getString(2));
					}
					break;
				case "jadwal":
					sql = "select m.nama, k.nama from tb_jadwal as j join tb_mapel as m on j.id_mapel = m.id join tb_kelas as k on j.id_kelas = k.id where j.id = '" + jadwal.getSelectedItem() + "'";
					pst = con.prepareStatement(sql);
					rs = pst.executeQuery();
					if (rs.next()) {
						notify("info", rs.getString(1) + ": " + rs.getString(2));
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
			String sql = "update tb_detail_jadwal set hari = '" + getSelectedHari((String) hari.getSelectedItem()) + "', jam = '" + txtTime.getText() + "', durasi = '" + getDurasi((String) durasi.getSelectedItem()) + "', id_karyawan = (select id from tb_karyawan where nik = '" + karyawan.getSelectedItem() + "'), id_jadwal = '" + jadwal.getSelectedItem() + "' where id = '" + id + "'";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
			notify("success", "Update Berhasil!");
			closeMessage();
		} catch (SQLException ex) {
			System.out.println("error: " + ex.getMessage());
		}
	}

	private void deleteAndLeave() {
		try {
			String sql = "delete from tb_detail_jadwal where id = '" + this.id + "'";
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
			notify("success", "Delete Berhasil!");
			closeMessage();
		} catch (SQLException ex) {
			notify("warning", "Jadwal telah masuk rekapan absen!");
			System.out.println("error: " + ex.getMessage());
		}
	}

	private void verifyFields() {
		if (karyawan.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi NIK");
		} else if (jadwal.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi ID Jadwal");
		} else if (txtTime.getText().equals("")) {
			notify("warning", "Mohon isi Jam Mulai");
		} else if (hari.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi Hari");
		} else if (durasi.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi Durasi");
		} else {
			updateAndLeave();
		}

	}

	private int getSelectedHari(String day) {
		int hari = 0;

		switch (day) {
			case "Senin":
				hari = 0;
				break;
			case "Selasa":
				hari = 1;
				break;
			case "Rabu":
				hari = 2;
				break;
			case "Kamis":
				hari = 3;
				break;
			case "Jumat":
				hari = 4;
				break;
			default:
				break;
		}
		return hari;
	}

	private String getSelectedHari(int day) {
		String hari = "";

		switch (day) {
			case 0:
				hari = "Senin";
				break;
			case 1:
				hari = "Selasa";
				break;
			case 2:
				hari = "Rabu";
				break;
			case 3:
				hari = "Kamis";
				break;
			case 4:
				hari = "Jumat";
				break;
			default:
				break;
		}
		return hari;
	}

	private String getDurasi(String durasi) {
		return "0" + durasi + ":00:00";
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
                detailKaryawan = new com.presensikeun.swing.Button();
                karyawan = new com.presensikeun.swing.Combobox();
                jadwal = new com.presensikeun.swing.Combobox();
                detailJadwal = new com.presensikeun.swing.Button();
                txtTime = new com.presensikeun.swing.TextField();
                button3 = new com.presensikeun.swing.Button();
                durasi = new com.presensikeun.swing.Combobox();
                button2 = new com.presensikeun.swing.Button();
                hari = new com.presensikeun.swing.Combobox();
                button1 = new com.presensikeun.swing.Button();
                delete = new com.presensikeun.swing.Button();
                kelas = new com.presensikeun.swing.Combobox();

                timePicker1.setForeground(new java.awt.Color(85, 65, 118));
                timePicker1.setDisplayText(txtTime);

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setUndecorated(true);

                panel.setBackground(new java.awt.Color(252, 254, 255));

                jPanel2.setBackground(new java.awt.Color(85, 65, 118));
                jPanel2.setForeground(new java.awt.Color(85, 65, 118));

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

                detailKaryawan.setBackground(new java.awt.Color(85, 65, 118));
                detailKaryawan.setForeground(new java.awt.Color(255, 255, 255));
                detailKaryawan.setText("D");
                detailKaryawan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                detailKaryawan.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                detailKaryawanActionPerformed(evt);
                        }
                });

                karyawan.setForeground(new java.awt.Color(85, 65, 118));
                karyawan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", " " }));
                karyawan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                karyawan.setLabeText("NIK Karyawan");

                jadwal.setForeground(new java.awt.Color(85, 65, 118));
                jadwal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", " " }));
                jadwal.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                jadwal.setLabeText("ID Jadwal");

                detailJadwal.setBackground(new java.awt.Color(85, 65, 118));
                detailJadwal.setForeground(new java.awt.Color(255, 255, 255));
                detailJadwal.setText("D");
                detailJadwal.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                detailJadwal.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                detailJadwalActionPerformed(evt);
                        }
                });

                txtTime.setEditable(false);
                txtTime.setBackground(new java.awt.Color(255, 255, 255));
                txtTime.setForeground(new java.awt.Color(85, 65, 118));
                txtTime.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                txtTime.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                txtTime.setLabelText("Jam Mulai");

                button3.setBackground(new java.awt.Color(85, 65, 118));
                button3.setForeground(new java.awt.Color(255, 255, 255));
                button3.setText("...");
                button3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                button3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                button3ActionPerformed(evt);
                        }
                });

                durasi.setForeground(new java.awt.Color(85, 65, 118));
                durasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
                durasi.setSelectedIndex(-1);
                durasi.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                durasi.setLabeText("Durasi Jam");

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

                hari.setForeground(new java.awt.Color(85, 65, 118));
                hari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Senin", "Selasa", "Rabu", "Kamis", "Jumat" }));
                hari.setSelectedIndex(-1);
                hari.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                hari.setLabeText("Hari");

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

                kelas.setForeground(new java.awt.Color(85, 65, 118));
                kelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", " " }));
                kelas.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                kelas.setLabeText("Cari Kelas");
                kelas.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
                        public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                        }
                        public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                                kelasPopupMenuWillBecomeInvisible(evt);
                        }
                        public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                        }
                });

                javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
                panel.setLayout(panelLayout);
                panelLayout.setHorizontalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(durasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(hari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(karyawan, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                                        .addComponent(txtTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jadwal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                                        .addComponent(detailJadwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(detailKaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(30, 30, 30))
                );
                panelLayout.setVerticalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(detailKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jadwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(detailJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addComponent(hari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(durasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(44, Short.MAX_VALUE))
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

        private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
		// TODO add your handling code here:
		timePicker1.showPopup(this, (getWidth() - timePicker1.getPreferredSize().width) / 2, (getHeight() - timePicker1.getPreferredSize().height) / 2);
        }//GEN-LAST:event_button3ActionPerformed

        private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
		closeMessage();
		// TODO add your handling code here:
        }//GEN-LAST:event_button2ActionPerformed

        private void detailKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailKaryawanActionPerformed
		// TODO add your handling code here:
		getInfo("karyawan");
        }//GEN-LAST:event_detailKaryawanActionPerformed

        private void detailJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailJadwalActionPerformed
		// TODO add your handling code here:
		getInfo("jadwal");
        }//GEN-LAST:event_detailJadwalActionPerformed

        private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
		// TODO add your handling code here:
		//		insertAndLeave();
		verifyFields();
        }//GEN-LAST:event_button1ActionPerformed

        private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
		// TODO add your handling code here:
		deleteAndLeave();
        }//GEN-LAST:event_deleteActionPerformed

        private void kelasPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_kelasPopupMenuWillBecomeInvisible
		// TODO add your handling code here:
		getJadwal((String) kelas.getSelectedItem());
        }//GEN-LAST:event_kelasPopupMenuWillBecomeInvisible

	public static enum MessageType {
		CANCEL, OK
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.Button button1;
        private com.presensikeun.swing.Button button2;
        private com.presensikeun.swing.Button button3;
        private com.presensikeun.swing.Button delete;
        private com.presensikeun.swing.Button detailJadwal;
        private com.presensikeun.swing.Button detailKaryawan;
        private com.presensikeun.swing.Combobox durasi;
        private com.presensikeun.swing.Combobox hari;
        private javax.swing.JPanel jPanel2;
        private com.presensikeun.swing.Combobox jadwal;
        private com.presensikeun.swing.Combobox karyawan;
        private com.presensikeun.swing.Combobox kelas;
        private javax.swing.JPanel panel;
        private com.presensikeun.swing.TimePicker timePicker1;
        private com.presensikeun.swing.TextField txtTime;
        // End of variables declaration//GEN-END:variables
}
