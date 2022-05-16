package com.presensikeun.form.admin;

import com.presensikeun.chart.ModelChart;
import com.presensikeun.chart.ModelPolarAreaChart;
import com.presensikeun.controller.Koneksi;
import com.presensikeun.model.ModelCard;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public final class Dashboard extends javax.swing.JPanel {

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public Dashboard() {
		this.con = Koneksi.getKoneksi();
		initComponents();
		setData();
	}

	public void setData() {
		// card
		card1.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/presensikeun/images/icon/presensi.png")), "Total Presensi", getTotalPresensi()));
		card2.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/presensikeun/images/icon/karyawan.png")), "Karyawan", getKaryawan()));
		card3.setData(new ModelCard(new ImageIcon(getClass().getResource("/com/presensikeun/images/icon/admin.png")), "Admin", getAdmin()));
		// chart 
		chart.addLegend("Minggu 1", new Color(245, 189, 135));
		chart.addLegend("Minggu 2", new Color(135, 189, 245));
		chart.addLegend("Minggu 3", new Color(189, 135, 245));
		chart.addLegend("Minggu 4", new Color(245, 135, 189));
		chart.addData(new ModelChart("Januari", new double[]{10, 5, 7, 10}));
		chart.addData(new ModelChart("Februari", new double[]{5, 5, 4, 15}));
		chart.addData(new ModelChart("Maret", new double[]{5, 15, 4, 20}));
		chart.addData(new ModelChart("April", new double[]{30, 25, 4, 10}));
		chart.addData(new ModelChart("Mei", new double[]{5, 10, 2, 15}));
		chart.addData(new ModelChart("Juni", new double[]{1, 35, 4, 20}));

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
			String sql = "select count(*) tb_presensi";
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

//	public void get 
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                card1 = new com.presensikeun.component.Card();
                card2 = new com.presensikeun.component.Card();
                card3 = new com.presensikeun.component.Card();
                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                chart = new com.presensikeun.chart.Chart();
                panelShadow2 = new com.presensikeun.swing.PanelShadow();
                polarAreaChart1 = new com.presensikeun.chart.PolarAreaChart();

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

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 810, Short.MAX_VALUE)))
                                .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                );

                card1.setBackground(new java.awt.Color(252, 254, 255));

                card2.setBackground(new java.awt.Color(252, 254, 255));

                card3.setBackground(new java.awt.Color(252, 254, 255));

                panelShadow1.setBackground(new java.awt.Color(252, 254, 255));

                chart.setOpaque(false);

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                .addContainerGap())
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                                .addContainerGap())
                );

                panelShadow2.setBackground(new java.awt.Color(252, 254, 255));

                polarAreaChart1.setPreferredSize(new java.awt.Dimension(250, 250));

                javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
                panelShadow2.setLayout(panelShadow2Layout);
                panelShadow2Layout.setHorizontalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(polarAreaChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                panelShadow2Layout.setVerticalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(polarAreaChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.component.Card card1;
        private com.presensikeun.component.Card card2;
        private com.presensikeun.component.Card card3;
        private com.presensikeun.chart.Chart chart;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        private com.presensikeun.swing.PanelShadow panelShadow2;
        private com.presensikeun.chart.PolarAreaChart polarAreaChart1;
        // End of variables declaration//GEN-END:variables
}
