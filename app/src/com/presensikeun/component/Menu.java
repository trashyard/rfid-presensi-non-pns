package com.presensikeun.component;

import com.presensikeun.event.EventMenu;
import com.presensikeun.event.EventMenuCallBack;
import com.presensikeun.event.EventMenuSelected;
import com.presensikeun.model.ModelMenu;
import com.presensikeun.shadow.ShadowBorder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Menu extends JPanel {

	private int selectedIndex = -1;
	private double menuTarget;
	private double menuLastTarget;
	private double currentLocation;
	private BufferedImage selectedImage;
	private Animator animator;
	private EventMenuCallBack callBack;
	private EventMenu event;

	public Menu() {
		initComponents();
		init();
	}

	private void init() {
		initData();
		listMenu.addEventSelectedMenu(new EventMenuSelected() {
			@Override
			public void menuSelected(int index, EventMenuCallBack callBack) {
				if (!animator.isRunning()) {
					if (index != selectedIndex) {
						Menu.this.callBack = callBack;
						selectedIndex = index;
						menuTarget = selectedIndex * 50 + listMenu.getY();
						animator.start();
					}
				}
			}
		});
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				currentLocation = (menuTarget - menuLastTarget) * fraction;
				currentLocation += menuLastTarget;
				repaint();
			}

			@Override
			public void end() {
				menuLastTarget = menuTarget;
				callBack.call(selectedIndex);
				if (event != null) {
					event.menuIndexChange(selectedIndex);
				}
			}
		};
		animator = new Animator(300, target);
		animator.setResolution(1);
		animator.setAcceleration(0.5f);
		animator.setDeceleration(0.5f);
	}

	public void setSelectedIndex(int index) {
		selectedIndex = index;
		menuTarget = selectedIndex * 50 + listMenu.getY();
		menuLastTarget = menuTarget;
		currentLocation = menuLastTarget;
		listMenu.selectedIndex(index);
		repaint();
	}

	private void initData() {
		listMenu.addItem(new ModelMenu("1", "Dashboard", ModelMenu.MenuType.MENU));
		listMenu.addItem(new ModelMenu("attendance", "Presensi", ModelMenu.MenuType.MENU));
		listMenu.addItem(new ModelMenu("people", "Karyawan", ModelMenu.MenuType.MENU));
		listMenu.addItem(new ModelMenu("schedule", "Jadwal", ModelMenu.MenuType.MENU));
		listMenu.addItem(new ModelMenu("report", "Report", ModelMenu.MenuType.MENU));
		listMenu.addItem(new ModelMenu("auto", "Automatic", ModelMenu.MenuType.MENU));
		listMenu.addItem(new ModelMenu("", "", ModelMenu.MenuType.EMPTY));
		listMenu.addItem(new ModelMenu("", "", ModelMenu.MenuType.EMPTY));
		listMenu.addItem(new ModelMenu("", "", ModelMenu.MenuType.EMPTY));
		listMenu.addItem(new ModelMenu("", "", ModelMenu.MenuType.EMPTY));
		listMenu.addItem(new ModelMenu("logout", "Logout", ModelMenu.MenuType.MENU));
	}

	private void createImage() {
		int width = getWidth() - 30;
		selectedImage = ShadowBorder.getInstance().createShadowOut(width, 50, 8, 8, new Color(250, 250, 250));
	}

	@Override
	public void setBounds(int i, int i1, int i2, int i3) {
		super.setBounds(i, i1, i2, i3);
		createImage();
	}

	@Override
	protected void paintComponent(Graphics grphcs) {
		super.paintComponent(grphcs);
		if (selectedIndex >= 0) {
			grphcs.drawImage(selectedImage, 15, (int) currentLocation, null);
		}
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                listMenu = new com.presensikeun.swing.ListMenu<>();
                jPanel1 = new javax.swing.JPanel();
                profile1 = new com.presensikeun.component.Profile();

                listMenu.setOpaque(false);

                jPanel1.setBackground(new java.awt.Color(53, 33, 89));
                jPanel1.setPreferredSize(new java.awt.Dimension(291, 100));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(24, Short.MAX_VALUE)
                                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(19, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(listMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(listMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents

	public void addEvent(EventMenu event) {
		this.event = event;
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel jPanel1;
        private com.presensikeun.swing.ListMenu<String> listMenu;
        private com.presensikeun.component.Profile profile1;
        // End of variables declaration//GEN-END:variables
}
