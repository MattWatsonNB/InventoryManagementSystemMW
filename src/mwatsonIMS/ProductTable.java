package mwatsonIMS;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ProductTable extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Initialising and declaring each column variables
	public static final int Object_Col = -1;
	private static final int ProductName_Col = 0;
	private static final int ProductID_Col = 1;
	private static final int ProductQty_Col = 2;
	private static final int ProductMinQty_Col = 3;
	private static final int ProductMaxQty_Col = 4;

	private ArrayList<Product> Products;
	private String[] columns = { "Product Name", "Product ID",
			"Product Quantity", "Minimum Quantity", "Maximum Quantity" };
	private Vector data = new Vector();

	public ProductTable(ArrayList<Product> ProductList) {
		Products = ProductList;
	}

	// Overriding the AbstractTableModel getColumnCount method so that it
	// returns the number of columns the table has
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	// Overriding the AbstractTableModel getRowCount method so that it now
	// returns the number of rows that the table will have
	// Relative to the ArrayList, Products
	@Override
	public int getRowCount() {
		return Products.size();
	}

	// Overriding the AbstractTableModel getColumnName method so that it returns
	// the names of each column
	public String getColumnName(int col) {
		return columns[col];
	}

	// Overrides the method getValueat method so that it is dependent on what
	// column is selected and will return a certain product value
	@Override
	public Object getValueAt(int row, int col) {

		Product product = Products.get(row);

		switch (col) {
		case Object_Col:
			return product;
		case ProductName_Col:
			return product.getProductName();
		case ProductID_Col:
			return product.getProductID();
		case ProductQty_Col:
			return product.getProductQty();
		case ProductMinQty_Col:
			return product.getProductMinQty();
		case ProductMaxQty_Col:
			return product.getProductMaxQty();
		default:
			return product.getProductName();
		}
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 0:
			return true;
		case 1:
			return false;
		case 2:
			return true;
		case 3:
			return true;
		case 4:
			return true;
		default:
			return false;
		}

	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		System.out.println("Set Value At");

		Product product = Products.get(row);

		switch (col) {
		case 0:
			System.out.println(value);
			product.setProductName((String) value);
			fireTableCellUpdated(row, col);
			break;
		case 2:
			System.out.println(value);
			product.setProductQty(Integer.parseInt(value.toString()));
			System.out.println("Qty - Fire");
			fireTableCellUpdated(row, col);

			break;
		case 3:
			System.out.println(value);
			product.setProductMinQty((int) value);
			fireTableCellUpdated(row, col);
			break;
		case 4:
			System.out.println(value);
			product.setProductMaxQty((int) value);
			fireTableCellUpdated(row, col);
			break;
		default:
			System.out.println(value);
			break;
		}

	}

}
