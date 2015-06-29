package mwatsonIMS;

import java.sql.*;

public class DatabaseConnector  {

	String url = "jdbc:mysql://localhost:3306/ims" ;
	String user = "root";
	String password = "netbuilder1:";
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs  = null;
	
	
	public DatabaseConnector() throws SQLException { 
		
		try { 
			//Connect to database
			myConn = DriverManager.getConnection(url, user, password);
			
			System.out.println("Connected");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	/*public void searchTable() {
		
		//Reading Data
		myRs = myStmt.executeQuery("select * from product");
		
		//Prepare statement 
		myStmt = myConn.prepareStatement(" select * from product where ProductName = ? ");
		myRs = myStmt.executeQuery(password);
		
		
		//SetParameters 
		myStmt.setString(1 , "Gnome");
		
		
		display(myRs);
		
	}*/
	
	public void deleteProduct() {
		try {
			//Create Statement 
			myStmt = myConn.createStatement();
		
		//Execute SQL Query 
		
		String sq1 = "delete from product where ProductID ='2'";
		
		int rowsAffected = myStmt.executeUpdate(sq1);
		
		System.out.println("Rows affected " + rowsAffected);
		System.out.println("Delete Complete. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void updateProductName() {
		
		try {
		//Create Statement 
			myStmt = myConn.createStatement();
			
		String sq1 = "update product" 
				+ " set ProductName='Arnold Gnome' "
				+ " where ProductID = 2";
	
		myStmt.executeUpdate(sq1);
	
		System.out.println("Update Complete");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addData() {
		
		try {
			myStmt = myConn.createStatement();
		
		
		String sq1 = "insert into product"
			 	 + "(ProductName, ProductQTY)"
			 	 + " values ('JustinBeaver Gnome', '50' )";
	
		myStmt.executeUpdate(sq1);
		
		System.out.println("Insert complete");
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getAllProducts () {
		try {
		//Reading Data
		ResultSet myRs;
		
			myRs = myStmt.executeQuery("select * from product");
		
		
		
		//Read data from database
		//Process result set 
		
		while (myRs.next()) {
			System.out.println(myRs.getString("ProductName") +  ", " + myRs.getString("ProductID"));
			
		}	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}