package com.presensikeun.form.popup;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.swing.Glass;
import com.presensikeun.swing.Notification;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
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

public class PopUpAddKaryawan extends javax.swing.JDialog {

	private Animator animator;
	private Glass glass;
	private boolean show;
	private final MessageType messageType = MessageType.CANCEL;
	private final JFrame fram;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public PopUpAddKaryawan(JFrame fram) {
		super(fram, true);
		this.fram = fram;
		this.con = Koneksi.getKoneksi();
		initComponents();
		init();
		getJabatan();
		getGender();
	}

	private void getGender() {
		jk.removeAllItems();
		jk.addItem("Laki-Laki");
		jk.addItem("Perempuan");
		jk.setSelectedIndex(-1);
	}

	private String getSelectedGender() {
		String selected = "";
		if (jk.getSelectedItem().equals("Laki-Laki")) {
			selected = "L";
		} else {
			selected = "P";
		}
		return selected;
	}

	private void getJabatan() {
		jabatan.removeAllItems();
		try {
			String sql = "select nama from tb_jabatan";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				jabatan.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		jabatan.setSelectedIndex(-1);
	}

	private void insertAndLeave() {
		try {
			String sql = "insert into tb_karyawan values(null, '" + nik.getText() + "', null, null, '" + nama.getText() + "', 'user', '" + getSelectedGender() + "', (select id from tb_jabatan where nama = '" + jabatan.getSelectedItem() + "'))";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
			notify("success", "Insert Berhasil!");
			closeMessage();
		} catch (SQLException ex) {
			System.out.println("error: " + ex.getMessage());
		}
	}

	private void notify(String version, String msg) {
		Notification panel = null;
		if (version.equals("success")) {
			panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, msg);
		} else if (version.equals("warning")) {
			panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, msg);
		} else if (version.equals("info")) {
			panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.INFO, Notification.Location.TOP_CENTER, msg);
		}
		panel.showNotification();
	}

	private void verifyFields() {

		if (nik.getText().split("").length != 16) {
			notify("warning", "Format NIK Salah");
		} else if (nama.getText().split("").length < 3) {
			notify("warning", "Nama tidak boleh kurang dari 3 huruf");
		} else if (jk.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi jenis kelamin");
		} else if (jabatan.getSelectedIndex() == -1) {
			notify("warning", "Mohon isi jabatan");
		} else {
			insertAndLeave();
		}

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
                button1 = new com.presensikeun.swing.Button();
                button2 = new com.presensikeun.swing.Button();
                nik = new com.presensikeun.swing.TextField();
                nama = new com.presensikeun.swing.TextField();
                jk = new com.presensikeun.swing.Combobox();
                jabatan = new com.presensikeun.swing.Combobox();

                timePicker1.setForeground(new java.awt.Color(85, 65, 118));

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setUndecorated(true);

                jPanel1.setBackground(new java.awt.Color(252, 254, 255));

                jPanel2.setBackground(new java.awt.Color(85, 65, 118));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 360, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
                );

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

                nik.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                nik.setLabelText("NIK");
                nik.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                nikKeyPressed(evt);
                        }
                });

                nama.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                nama.setLabelText("Nama");
                nama.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                namaKeyPressed(evt);
                        }
                });

                jk.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                jk.setLabeText("Jenis Kelamin");

                jabatan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                jabatan.setLabeText("Jabatan");
                jabatan.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jabatanActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(43, 43, 43))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(nik, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(28, 28, 28))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
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

        private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
		// TODO add your handling code here:
//		insertAndLeave();
		verifyFields();
        }//GEN-LAST:event_button1ActionPerformed

        private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
		closeMessage();
		// TODO add your handling code here:
        }//GEN-LAST:event_button2ActionPerformed

        private void jabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jabatanActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_jabatanActionPerformed

        private void nikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nikKeyPressed
		// TODO add your handling code here:
		String nomorNIK = nik.getText();

		int panjang = nomorNIK.length();

		char c = evt.getKeyChar();
		if (Character.isLetter(c)) {
			nik.setEditable(false);
			notify("warning", "Mohon Masukkan Angka");
		} else {

			if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
//				nik ada 16 huruf trus ini mulai dari 0 jadi 15
				if (panjang <= 15) {
					nik.setEditable(true);
				} else {
					nik.setEditable(false);
				}
			} else {
				if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_DELETE) {
					nik.setEditable(true);
				} else {
					nik.setEditable(false);
				}
			}
		}
        }//GEN-LAST:event_nikKeyPressed

        private void namaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaKeyPressed
		// TODO add your handling code here:
		String namaOrang = nama.getText();

		int panjang = namaOrang.length();
		char c = evt.getKeyChar();

		if (Character.isDigit(c)) {
			nama.setEditable(false);
			notify("warning", "Mohon Masukkan Huruf");
		} else {
			if (evt.getKeyChar() >= 'A' && evt.getKeyChar() <= 'Z' || evt.getKeyChar() >= 'a' && evt.getKeyChar() <= 'z') {
//				nik ada 16 huruf trus ini mulai dari 0 jadi 15
				if (panjang <= 50) {
					nama.setEditable(true);
				} else {
					nama.setEditable(false);
				}
			} else {
				if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE || evt.getKeyCode() == KeyEvent.VK_SPACE || evt.getKeyCode() == KeyEvent.VK_SHIFT) {
					nama.setEditable(true);
				} else {
					nama.setEditable(false);
				}
			}
		}
        }//GEN-LAST:event_namaKeyPressed

	public static enum MessageType {
		CANCEL, OK
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.Button button1;
        private com.presensikeun.swing.Button button2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private com.presensikeun.swing.Combobox jabatan;
        private com.presensikeun.swing.Combobox jk;
        private com.presensikeun.swing.TextField nama;
        private com.presensikeun.swing.TextField nik;
        private com.presensikeun.swing.TimePicker timePicker1;
        // End of variables declaration//GEN-END:variables
}
