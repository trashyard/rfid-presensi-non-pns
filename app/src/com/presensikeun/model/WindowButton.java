package com.presensikeun.model;

import com.presensikeun.main.Main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WindowButton {

	String notMax = "/com/presensikeun/images/icon/windows-button/icons8-maximized-18.png";
	String max = "/com/presensikeun/images/icon/windows-button/icons8-rhombus-18.png";

	public void setWindow(String type, JFrame parent, JLabel label) {

		switch (type) {
			case "max":
				if (parent.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
					parent.setExtendedState(JFrame.NORMAL);
				} else {
					parent.setExtendedState(parent.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				}
				break;
			case "min":
				parent.setExtendedState(parent.getExtendedState() | JFrame.ICONIFIED);
				break;
			case "close":
				System.exit(0);
				break;

			default:
				break;
		}

	}

}
