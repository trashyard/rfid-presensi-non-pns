package com.presensikeun.form.admin;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.event.EventCallBack;
import com.presensikeun.event.EventTextField;
import com.presensikeun.form.popup.PopUpAddDetailJadwal;
import com.presensikeun.form.popup.PopUpAddJadwal;
import com.presensikeun.form.popup.PopUpEditDetailJadwal;
import com.presensikeun.form.popup.PopUpEditJadwal;
import com.presensikeun.model.WhatOS;
import com.presensikeun.model.WindowButton;
import com.presensikeun.swing.Notification;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public final class Jadwal extends javax.swing.JPanel {

	WindowButton w = new WindowButton();

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	boolean mode = true;

	public Jadwal() {
		this.con = Koneksi.getKoneksi();
		initComponents();

		// initialize
		showWinButton();
		table1.scroll(jScrollPane1);
		switchTable();
		searchBar();
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

	private void reset() {
		first.setSelectedIndex(-1);
		second.setSelectedIndex(-1);
		resetSearchText();
		refreshTable();
	}

	private void getMapel() {
		first.setLabeText("Mapel");
		first.removeAllItems();
		try {
			String sql = "select id from tb_mapel";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				first.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		first.setSelectedIndex(-1);
	}

	private void getHari() {
		first.setLabeText("Hari");
		first.removeAllItems();
		first.addItem("Senin");
		first.addItem("Selasa");
		first.addItem("Rabu");
		first.addItem("Kamis");
		first.addItem("Jumat");
		first.setSelectedIndex(-1);
	}

	private int getSelectedHari(String day) {
		int hari = 0;

		switch (day) {
			case "Senin":
				hari = 0;
				break;
			case "Selasa":
				hari = 1;
				break;
			case "Rabu":
				hari = 2;
				break;
			case "Kamis":
				hari = 3;
				break;
			case "Jumat":
				hari = 4;
				break;
			default:
				break;
		}
		return hari;
	}

	private void getAngkatan() {
		second.setLabeText("Angkatan");
		second.removeAllItems();
		second.addItem("1");
		second.addItem("2");
		second.addItem("3");
		second.setSelectedIndex(-1);
	}

	private void getKelas() {
		second.setLabeText("Kelas");
		second.removeAllItems();
		try {
			String sql = "select id from tb_kelas";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				second.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		}
		second.setSelectedIndex(-1);
	}

	private void tableDetailJadwal() {
		labelTable.setText("Detail Jadwal Guru");
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Hari");
		model.addColumn("Jam Mulai");
		model.addColumn("Jam Selesai");
		model.addColumn("Nama Mapel");
		model.addColumn("Nama Guru");
		model.addColumn("Jadwal");
		try {

			String sql = "select dj.id, dj.hari as \"Date\", dj.jam as \"Jam Mulai\", ADDTIME(dj.jam, dj.durasi) as \"Jam Selesai\", m.nama as \"Nama Mapel\", k.nama as \"Nama Guru\", j.id from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id join tb_kelas as kls on kls.id = j.id_kelas join tb_ruang as r on r.id = kls.id_ruang";

			// boolean buat field ama comboboxnya
			Boolean searchJ = !searchJadwal.getText().contains("Guru");
			Boolean firstJ = first.getSelectedIndex() != -1;
			Boolean secondJ = second.getSelectedIndex() != -1;

			// filtering cuy
			if (searchJ) {
				sql = sql + " where k.nama like '%" + searchJadwal.getText() + "%'";
			}
			if (firstJ) {
				sql = sql + " && dj.hari = '" + getSelectedHari((String) first.getSelectedItem()) + "'";
			}
			if (secondJ) {
				sql = sql + " && dj.id_jadwal like '%" + second.getSelectedItem() + "%'";
			}

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), getDay(rs.getInt(2)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
			}
			table1.setModel(model);
		} catch (SQLException ex) {
			model.addRow(new Object[]{});
			table1.setModel(model);
		}
	}

	public void tableJadwal() {
		labelTable.setText("Jadwal Mata Pelajaran");
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("ID");
		model.addColumn("Kode");
		model.addColumn("Nama Mapel");
		model.addColumn("Ruangan");
		model.addColumn("Kelas");
		try {

			String sql = "select j.id, m.id, m.nama, r.nama, concat(k.id, \": Kelas \", k.angkatan, \" - \", k.nama)  from tb_jadwal as j join tb_mapel as m on j.id_mapel = m.id join tb_kelas as k on j.id_kelas = k.id join tb_ruang as r on k.id_ruang = r.id";

			// boolean buat field ama comboboxnya
			Boolean searchJ = !searchJadwal.getText().contains("Kelas");
			Boolean firstJ = first.getSelectedIndex() != -1;
			Boolean secondJ = second.getSelectedIndex() != -1;

			// filtering cuy
			if (searchJ) {
				sql = sql + " where concat(k.id, k.angkatan, k.nama) like '%" + searchJadwal.getText() + "%'";
			}
			if (firstJ) {
				sql = sql + " && m.id = '" + first.getSelectedItem() + "'";
			}
			if (secondJ) {
				sql = sql + " && k.angkatan = " + second.getSelectedItem() + "";
			}

			System.out.println(sql);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
			}
			table1.setModel(model);
		} catch (SQLException ex) {
			model.addRow(new Object[]{});
			table1.setModel(model);
		}
	}

	private void switchTable() {
		if (mode) {
			mode = false;
			reset();
			tableDetailJadwal();
			getHari();
			getKelas();
			// abis load detail jadwal set mode ke false
			// berarti mode false == state detail jadwal
			switchBtn.setText("Ke Jadwal");
		} else {
			mode = true;
			reset();
			tableJadwal();
			getMapel();
			getAngkatan();
			// sama, kek di atas intinya tapi jadwal hahaha
			switchBtn.setText("Ke Detail");
		}
	}

	private void resetSearchText() {
		if (mode) {
			searchJadwal.setText("Cari Kelas ...");
		} else {
			searchJadwal.setText("Cari Guru ...");
		}
	}

	private void refreshTable() {
		// self-explanatory ngabs
		if (mode) {
			tableJadwal();
		} else {
			tableDetailJadwal();
		}
	}

	private void whichPopUpAndRefresh(String which, String addValue) {
		switch (which) {
			case "add":
				if (mode) {
					PopUpAddJadwal p = new PopUpAddJadwal((JFrame) SwingUtilities.getWindowAncestor(this));
					p.showMessage(null);
				} else {
					PopUpAddDetailJadwal p = new PopUpAddDetailJadwal((JFrame) SwingUtilities.getWindowAncestor(this));
					p.showMessage(null);
				}
				break;
			case "edit":
				if (mode) {
					PopUpEditJadwal p = new PopUpEditJadwal((JFrame) SwingUtilities.getWindowAncestor(this), addValue);
					p.showMessage(null);
				} else {
					PopUpEditDetailJadwal p = new PopUpEditDetailJadwal((JFrame) SwingUtilities.getWindowAncestor(this), addValue);
					p.showMessage(null);
				}
				break;
			default:
				break;
		}
		refreshTable();
	}

	private void notify(String version, String msg) {
		Notification panel = null;
		if (version.equals("success")) {
			panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, msg);
		} else if (version.equals("warning")) {
			panel = new Notification((Frame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, msg);
		}
		panel.showNotification();
	}

	private String getDay(int day) {
		String string;

		switch (day) {
			case 0:
				string = "Senin";
				break;
			case 1:
				string = "Selasa";
				break;
			case 2:
				string = "Rabu";
				break;
			case 3:
				string = "Kamis";
				break;
			case 4:
				string = "Jumat";
				break;
			case 5:
				string = "Sabtu";
				break;
			case 6:
				string = "Minggu";
				break;
			default:
				string = "?";
				break;
		}

		return string;
	}

	private void searchBar() {
		searchJadwal.addEvent(new EventTextField() {
			@Override
			public void onPressed(EventCallBack call) {
				//  Test
				try {
					Thread.sleep(500);
					refreshTable();
					call.done();
				} catch (InterruptedException e) {
					System.err.println(e);
				}
			}

			@Override
			public void onCancel() {

			}

		});

	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jMenuBar1 = new javax.swing.JMenuBar();
                jMenu1 = new javax.swing.JMenu();
                jMenu2 = new javax.swing.JMenu();
                jComboBox1 = new javax.swing.JComboBox<>();
                jScrollBar1 = new javax.swing.JScrollBar();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                min = new javax.swing.JLabel();
                max = new javax.swing.JLabel();
                panelShadow1 = new com.presensikeun.swing.PanelShadow();
                searchJadwal = new com.presensikeun.swing.Searchbar();
                jLabel2 = new javax.swing.JLabel();
                first = new com.presensikeun.swing.Combobox();
                second = new com.presensikeun.swing.Combobox();
                jLabel3 = new javax.swing.JLabel();
                labelTable1 = new javax.swing.JLabel();
                panelShadow2 = new com.presensikeun.swing.PanelShadow();
                jScrollPane1 = new javax.swing.JScrollPane();
                table1 = new com.presensikeun.swing.Table();
                labelTable = new javax.swing.JLabel();
                switchBtn = new com.presensikeun.swing.Button();

                jMenu1.setText("File");
                jMenuBar1.add(jMenu1);

                jMenu2.setText("Edit");
                jMenuBar1.add(jMenu2);

                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                setBackground(new java.awt.Color(250, 250, 250));

                jPanel1.setBackground(new java.awt.Color(85, 65, 118));
                jPanel1.setMinimumSize(new java.awt.Dimension(100, 90));
                jPanel1.setPreferredSize(new java.awt.Dimension(173, 100));

                jLabel1.setBackground(new java.awt.Color(240, 236, 227));
                jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(240, 236, 227));
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icons/icons8-schedule-36.png"))); // NOI18N
                jLabel1.setText("Jadwal");
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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

                panelShadow1.setBackground(new java.awt.Color(252, 254, 255));
                panelShadow1.setPreferredSize(new java.awt.Dimension(234, 72));

                searchJadwal.setAnimationColor(new java.awt.Color(85, 65, 118));
                searchJadwal.setHintText("Cari  ...");
                searchJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                searchJadwalMouseClicked(evt);
                        }
                });
                searchJadwal.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                searchJadwalActionPerformed(evt);
                        }
                });

                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/add.png"))); // NOI18N
                jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jLabel2MousePressed(evt);
                        }
                });

                first.setLabeText("Mata Pelajaran");

                second.setLabeText("Kelas");
                second.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                secondActionPerformed(evt);
                        }
                });

                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/presensikeun/images/icon/icons8-reset-24.png"))); // NOI18N
                jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jLabel3MouseClicked(evt);
                        }
                });

                labelTable1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
                labelTable1.setForeground(new java.awt.Color(85, 65, 118));
                labelTable1.setText("Filter:");

                javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
                panelShadow1.setLayout(panelShadow1Layout);
                panelShadow1Layout.setHorizontalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTable1)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(second, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchJadwal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2)
                                .addContainerGap())
                );
                panelShadow1Layout.setVerticalGroup(
                        panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelShadow1Layout.createSequentialGroup()
                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelShadow1Layout.createSequentialGroup()
                                                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(second, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(first, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(searchJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(labelTable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                );

                panelShadow2.setBackground(new java.awt.Color(252, 254, 255));

                jScrollPane1.setBackground(new java.awt.Color(252, 254, 255));
                jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                table1.setBackground(new java.awt.Color(252, 254, 255));
                table1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                table1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null}
                        },
                        new String [] {
                                "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                ));
                table1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                table1MousePressed(evt);
                        }
                });
                jScrollPane1.setViewportView(table1);

                labelTable.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
                labelTable.setForeground(new java.awt.Color(85, 65, 118));
                labelTable.setText("Jadwal");

                switchBtn.setBackground(new java.awt.Color(85, 65, 118));
                switchBtn.setForeground(new java.awt.Color(255, 255, 255));
                switchBtn.setText("Switch!");
                switchBtn.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                switchBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                switchBtnActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
                panelShadow2.setLayout(panelShadow2Layout);
                panelShadow2Layout.setHorizontalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                                        .addGroup(panelShadow2Layout.createSequentialGroup()
                                                .addComponent(labelTable, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(switchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                );
                panelShadow2Layout.setVerticalGroup(
                        panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelTable)
                                        .addComponent(switchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents

        private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
		// TODO add your handling code here:
		whichPopUpAndRefresh("add", null);
        }//GEN-LAST:event_jLabel2MousePressed

        private void table1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MousePressed
		// TODO add your handling code here:
		try {
			int row = table1.rowAtPoint(evt.getPoint());
			String columnOne = table1.getValueAt(row, 0).toString();

			if (evt.getClickCount() == 2 && table1.getSelectedRow() != -1) {
				whichPopUpAndRefresh("edit", columnOne);
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
		}
        }//GEN-LAST:event_table1MousePressed

        private void switchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchBtnActionPerformed
		// TODO add your handling code here:
		switchTable();
        }//GEN-LAST:event_switchBtnActionPerformed

        private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
		// TODO add your handling code here:
		w.setWindow("min", (JFrame) SwingUtilities.getWindowAncestor(this), null);
        }//GEN-LAST:event_minMouseClicked

        private void maxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxMouseClicked
		// TODO add your handling code here:
		w.setWindow("max", (JFrame) SwingUtilities.getWindowAncestor(this), max);
        }//GEN-LAST:event_maxMouseClicked

        private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
		// TODO add your handling code here:
		reset();
        }//GEN-LAST:event_jLabel3MouseClicked

        private void searchJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchJadwalActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_searchJadwalActionPerformed

        private void searchJadwalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchJadwalMouseClicked
		// TODO add your handling code here:
		if (searchJadwal.getText().contains("Guru") || searchJadwal.getText().contains("Kelas")) {
			searchJadwal.setText("");
		}
        }//GEN-LAST:event_searchJadwalMouseClicked

        private void secondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_secondActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.Combobox first;
        private javax.swing.JComboBox<String> jComboBox1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JMenu jMenu1;
        private javax.swing.JMenu jMenu2;
        private javax.swing.JMenuBar jMenuBar1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollBar jScrollBar1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JLabel labelTable;
        private javax.swing.JLabel labelTable1;
        private javax.swing.JLabel max;
        private javax.swing.JLabel min;
        private com.presensikeun.swing.PanelShadow panelShadow1;
        private com.presensikeun.swing.PanelShadow panelShadow2;
        private com.presensikeun.swing.Searchbar searchJadwal;
        private com.presensikeun.swing.Combobox second;
        private com.presensikeun.swing.Button switchBtn;
        private com.presensikeun.swing.Table table1;
        // End of variables declaration//GEN-END:variables
}
