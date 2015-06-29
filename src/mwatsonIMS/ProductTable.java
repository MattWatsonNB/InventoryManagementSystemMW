package mwatsonIMS;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ProductTable extends AbstractTableModel {
	
	public static final int Object_Col = -1;
	private static final int ProductName_Col = 0;
	private static final int ProductID_Col = 1;
	private static final int ProductQty_Col = 2;
	private static final int ProductMinQty_Col = 3;
	
	private ArrayList<Product> Products;
	private String[] columns = {"Product Name" , "Product ID" , "Product Quantity", "Minimum Quantity"};
	
	public ProductTable(ArrayList<Product> ProductList) {
		Products = ProductList;
	}
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return Products.size();
	}
	
	public String getColumnName(int col) {
		return columns[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		Product product = Products.get(row);
		
		switch(col) {
		case Object_Col: return product;
		case ProductName_Col: return product.getProductName(); 
		case ProductID_Col: return product.getProductID();
		case ProductQty_Col: return product.getProductQty();
		case ProductMinQty_Col: return product.getProductMinQty();
		default: return product.getProductName();
		}
	}

	public Class getColumnClass (int c) {
		return getValueAt(0, c).getClass();
	}
	
}
