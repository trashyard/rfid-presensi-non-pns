package com.presensikeun.form.admin;

import com.presensikeun.chart.ModelChart;
import com.presensikeun.chart.ModelPolarAreaChart;
import com.presensikeun.controller.Koneksi;
import com.presensikeun.main.Main;
import com.presensikeun.model.ModelCard;
import com.presensikeun.model.WhatOS;
import com.presensikeun.model.WindowButton;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public final class Dashboard extends javax.swing.JPanel {

	WindowButton w = new WindowButton();
	JFrame f3 = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public Dashboard() {
		this.con = Koneksi.getKoneksi();
		initComponents();

		showWinButton();
		setData();
	}

	private void showWinButton() {
		if (WhatOS.isWindows()) {
			min.setVisible(true);
			max.setVisible(true);
		} else {
			// i use arch btw + wm hahahahahahahahhahahahahha
			min.setVisible(false);
			max.setVisible(false);
		}
	}

	public void setData() {
		// card
		card1.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/presensikeun/images/icon/presensi.png")), "Total Presensi", getTotalPresensi()));
		card2.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/presensikeun/images/icon/karyawan.png")), "Karyawan", getKaryawan()));
		card3.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/presensikeun/images/icon/admin.png")), "Admin", getAdmin()));
		// diagram chart 
		chart.addLegend("Minggu 1", new Color(245, 189, 135));
		chart.addLegend("Minggu 2", new Color(135, 189, 245));
		chart.addLegend("Minggu 3", new Color(189, 135, 245));
		chart.addLegend("Minggu 4", new Color(245, 135, 189));

		// ^ data
		chart.addData(new ModelChart("Jan", new double[]{getWeekly(1, 1), getWeekly(1, 2), getWeekly(1, 3), getWeekly(1, 4)}));
		chart.addData(new ModelChart("Feb", new double[]{getWeekly(2, 1), getWeekly(2, 2), getWeekly(2, 3), getWeekly(2, 4)}));
		chart.addData(new ModelChart("Mar", new double[]{getWeekly(3, 1), getWeekly(3, 2), getWeekly(3, 3), getWeekly(3, 4)}));
		chart.addData(new ModelChart("Apr", new double[]{getWeekly(4, 1), getWeekly(4, 2), getWeekly(4, 3), getWeekly(4, 4)}));
		chart.addData(new ModelChart("Mei", new double[]{getWeekly(5, 1), getWeekly(5, 2), getWeekly(5, 3), getWeekly(5, 4)}));
		chart.addData(new ModelChart("Juni", new double[]{getWeekly(6, 1), getWeekly(6, 2), getWeekly(6, 3), getWeekly(6, 4)}));
		chart.addData(new ModelChart("Juli", new double[]{getWeekly(7, 1), getWeekly(7, 2), getWeekly(7, 3), getWeekly(7, 4)}));
		chart.addData(new ModelChart("Agu", new double[]{getWeekly(8, 1), getWeekly(8, 2), getWeekly(8, 3), getWeekly(8, 4)}));
		chart.addData(new ModelChart("Sep", new double[]{getWeekly(9, 1), getWeekly(9, 2), getWeekly(9, 3), getWeekly(9, 4)}));
		chart.addData(new ModelChart("Okt", new double[]{getWeekly(10, 1), getWeekly(10, 2), getWeekly(10, 3), getWeekly(10, 4)}));
		chart.addData(new ModelChart("Nov", new double[]{getWeekly(11, 1), getWeekly(11, 2), getWeekly(11, 3), getWeekly(11, 4)}));
		chart.addData(new ModelChart("Des", new double[]{getWeekly(12, 1), getWeekly(12, 2), getWeekly(12, 3), getWeekly(12, 4)}));

		// pie chart
		// telat hadir sakit izin alpa
		polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(227, 148, 0), "Jan - Mar", getMonth("1", "3")));
		polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(92, 204, 150), "Apr - Jun", getMonth("4", "6")));
		polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(179, 161, 230), "Jul - Sept", getMonth("7", "9")));
		polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(0, 163, 204), "Okt - Des", getMonth("10", "12")));
		polarAreaChart1.start();
	}

	private String getTotalPresensi() {
		String tot = "";
		try {
			String sql = "select count(*) from tb_presensi";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				tot = rs.getString(1);
			}
		} catch (SQLException ex) {
			tot = "0";
		}
		return tot;
	}

	private String getKaryawan() {
		String tot = "";
		try {

			String sql = "select count(*) from tb_karyawan where status != 'admin'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				tot = rs.getString(1);
			}
		} catch (SQLException ex) {
			tot = "0";
		}
		return tot;
	}

	private String getAdmin() {
		String tot = "";
		try {

			String sql = "select count(*) from tb_karyawan where status = 'admin'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				tot = rs.getString(1);
			}
		} catch (SQLException ex) {
			tot = "0";
		}
		return tot;
	}

	private double getMonth(String first, String second) {
		double tot = 0;
		try {

			String sql = "select count(*) from tb_presensi where month(tanggal) between " + first + " and " + second + "";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				tot = rs.getDouble(1);
			}
		} catch (SQLException ex) {
			tot = 0;
		}
		return tot;
	}

	private double getWeekly(int month, int week) {
		double tot = 0;
		try {

			String sql = "SELECT count(*) from tb_presensi where month(tanggal) = " + month + " && FLOOR((DayOfMonth(tanggal)-1)/7)+1 = " + week + "";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				tot = rs.getDouble(1);
			}
		} catch (SQLException ex) {
			tot = 0;
		}
		return tot;
	}

//	public void get 
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                min = new javax.swing.JLabel();
                max = new javax.swing.JLabel();
                card1 = new com.presensikeun.component.Card();
                card2 = new com.presensikeun.component.Card();
                card3 = new com.presensikeun.component.Card();
                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                chart = new com.presensikeun.chart.Chart();
                jLabel3 = new javax.swing.JLabel();
                panelShadow2 = new com.presensikeun.swing.PanelShadow();
                polarAreaChart1 = new com.presensikeun.chart.PolarAreaChart();
                jLabel2 = new javax.swing.JLabel();

                setBackground(new java.awt.Color(250, 250, 250));

                jPanel1.setBackground(new java.awt.Color(85, 65, 118));
                jPanel1.setMinimumSize(new java.awt.Dimension(100, 90));
                jPanel1.setPreferredSize(new java.awt.Dimension(173, 100));

                jLabel1.setBackground(new java.awt.Color(240, 236, 227));
                jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(240, 236, 227));
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-home-36.png"))); // NOI18N
                jLabel1.setText("Dashboard");
                jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 8, Short.MAX_VALUE)
                );

                min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/windows-button/icons8-minus-18.png"))); // NOI18N
                min.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                minMouseClicked(evt);
                        }
                });

                max.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/windows-button/icons8-maximized-18.png"))); // NOI18N
                max.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                maxMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(max, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(22, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(max, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                );

                card1.setBackground(new java.awt.Color(252, 254, 255));
                card1.setForeground(new java.awt.Color(85, 65, 118));

                card2.setBackground(new java.awt.Color(252, 254, 255));
                card2.setForeground(new java.awt.Color(85, 65, 118));

                card3.setBackground(new java.awt.Color(252, 254, 255));
                card3.setForeground(new java.awt.Color(85, 65, 118));

                panelShadow1.setBackground(new java.awt.Color(252, 254, 255));

                chart.setOpaque(false);

                jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(85, 65, 118));
                jLabel3.setText("Diagram Batang");

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                        .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())
                );

                panelShadow2.setBackground(new java.awt.Color(252, 254, 255));

                polarAreaChart1.setPreferredSize(new java.awt.Dimension(250, 250));

                jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(85, 65, 118));
                jLabel2.setText("Pie Chart");

                javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
                panelShadow2.setLayout(panelShadow2Layout);
                panelShadow2Layout.setHorizontalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(polarAreaChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                panelShadow2Layout.setVerticalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(polarAreaChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                                .addContainerGap())
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(card2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );
        }// </editor-fold>//GEN-END:initComponents

        private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
		// TODO add your handling code here:
		w.setWindow("min", (JFrame) SwingUtilities.getWindowAncestor(this), null);
        }//GEN-LAST:event_minMouseClicked

        private void maxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseClicked
		// TODO add your handling code here:
		w.setWindow("max", (JFrame) SwingUtilities.getWindowAncestor(this), max);
        }//GEN-LAST:event_maxMouseClicked

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.component.Card card1;
        private com.presensikeun.component.Card card2;
        private com.presensikeun.component.Card card3;
        private com.presensikeun.chart.Chart chart;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JLabel max;
        private javax.swing.JLabel min;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        private com.presensikeun.swing.PanelShadow panelShadow2;
        private com.presensikeun.chart.PolarAreaChart polarAreaChart1;
        // End of variables declaration//GEN-END:variables
}
