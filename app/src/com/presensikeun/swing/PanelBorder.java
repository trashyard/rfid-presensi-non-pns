package com.presensikeun.swing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelBorder extends JPanel {

	public PanelBorder() {
		setOpaque(false);
		setBackground(new Color(242, 246, 253));
	}

	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		super.paintComponent(grphcs);
	}

}
