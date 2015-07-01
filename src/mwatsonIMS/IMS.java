package mwatsonIMS;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class IMS {

	ArrayList<Product> allproducts = new ArrayList<Product>();
	FileWriter writer, writer2;
	
	DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
	private int id;
	private int qty;
	private String name;

	
	public IMS() throws SQLException { 
		System.out.print("Hello!" + "\n");
		GUI gui = new GUI();
	}	
	
	public void printProductList() {
		
		try {
			writer2 = new FileWriter("hello.txt");
			
		for (Product p : allproducts) {
			System.out.println(p.toString());
			writer2.write("%20s %20s %20s %20s \r\n");
		}
		writer2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void createProduct() {
		Boolean createanother = true;
		
		//do-while loop which allows the user to create as many products as they need
		do { 
			Scanner scan = new Scanner(System.in);
		try { 
		System.out.println("What is the Product ID?");
		id = scan.nextInt();
		
		System.out.println("How many are in stock?");
		qty = scan.nextInt(); 
		
		System.out.println("What is the Product Name?");
		name = scan.next();
			
		//allproducts.add(new Product(id, qty, name));
		System.out.println("Do you want to add another? y/n ");
		char response = scan.next().charAt(0);
		
			if (response == 'y'){
				createanother = true;
			}
			else {
				createanother = false;
			}
		} catch (Exception e) {
			System.err.println("Error Caught  " + e.getMessage());
		} 
		//If createanother is true the user another product can be created
		} while(createanother == true );
			
		try {
			writer = new FileWriter("output.txt");
			writer.write(String.format("%20s %20s %20s %20s \r\n", "Product ID", "Product Name","Product Qty", dateformat.format(date)));
		//Print out all products in array list and writes to txt file
		for (Product p : allproducts) {
			System.out.println(p.toString());
			writer.write(String.format("%20s %20s %20s \r\n", String.valueOf(p.getProductID()), p.getProductName(),String.valueOf(p.getProductQty())));  
			}
		writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeProductStock(){
		System.out.println("Enter Product ID ");
		
		Scanner scan = new Scanner(System.in);
		
		try { 
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
		
			}catch (Exception e) {
				System.err.println("Error Caught  " + e.getMessage());
			}
		
		for (Product p : allproducts) {
			System.out.println(p.toString());
		}
		
		System.out.println(allproducts.get(0).getProductID());
	}
	
}
