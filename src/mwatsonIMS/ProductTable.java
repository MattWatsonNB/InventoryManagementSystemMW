package mwatsonIMS;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ProductTable extends AbstractTableModel {
	
	
	//Initialising and declaring each column variables
	public static final int Object_Col = -1;
	private static final int ProductName_Col = 0;
	private static final int ProductID_Col = 1;
	private static final int ProductQty_Col = 2;
	private static final int ProductMinQty_Col = 3;
	private static final int ProductMaxQty_Col = 4;
	

	private ArrayList<Product> Products;
	private String[] columns = {"Product Name" , "Product ID" , "Product Quantity", "Minimum Quantity", "Maximum Quantity"};
	
	public ProductTable(ArrayList<Product> ProductList) {
		Products = ProductList;
	}
	
	//Overriding the AbstractTableModel getColumnCount method so that it returns the number of columns the table has
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	//Overriding the AbstractTableModel getRowCount method so  that it now returns the number of rows that the table will have 
	//Relative to the ArrayList, Products
	@Override
	public int getRowCount() {
		return Products.size();
	}
	
	//Overriding the AbstractTableModel getColumnName method so that it returns the names of each column
	public String getColumnName(int col) {
		return columns[col];
	}

	//Overrides the method getValueat method so that it is dependent on what column is selected and will return a certain product value
	@Override
	public Object getValueAt(int row, int col) {
		
		Product product = Products.get(row);
		
		switch(col) {
		case Object_Col: return product;
		case ProductName_Col: return product.getProductName(); 
		case ProductID_Col: return product.getProductID();
		case ProductQty_Col: return product.getProductQty();
		case ProductMinQty_Col: return product.getProductMinQty();
		case ProductMaxQty_Col: return product.getProductMaxQty();
		default: return product.getProductName();
		}
	}

	public Class getColumnClass (int c) {
		return getValueAt(0, c).getClass();
	}
	
}
