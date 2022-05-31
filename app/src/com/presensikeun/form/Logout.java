package com.presensikeun.form;

import com.presensikeun.controller.Session;
import com.presensikeun.swing.Glass;
import com.presensikeun.swing.Notification;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Logout extends javax.swing.JDialog {

	private final JFrame fram;
	private Animator animator;
	private Glass glass;
	private boolean show;
	private MessageType messageType = MessageType.CANCEL;

	public Logout(JFrame fram) {
		super(fram, true);
		this.fram = fram;
		initComponents();
		init();
	}

	public void logout() {
		Session.logout();
		JFrame loldek = (JFrame) SwingUtilities.getWindowAncestor(this);
		loldek.setVisible(false);
		Notification panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Goodbyeeeeee! :(");
		panel.showNotification();
		new Login().setVisible(true);
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
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();

                timePicker1.setForeground(new java.awt.Color(85, 65, 118));

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setUndecorated(true);

                jPanel1.setBackground(new java.awt.Color(252, 254, 255));

                jPanel2.setBackground(new java.awt.Color(85, 65, 118));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 396, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 42, Short.MAX_VALUE)
                );

                button1.setBackground(new java.awt.Color(0, 204, 0));
                button1.setForeground(new java.awt.Color(255, 255, 255));
                button1.setText("Ya");
                button1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                button1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                button1ActionPerformed(evt);
                        }
                });

                button2.setBackground(new java.awt.Color(255, 0, 51));
                button2.setForeground(new java.awt.Color(255, 255, 255));
                button2.setText("Tidak");
                button2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                button2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                button2ActionPerformed(evt);
                        }
                });

                jLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setText("<html><center>Anda akan keluar dari aplikasi ini</center>");

                jLabel2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setText("Logout?");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64))
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
		closeMessage();
		logout();
        }//GEN-LAST:event_button1ActionPerformed

        private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
		closeMessage();
		// TODO add your handling code here:
        }//GEN-LAST:event_button2ActionPerformed

	public static enum MessageType {
		CANCEL, OK
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.Button button1;
        private com.presensikeun.swing.Button button2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private com.presensikeun.swing.TimePicker timePicker1;
        // End of variables declaration//GEN-END:variables
}
