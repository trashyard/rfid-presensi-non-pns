package com.presensikeun.form;

import com.presensikeun.model.WhatOS;
import com.presensikeun.model.WindowButton;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Auth extends javax.swing.JFrame {

	WindowButton w = new WindowButton();

	public Auth() {
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

	public void enterLog() {
		new Login().setVisible(true);
		this.setVisible(false);
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                close = new javax.swing.JLabel();
                min = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jLabel17 = new javax.swing.JLabel();
                jLabel18 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setUndecorated(true);
                addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                formKeyPressed(evt);
                        }
                });

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                jPanel1KeyPressed(evt);
                        }
                });
                jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/windows-button/icons8-close-18.png"))); // NOI18N
                close.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                closeMouseClicked(evt);
                        }
                });
                jPanel1.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 30, 40));

                min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/windows-button/icons8-minus-18.png"))); // NOI18N
                min.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                minMouseClicked(evt);
                        }
                });
                jPanel1.add(min, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, 30, 40));

                jPanel3.setBackground(new java.awt.Color(53, 33, 89));
                jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                jPanel3MouseEntered(evt);
                        }
                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                jPanel3MouseExited(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jPanel3MousePressed(evt);
                        }
                });

                jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
                jLabel17.setForeground(new java.awt.Color(255, 255, 255));
                jLabel17.setText("Login Sebagai Admin");

                jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/key.png"))); // NOI18N

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(14, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 240, 40));

                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/login/apaini.PNG"))); // NOI18N
                jLabel3.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                jLabel3KeyPressed(evt);
                        }
                });
                jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, -1, -1));

                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/login/auth new.PNG"))); // NOI18N
                jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
		jPanel3.setBackground(new Color(28, 8, 64));
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
		jPanel3.setBackground(new Color(53, 33, 89));
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
		enterLog();
    }//GEN-LAST:event_jPanel3MousePressed

        private void jLabel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel3KeyPressed
		// TODO add your handling code here:
        }//GEN-LAST:event_jLabel3KeyPressed

        private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
		// TODO add your handling code here:
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			enterLog();
		}
        }//GEN-LAST:event_jPanel1KeyPressed

        private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
		// TODO add your handling code here:
		enterLog();
        }//GEN-LAST:event_formKeyPressed

        private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
		// TODO add your handling code here:
		w.setWindow("min", this, null);
        }//GEN-LAST:event_minMouseClicked

        private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
		// TODO add your handling code here:
		w.setWindow("close", this, null);
        }//GEN-LAST:event_closeMouseClicked

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
			java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Auth.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Auth().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel close;
        private javax.swing.JLabel jLabel17;
        private javax.swing.JLabel jLabel18;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JLabel min;
        // End of variables declaration//GEN-END:variables
}
