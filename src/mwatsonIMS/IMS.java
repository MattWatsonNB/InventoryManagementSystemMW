package mwatsonIMS;

import java.util.ArrayList;

public class IMS {

	int[] PI;
	String[] PN;
	
	
	public IMS() { 
		PI = getProductID();
		PN = getProductName();
		
		for (int i = 0; i < 4; i++){
		System.out.println(PI[i] + " " + PN[i] );
		}
		
		ArrayList<Product> allproducts = new ArrayList<Product>();
		Product p = new Product();
		p.setProductName("Hello");
		p.setProductID(2010);
		p.setProductQty(12);
		allproducts.add(p);
		System.out.println(allproducts.get(0).getProductName() + ", " + allproducts.get(0).getProductID() + " & " + allproducts.get(0).getProductQty());
		
		
	}
	
	public int[] getProductID() {
		int[] ProductID = {2,3,4,5};
		return ProductID;
	}
	
	public String[] getProductName() {
		String[] ProductName = {"Chair", "Table", "Gnome" , "Swing"};
		return ProductName;
	}
	
	
}
