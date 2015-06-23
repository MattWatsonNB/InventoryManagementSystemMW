package mwatsonIMS;

public class Product {

	//Product Attributes and Stock Quantity 
	private int ProductID;
	private int ProductQty;
	private String ProductName;
	
	public Product() {	
		
	}
	
	public Product(int ID, int Qty, String Name) {	
		ProductID = ID;
		ProductQty = Qty;
		ProductName = Name;
	}
	
	public Product(int ID) { 
		ProductID = ID;
	}
		
	//Retrieves Product ID  
	public int getProductID(){
		return ProductID;
	}
	
	//Retrieves Product Quantity
	public int getProductQty(){
		return ProductQty;
	}
	
	//Retrieves Product Name 
	public String getProductName(){
		return ProductName;
	}
	
	//Setting Product ID 
	public void setProductID(int ProductID){
		this.ProductID = ProductID;
	}
	
	//Setting Product Quantity
	public void setProductQty(int ProductQty) {
		this.ProductQty = ProductQty;
	}
	
	//Setting Product Name
	public void setProductName(String ProductName){
		this.ProductName = ProductName;
	}

	@Override
	public String toString() {
		return "Product [ProductID=" + ProductID + ", ProductQty=" + ProductQty
				+ ", ProductName=" + ProductName + "]";
	}	
}
