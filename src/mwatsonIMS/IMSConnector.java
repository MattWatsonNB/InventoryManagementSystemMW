package mwatsonIMS;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IMSConnector {
	
	String url = "jdbc:mysql://10.50.15.26:3306/ims" ;
	String user = "mwatson";
	String password = "netbuilder1:";
	Connection myConn = null;	

	public IMSConnector() throws Exception {
		
		try { 
			//Connect to database
			myConn = DriverManager.getConnection(url, user, password);
			
			System.out.println("Connected");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		
	}
	
	public ArrayList<Product> getAllProducts() throws Exception {
		
		Statement myStmt = null;
		ResultSet myRs  = null;
		ArrayList<Product> allproducts = new ArrayList<Product>();
				
		if (myConn != null) {
			myConn = DriverManager.getConnection(url, user, password);
		}
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from product");
			
			while(myRs.next()) {
				
				System.out.println(myRs.getString("ProductName") +  ", " + myRs.getString("ProductID") 
									+ ", " + myRs.getString("ProductQTY") + ", " + myRs.getString("MinQty") + ", " + myRs.getString("MaxQty"));
				
				int PID = myRs.getInt("ProductID");
				int PQ = myRs.getInt("ProductQTY");
				int MinQ = myRs.getInt("MinQty");
				int MaxQ = myRs.getInt("MaxQty");
				String PN = myRs.getString("ProductName");
				allproducts.add(new Product(PID, PQ, PN, MinQ, MaxQ));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try{
				if (myStmt != null){
					myConn.close();
				}
			} catch (SQLException se) {
			}
			
			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se ) {
				se.printStackTrace();
			}
			System.out.println("Goodbye");
		}
		return allproducts; 
		
		
	}
	
	public void updateProductQty(int Qty, int ID) {
		PreparedStatement myStmt = null;
		
		if (myConn != null) {
			try {
				myConn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			try {
			//Create Statement 
				
				
			String sql = "update product" 
					+ " set ProductQty = ? "
					+ " where ProductID = ?";
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
		
			myStmt.setLong(1, Qty);
			myStmt.setLong(2, ID);
			
			myStmt.executeUpdate();
			

		
			System.out.println("Update Complete");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try{
				if (myStmt != null){
					myConn.close();
				}
			} catch (SQLException se) {
			}
			
			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se ) {
				se.printStackTrace();
			}
				
			}
			//System.out.println("Goodbye");
		}
	
	public void addProduct(String Name, int Qty, int MinQty, int MaxQty) {
		PreparedStatement myStmt = null;
		
		if (myConn != null) {
			try {
				myConn = DriverManager.getConnection(url, user, password);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			try {
			//Create Statement 
			//	System.out.println("Connecting");
			myStmt = myConn.prepareStatement( "INSERT into product(ProductName, ProductQTY, MinQty, MaxQty)" + " VALUES (?, ?, ?, ?)");
		
			myStmt.setString(1, Name);
			myStmt.setInt(2, Qty);
			myStmt.setInt(3, MinQty);
			myStmt.setInt(4, MaxQty);
			
			myStmt.executeUpdate();
		
			System.out.println("Row Added");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try{
				if (myStmt != null){
					myConn.close();
				}
			} catch (SQLException se) {
			}
			
			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se ) {
				se.printStackTrace();
			}
				
			}
			//System.out.println("Goodbye");
	}
		
	public void printProductList() {
			
		ArrayList<Product> allproducts = new ArrayList<Product>();
		
			for (Product p : allproducts) {
				System.out.println(p.toString());
			}
			
		}
	
}
