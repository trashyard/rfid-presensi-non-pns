package com.presensikeun.form;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.controller.User;
import com.presensikeun.main.Main;
import com.presensikeun.model.WhatOS;
import com.presensikeun.model.WindowButton;
import com.presensikeun.swing.Notification;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.mindrot.jbcrypt.BCrypt;

public class Login extends javax.swing.JFrame {

	WindowButton w = new WindowButton();

	Connection conn = Koneksi.getKoneksi();
	ResultSet rs = null;
	PreparedStatement pst = null;

	public Login() {
		initComponents();
		showWinButton();
	}

	private void showWinButton() {
		if (WhatOS.isWindows()) {
			min.setVisible(true);
		} else {
			// i use arch btw + wm hahahahahahahahhahahahahha
			min.setVisible(false);
		}
	}

	public void loginDek() {
		try {
			User.setUsername(txt_user.getText());

			String userPassword = User.getSome("password");
			String userType = User.getSome("type");

			boolean isValidated = BCrypt.checkpw(new String(txt_pass.getPassword()), userPassword);

			if (txt_user.getText().equals("") || txt_pass.getText().equals("")) {
				Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "User atau Password Tidak Boleh Kosong");
				panel.showNotification();
				txt_user.setText(null);
				txt_pass.setText(null);
			} else if (isValidated && userType.equals("admin")) {
				Notification panel = new Notification(this, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Selamat datang, " + User.getSome("name") + "!");
				panel.showNotification();
				this.setVisible(false);
				new Main().setVisible(true);
			} else if (isValidated && userType.equals("user")) {
				Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Mohon maaf, Hanya admin yang dapat masuk");
				panel.showNotification();
				txt_user.setText("");
				txt_pass.setText("");
			} else {
				Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Username atau Password salah");
				panel.showNotification();
				txt_user.setText("");
				txt_pass.setText("");
			}

		} catch (IllegalArgumentException | NullPointerException e) {
			Notification panel = new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Username atau Password salah");
			panel.showNotification();
		}
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jLabel8 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                txt_user = new javax.swing.JTextField();
                txt_pass = new javax.swing.JPasswordField();
                jLabel1 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel14 = new javax.swing.JLabel();
                jLabel15 = new javax.swing.JLabel();
                jLabel16 = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                jLabel12 = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                min = new javax.swing.JLabel();
                close = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setUndecorated(true);
                addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                formKeyPressed(evt);
                        }
                });
                getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-hide-20.png"))); // NOI18N
                jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jLabel8MousePressed(evt);
                        }
                });
                jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, -1, -1));

                jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-eye-20.png"))); // NOI18N
                jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jLabel9MousePressed(evt);
                        }
                });
                jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, 40));

                txt_user.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
                txt_user.setBorder(null);
                jPanel1.add(txt_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 220, 30));

                txt_pass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
                txt_pass.setBorder(null);
                txt_pass.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                txt_passKeyPressed(evt);
                        }
                });
                jPanel1.add(txt_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 265, 210, -1));

                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/login/1.PNG"))); // NOI18N
                jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

                jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(102, 102, 102));
                jLabel5.setText("Sosial Media");
                jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 580, 70, -1));

                jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-youtube-20.png"))); // NOI18N
                jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jLabel14MousePressed(evt);
                        }
                });
                jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 600, -1, -1));

                jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-facebook-20.png"))); // NOI18N
                jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jLabel15MousePressed(evt);
                        }
                });
                jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 600, -1, -1));

                jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-instagram-20.png"))); // NOI18N
                jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jLabel16MousePressed(evt);
                        }
                });
                jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 600, -1, -1));

                jLabel11.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
                jLabel11.setForeground(new java.awt.Color(102, 102, 102));
                jLabel11.setText("@2022 - SMKN 8 Jember. All Reserved.");
                jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 230, -1));

                jPanel2.setBackground(new java.awt.Color(53, 33, 89));
                jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jPanel2MousePressed(evt);
                        }
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                jPanel2MouseExited(evt);
                        }
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                jPanel2MouseEntered(evt);
                        }
                });
                jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(255, 255, 255));
                jLabel12.setText("Log In");
                jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 0, -1, 40));

                jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/login.png"))); // NOI18N
                jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 0, 43, 40));

                jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 240, 40));

                getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 660));

                min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/windows-button/icons8-minus-18.png"))); // NOI18N
                min.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                minMouseClicked(evt);
                        }
                });
                getContentPane().add(min, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 0, 30, 40));

                close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/windows-button/icons8-close-18.png"))); // NOI18N
                close.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                closeMouseClicked(evt);
                        }
                });
                getContentPane().add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 0, 30, 40));

                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/login/background login.PNG"))); // NOI18N
                getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 990, 660));

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
		txt_pass.setEchoChar('●');
		jLabel9.setVisible(true);
		jLabel8.setVisible(false);
    }//GEN-LAST:event_jLabel8MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
		txt_pass.setEchoChar((char) 0);
		jLabel9.setVisible(false);
		jLabel8.setVisible(true);
    }//GEN-LAST:event_jLabel9MousePressed

    private void jLabel15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MousePressed

		Desktop d = Desktop.getDesktop();

		try {
			d.browse(java.net.URI.create("https://www.facebook.com/OFFICIALSMKN8JEMBER/"));
		} catch (IOException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
		}

    }//GEN-LAST:event_jLabel15MousePressed

    private void jLabel16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MousePressed
		Desktop d = Desktop.getDesktop();

		try {
			d.browse(java.net.URI.create("https://www.instagram.com/smkn8_official/"));
		} catch (IOException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
		}
    }//GEN-LAST:event_jLabel16MousePressed

    private void jLabel14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MousePressed
		Desktop d = Desktop.getDesktop();

		try {
			d.browse(java.net.URI.create("https://www.youtube.com/channel/UCOiaOwoFk5farZPwhgrsr6A"));
		} catch (IOException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
		}
    }//GEN-LAST:event_jLabel14MousePressed

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
		jPanel2.setBackground(new Color(28, 8, 64));
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
		jPanel2.setBackground(new Color(53, 33, 89));
    }//GEN-LAST:event_jPanel2MouseExited

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
		loginDek();
    }//GEN-LAST:event_jPanel2MousePressed

        private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
		// TODO add your handling code here:
        }//GEN-LAST:event_formKeyPressed

        private void txt_passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passKeyPressed
		// TODO add your handling code here:
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			loginDek();
		}
        }//GEN-LAST:event_txt_passKeyPressed

        private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
		// TODO add your handling code here:
		w.setWindow("close", this, null);
        }//GEN-LAST:event_closeMouseClicked

        private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
		// TODO add your handling code here:
		w.setWindow("min", this, null);
        }//GEN-LAST:event_minMouseClicked

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
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			new Login().setVisible(true);
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel close;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel16;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JLabel min;
        private javax.swing.JPasswordField txt_pass;
        private javax.swing.JTextField txt_user;
        // End of variables declaration//GEN-END:variables
}
