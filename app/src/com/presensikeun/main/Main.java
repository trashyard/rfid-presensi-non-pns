package com.presensikeun.main;

import com.presensikeun.form.admin.Dashboard;
import com.presensikeun.form.admin.Jadwal;
import com.presensikeun.form.admin.Karyawan;
import com.presensikeun.form.Logout;
import com.presensikeun.form.admin.Presensi;
import com.presensikeun.form.admin.Report;
import java.awt.Color;
import java.awt.Component;

public final class Main extends javax.swing.JFrame {

	public Main() {
		initComponents();
		setBackground(new Color(0, 0, 0, 0));
		showForm(new Dashboard());
	}

	public void showForm(Component com) {
		body.removeAll();
		body.add(com);
		body.repaint();
		body.revalidate();
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                menu1 = new com.presensikeun.component.Menu();
                body = new javax.swing.JLayeredPane();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setUndecorated(true);
                addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowOpened(java.awt.event.WindowEvent evt) {
                                formWindowOpened(evt);
                        }
                });

                jPanel1.setBackground(new java.awt.Color(242, 246, 253));

                menu1.setBackground(new java.awt.Color(252, 254, 255));

                body.setLayout(new java.awt.BorderLayout());

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(body)
                        .addComponent(menu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

	public void logout() {
		Logout p = new Logout(this);
		p.showMessage(null);
	}

	private void goBack() {
		menu1.setSelectedIndex(0);
		showForm(new Dashboard());
	}

        private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		// TODO add your handling code here:
		menu1.setSelectedIndex(0);
		menu1.addEvent((int index) -> {
			switch (index) {
				case 0:
					showForm(new Dashboard());
					break;
				case 1:
					showForm(new Presensi());
					break;
				case 2:
					showForm(new Karyawan());
					break;
				case 3:
					showForm(new Jadwal());
					break;
				case 4:
					showForm(new Report());
					break;
				case 10:
					logout();
					goBack();
					break;
				default:
					break;
			}
		});
        }//GEN-LAST:event_formWindowOpened

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Main().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLayeredPane body;
        private javax.swing.JPanel jPanel1;
        private com.presensikeun.component.Menu menu1;
        // End of variables declaration//GEN-END:variables
}
