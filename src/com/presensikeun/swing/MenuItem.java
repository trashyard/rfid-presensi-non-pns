package com.presensikeun.swing;

import com.presensikeun.model.ModelMenu;
import java.awt.Color;

public class MenuItem extends javax.swing.JPanel {

	private final ModelMenu data;

	public MenuItem(ModelMenu data) {
		this.data = data;
		initComponents();
		setOpaque(false);
		if (data.getType() == ModelMenu.MenuType.MENU) {
			lbIcon.setIcon(data.toIcon());
			lbName.setText(data.getName());
		} else {
			lbName.setText(" ");
		}
	}

	public void setSelected(boolean selected) {
		if (data.getType() == ModelMenu.MenuType.MENU) {
			if (selected) {
				lbIcon.setIcon(data.toIconSelected());
				lbName.setForeground(new Color(60, 60, 60));
			} else {
				lbIcon.setIcon(data.toIcon());
				lbName.setForeground(new Color(198, 203, 210));
			}
		}
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                lbIcon = new javax.swing.JLabel();
                lbName = new javax.swing.JLabel();

                lbName.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                lbName.setForeground(new java.awt.Color(102, 102, 102));
                lbName.setText("Menu Item");
                lbName.setMaximumSize(new java.awt.Dimension(84, 20));
                lbName.setMinimumSize(new java.awt.Dimension(84, 20));
                lbName.setPreferredSize(new java.awt.Dimension(84, 20));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lbIcon)
                                .addGap(18, 18, 18)
                                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                                .addGap(0, 0, 0))
                );
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel lbIcon;
        private javax.swing.JLabel lbName;
        // End of variables declaration//GEN-END:variables
}
