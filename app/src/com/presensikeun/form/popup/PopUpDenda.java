package com.presensikeun.form.popup;

import com.presensikeun.controller.Koneksi;
import com.presensikeun.swing.Glass;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public final class PopUpDenda extends javax.swing.JDialog {

	private Animator animator;
	private Glass glass;
	private boolean show;
	private final MessageType messageType = MessageType.CANCEL;
	private final JFrame fram;

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	public PopUpDenda(JFrame fram) {

		super(fram, true);
		this.fram = fram;
		this.con = Koneksi.getKoneksi();
		initComponents();
		init();
		table.scroll(jScrollPane1);

		panel.requestFocusInWindow();

		tableDenda();
	}

	private void tableDenda() {

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Nama");
		model.addColumn("Jumlah Telat");
		model.addColumn("Jumlah Denda");

		try {

			String sql = "select k.nama, count(*), SUBSTRING((count(*)/3) * 15000, 1, LENGTH((count(*)/3) * 15000)-5)  from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_jabatan as j on j.id = k.id_jabatan where month(p.tanggal) = month(current_date) && p.keterangan = \"Telat\" group by k.nik";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				model.addRow(new Object[]{rs.getString(1), rs.getString(2), convertCurrency(rs.getString(3))});
			}

			table.setModel(model);

		} catch (SQLException ex) {

			model.addRow(new Object[]{});
			table.setModel(model);

		}

	}

	private String convertCurrency(String rupiah) {
		Locale locale = new Locale("id", "ID");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return currencyFormatter.format(Integer.parseInt(rupiah));
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

	public void showMessage() {
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
                panel = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                table = new com.presensikeun.swing.Table();
                button2 = new com.presensikeun.swing.Button();
                jLabel1 = new javax.swing.JLabel();

                timePicker1.setForeground(new java.awt.Color(85, 65, 118));

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setUndecorated(true);

                panel.setBackground(new java.awt.Color(252, 254, 255));

                jPanel2.setBackground(new java.awt.Color(85, 65, 118));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
                );

                jScrollPane1.setBackground(new java.awt.Color(252, 254, 255));
                jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                table.setBackground(new java.awt.Color(252, 254, 255));
                table.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                table.setModel(new javax.swing.table.DefaultTableModel(
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
                table.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                tableMousePressed(evt);
                        }
                });
                jScrollPane1.setViewportView(table);

                button2.setBackground(new java.awt.Color(255, 0, 51));
                button2.setForeground(new java.awt.Color(255, 255, 255));
                button2.setText("Close");
                button2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
                button2.setPreferredSize(new java.awt.Dimension(80, 30));
                button2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                button2ActionPerformed(evt);
                        }
                });

                jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(85, 65, 118));
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setText("Tabel Denda Bulanan");

                javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
                panel.setLayout(panelLayout);
                panelLayout.setHorizontalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(button2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE))
                                .addGap(12, 12, 12))
                );
                panelLayout.setVerticalGroup(
                        panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
		// TODO add your handling code here:
        }//GEN-LAST:event_tableMousePressed

        private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
		closeMessage();
		// TODO add your handling code here:
        }//GEN-LAST:event_button2ActionPerformed

	public static enum MessageType {
		CANCEL, OK
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private com.presensikeun.swing.Button button2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JPanel panel;
        private com.presensikeun.swing.Table table;
        private com.presensikeun.swing.TimePicker timePicker1;
        // End of variables declaration//GEN-END:variables
}
