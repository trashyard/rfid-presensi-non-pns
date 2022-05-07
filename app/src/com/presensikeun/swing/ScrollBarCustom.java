package com.presensikeun.swing;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

	public ScrollBarCustom() {
		setUI(new ModernScrollBarUI());
		setPreferredSize(new Dimension(8, 8));
		setForeground(new Color(85, 65, 118));
		setBackground(Color.WHITE);
	}
}
