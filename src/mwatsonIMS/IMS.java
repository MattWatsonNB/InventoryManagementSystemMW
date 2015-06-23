package mwatsonIMS;

import java.util.ArrayList;
import java.util.Scanner;

public class IMS {

	ArrayList<Product> allproducts = new ArrayList<Product>();
	Product p1 = new Product();
	
	public IMS() { 
			
		allproducts.add(new Product(0, 10, "gnome"));
		
		
		/*
		for (int i = 0; i < 2; i ++ ){
		System.out.println("These are all contained in the array " + allproducts.get(i).getProductQty());
		}
		*/
		
		for (Product p : allproducts) {
			System.out.println(p.toString());
		}
		
		inputmethod();
		
		for (Product p : allproducts) {
			System.out.println(p.toString());
		}
	}	
		
	public void inputmethod() {
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the number?");
		int Qty = scan.nextInt();
		p1.setProductID(Qty); 
		allproducts.add(new Product(p1.getProductID()));
		
		for (Product p : allproducts) {
			System.out.println(p.toString());
		}
		
		System.out.println("?");
		int Qty1 = scan.nextInt();
		p1.setProductID(Qty1); 
		allproducts.add(new Product(p1.getProductID()));
		
		
	}
	
}
