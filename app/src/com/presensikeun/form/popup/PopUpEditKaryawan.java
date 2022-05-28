package com.presensikeun.form.popup;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.swing.Glass;
import com.presensikeun.swing.Notification;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public final class PopUpEditKaryawan extends javax.swing.JDialog {

	private Animator animator;
	private Glass glass;
	private boolean show;
	private final MessageType messageType = MessageType.CANCEL;
	private final JFrame fram;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	String noNIK = null;
	String id = null;

	public PopUpEditKaryawan(JFrame fram, String nik) {
		super(fram, true);
		this.fram = fram;
		this.noNIK = nik;
		this.con = Koneksi.getKoneksi();
		initComponents();
		init();
		setNIK();
		setID();
		setNama();
		addJabatan();
		addGender();
		setSelectedJabatan();
		setSelectedGender();

		panel.requestFocusInWindow();
	}

	private void setNIK() {
		nik.setText(this.noNIK);
		nik.setEditable(false);
	}

	private void setNama() {
		try {
			String sql = "select nama from tb_karyawan where nik = " + this.noNIK;
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				nama.setText(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
	}

	private void setID() {
		try {
			String sql = "select id from tb_karyawan where nik = " + this.noNIK;
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				this.id = rs.getString(1);
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
	}

	private void addGender() {
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

	private void setSelectedGender() {
		try {
			String sql = "select jenis_kelamin from tb_karyawan where nik = " + this.noNIK;
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				convertAndSetGender(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
	}

	private void convertAndSetGender(String gender) {
		if (gender.equals("L")) {
			jk.setSelectedItem("Laki-Laki");
		} else {
			jk.setSelectedItem("Perempuan");
		}
	}

	private void getBarcode() {
		try {
			String namaFile = "/com/presensikeun/model/Barcode.jasper";
			InputStream Report;
			Report = getClass().getResourceAsStream(namaFile);
			HashMap param = new HashMap();
			param.put("nik", nik.getText());
			JasperPrint JPrint = JasperFillManager.fillReport(Report, param, con);
			JasperViewer.viewReport(JPrint, false);
		} catch (JRException ex) {
		}
	}

	private void addJabatan() {
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

	private void setSelectedJabatan() {
		try {
			String sql = "select j.nama from tb_karyawan as k join tb_jabatan as j on k.id_jabatan = j.id where nik = " + this.noNIK;
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				jabatan.setSelectedItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
	}

	private void updateAndLeave() {
		try {
			String sql = "update tb_karyawan set nik = '" + nik.getText() + "', nama = '" + nama.getText() + "', jenis_kelamin = '" + getSelectedGender() + "', id_jabatan = (select id from tb_jabatan where nama = '" + jabatan.getSelectedItem() + "') where id = " + this.id;
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
			String sql = "delete from tb_karyawan where nik = " + this.noNIK;
			pst = con.prepareStatement(sql);
			pst.executeUpdate();
			notify("success", "Delete Berhasil!");
			closeMessage();
		} catch (SQLException ex) {
			notify("warning", "Mohon hapus jadwal terlebih dahulu!");
			System.out.println("error: " + ex.getMessage());
		}
	}

	private void notify(String version, String msg) {
		Notification panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Warning euy!");
		if (version.equals("success")) {
			panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, msg);
		} else if (version.equals("warning")) {
			panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, msg);
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
			updateAndLeave();
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
                panel = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                button1 = new com.presensikeun.swing.Button();
                button2 = new com.presensikeun.swing.Button();
                nik = new com.presensikeun.swing.TextField();
                nama = new com.presensikeun.swing.TextField();
                jk = new com.presensikeun.swing.Combobox();
                jabatan = new com.presensikeun.swing.Combobox();
                delete = new com.presensikeun.swing.Button();
                see = new com.presensikeun.swing.Button();

                timePicker1.setForeground(new java.awt.Color(85, 65, 118));

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setUndecorated(true);

                panel.setBackground(new java.awt.Color(252, 254, 255));

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
                button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-edit-24.png"))); // NOI18N
                button1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                button1.setPreferredSize(new java.awt.Dimension(80, 30));
                button1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                button1ActionPerformed(evt);
                        }
                });

                button2.setBackground(new java.awt.Color(255, 51, 51));
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

                see.setBackground(new java.awt.Color(153, 102, 255));
                see.setForeground(new java.awt.Color(255, 255, 255));
                see.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-uchiha-eyes-24.png"))); // NOI18N
                see.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                see.setPreferredSize(new java.awt.Dimension(80, 30));
                see.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                seeActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
                panel.setLayout(panelLayout);
                panelLayout.setHorizontalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(see, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(nik, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(nama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(28, 28, 28))
                );
                panelLayout.setVerticalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
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
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(see, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
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

        private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
		// TODO add your handling code here:
		deleteAndLeave();
        }//GEN-LAST:event_deleteActionPerformed

        private void seeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeActionPerformed
		// TODO add your handling code here:
		getBarcode();
		closeMessage();
        }//GEN-LAST:event_seeActionPerformed

	public static enum MessageType {
		CANCEL, OK
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.Button button1;
        private com.presensikeun.swing.Button button2;
        private com.presensikeun.swing.Button delete;
        private javax.swing.JPanel jPanel2;
        private com.presensikeun.swing.Combobox jabatan;
        private com.presensikeun.swing.Combobox jk;
        private com.presensikeun.swing.TextField nama;
        private com.presensikeun.swing.TextField nik;
        private javax.swing.JPanel panel;
        private com.presensikeun.swing.Button see;
        private com.presensikeun.swing.TimePicker timePicker1;
        // End of variables declaration//GEN-END:variables
}
