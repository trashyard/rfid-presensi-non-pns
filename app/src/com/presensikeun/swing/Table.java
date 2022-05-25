package com.presensikeun.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

	public Table() {
		setShowHorizontalLines(true);
		setGridColor(new Color(242, 242, 242));
		setRowHeight(40);
		setShowVerticalLines(false);
		setFillsViewportHeight(true);
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
				TableHeader header = new TableHeader(o + "");
				if (i1 == 4) {
					header.setHorizontalAlignment(JLabel.CENTER);
				}
				return header;
			}
		});
		setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
				Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
				com.setBackground(new Color(252, 254, 255));
				setBorder(noFocusBorder);
				if (selected) {
					com.setForeground(new Color(85, 65, 118));
					com.setFont(new Font("Dialog", Font.BOLD, 12));
				} else {
					com.setForeground(new Color(102, 102, 102));
				}
				return com;
			}

		}
		);
	}

	public void addRow(Object[] row) {
		DefaultTableModel model = (DefaultTableModel) getModel();
		model.addRow(row);
	}

	public void scroll(JScrollPane jspane) {
		jspane.setVerticalScrollBar(new ScrollBar());
		jspane.getVerticalScrollBar().setBackground(new Color(252, 254, 255));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(252, 254, 255));
		jspane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
;
}
