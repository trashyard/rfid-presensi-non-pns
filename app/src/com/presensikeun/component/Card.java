package com.presensikeun.component;

import com.presensikeun.model.ModelCard;
import com.presensikeun.swing.PanelShadow;

public class Card extends PanelShadow {

	public Card() {
		initComponents();
		init();
	}

	private void init() {
		setRadius(25);
	}

	public void setData(ModelCard data) {
		lbIcon.setIcon(data.getIcon());
		lbName.setText(data.getTitle());
		lbValues.setText(data.getValues());
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                lbIcon = new javax.swing.JLabel();
                lbName = new javax.swing.JLabel();
                lbValues = new javax.swing.JLabel();

                panelShadow1.setShadowType(com.presensikeun.shadow.ShadowBorder.ShadowType.IN_SHADOW);

                lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                );

                lbName.setBackground(new java.awt.Color(0, 0, 0));
                lbName.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
                lbName.setText("Name");

                lbValues.setBackground(new java.awt.Color(0, 0, 0));
                lbValues.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
                lbValues.setText("Values");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbName)
                                        .addComponent(lbValues))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lbName)
                                .addGap(0, 0, 0)
                                .addComponent(lbValues))
                );
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel lbIcon;
        private javax.swing.JLabel lbName;
        private javax.swing.JLabel lbValues;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        // End of variables declaration//GEN-END:variables
}
