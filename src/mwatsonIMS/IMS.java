package mwatsonIMS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class IMS {

	ArrayList<Product> allproducts = new ArrayList<Product>();
	FileWriter writer = null;
	DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
	private int id;
	private int qty;
	private String name;
	Scanner scan = new Scanner(System.in);
	
	public IMS() { 
		
		createProduct();
		
	}	
	
	public void checkProductList() {
		for (Product p : allproducts) {
			System.out.println(p.toString());
		}
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
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter("output.txt");
		
		//Print out all products in array list and writes to txt file
		for (Product p : allproducts) {
			System.out.println(p.toString());
			writer.write(String.format("%20s %20s %20s %20s \r\n", String.valueOf(p.getProductID()), p.getProductName(),String.valueOf(p.getProductQty()), dateformat.format(date)));  
			}
		writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}

	public void changeProductStock(){
		
		System.out.println("Enter Product ID ");
		id = scan.nextInt();
		
		//will check the array list for this ID 
		for (Product p : allproducts) {
			if (p.getProductID() == id) {
				System.out.println("What do you want to change the stock to?");
				qty = scan.nextInt();
				p.setProductQty(qty);
				//System.out.println("New quantity for Product ID: " + id + " Quantity: " + qty);
			}
		}
			
		
		for (Product p : allproducts) {
			System.out.println(p.toString());
		}
		
		System.out.println(allproducts.get(0).getProductID());
	}
	
}
