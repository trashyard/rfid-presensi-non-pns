package com.presensikeun.main;

import com.presensikeun.event.EventMenu;
import com.presensikeun.form.admin.Dashboard;
import com.presensikeun.form.admin.Jadwal;
import com.presensikeun.form.admin.Karyawan;
import com.presensikeun.form.Login;
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

                panelBorder1 = new com.presensikeun.swing.PanelBorder();
                menu1 = new com.presensikeun.component.Menu();
                body = new javax.swing.JLayeredPane();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setUndecorated(true);
                addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowOpened(java.awt.event.WindowEvent evt) {
                                formWindowOpened(evt);
                        }
                });

                menu1.setBorder(null);

                body.setLayout(new java.awt.BorderLayout());

                javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
                panelBorder1.setLayout(panelBorder1Layout);
                panelBorder1Layout.setHorizontalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
                                .addContainerGap())
                );
                panelBorder1Layout.setVerticalGroup(
                        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(body)
                                .addContainerGap())
                        .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

	void logout() {
		this.setVisible(false);
		new Login().setVisible(true);
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
				case 5:
					logout();
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
        private com.presensikeun.component.Menu menu1;
        private com.presensikeun.swing.PanelBorder panelBorder1;
        // End of variables declaration//GEN-END:variables
}
