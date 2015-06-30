package mwatsonIMS;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TableCellRenderer extends DefaultTableCellRenderer  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableCellRenderer() {
		setOpaque(true);
	}
	
	public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setBackground(null);
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if () { 
			setBackground(Color.RED); 
		}
		
		return this;
	}
	
}
