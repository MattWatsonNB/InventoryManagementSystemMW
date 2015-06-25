package mwatsonIMS;

import java.util.Scanner;

public class StockManager {

	private int id;
	private int qty;
	private String name;
	Scanner scan = new Scanner(System.in);
	
	public StockManager() {
		
	}
	
	public void createProduct() {
		Boolean createanother;
		
		//do-while loop which allows the user to create as many products as they need
		do { 
		System.out.println("What is the Product ID?");
		id = scan.nextInt();
		System.out.println("How many are in stock?");
		qty = scan.nextInt(); 
		System.out.println("What is the Product Name?");
		name = scan.next();
		allproducts.add(new Product(id, qty, name));
		System.out.println("Do you want to add another? y/n ");
		char response = scan.next().charAt(0);
		
			if (response == 'y'){
				createanother = true;
			}
			else {
				createanother = false;
			}
		//If createanother is true the user another product can be created
		} while(createanother == true );
		
		//Print out all products in array list
		for (Product p : allproducts) {
			System.out.println(p.toString());
		}
		
	}
}
