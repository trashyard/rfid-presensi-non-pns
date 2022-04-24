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

                lbName.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
                lbName.setForeground(new java.awt.Color(102, 102, 102));
                lbName.setText("Menu Item");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbName)
                                .addGap(0, 0, 0))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                                .addGap(5, 5, 5))
                );
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel lbIcon;
        private javax.swing.JLabel lbName;
        // End of variables declaration//GEN-END:variables
}
