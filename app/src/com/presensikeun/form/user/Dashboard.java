package com.presensikeun.form.user;

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
		setData("1");
	}

	public Dashboard(String id) {
		this.con = Koneksi.getKoneksi();
		initComponents();
		setData(id);
	}

	public void setData(String id) {
		// initial welcome
		jLabel1.setText("Selamat Datang, " + getName(id));

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
		polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(52, 148, 203), "Hadir", getStatus(id, "hadir")));
		polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(175, 67, 237), "Sakit", getStatus(id, "sakit")));
		polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(175, 67, 237), "Izin", getStatus(id, "izin")));
		polarAreaChart1.addItem(new ModelPolarAreaChart(new Color(87, 218, 137), "Alpa", getStatus(id, "alpa")));
		polarAreaChart1.start();
	}

	public String getName(String id) {
		String nama = null;
		try {
			String sql = "select nama from tb_karyawan where id = " + id;
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				nama = rs.getString(1);
			}
		} catch (SQLException ex) {
		}
		return nama;
	}

	public int getStatus(String id, String query) {
		int status = 0;
		try {
			String sql = "select count(*) from tb_presensi as p join tb_detail_jadwal as dj on dj.id = p.id_detail_jadwal join tb_karyawan as k on k.id = dj.id_karyawan where p.keterangan = '" + query + "' and k.id = " + id;
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				status = rs.getInt(1);
			}
		} catch (SQLException ex) {
		}
		return status;
	}

//	public void get 
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                chart = new com.presensikeun.chart.Chart();
                panelShadow2 = new com.presensikeun.swing.PanelShadow();
                polarAreaChart1 = new com.presensikeun.chart.PolarAreaChart();

                setBackground(new java.awt.Color(242, 246, 253));

                jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel1.setText("Dashboard");
                jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

                chart.setOpaque(false);

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                                .addContainerGap())
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );

                javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
                panelShadow2.setLayout(panelShadow2Layout);
                panelShadow2Layout.setHorizontalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(polarAreaChart1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
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
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.chart.Chart chart;
        private javax.swing.JLabel jLabel1;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        private com.presensikeun.swing.PanelShadow panelShadow2;
        private com.presensikeun.chart.PolarAreaChart polarAreaChart1;
        // End of variables declaration//GEN-END:variables
}
