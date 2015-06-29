package mwatsonIMS;

public class Product {

	//Product Attributes and Stock Quantity 
	private int ProductID;
	private int ProductQty;
	private int ProductMinQty;
	private String ProductName;
		
	public Product(int ID, int Qty, String Name, int MinQty) {	
		ProductID = ID;
		ProductQty = Qty;
		ProductName = Name;
		ProductMinQty = MinQty;
		
	}
			
	//Retrieves Product ID  
	public int getProductID(){
		return ProductID;
	}
	
	//Retrieves Product Quantity
	public int getProductQty(){
		return ProductQty;
	}
	
	//Retrieves Minimum Product Quantity
	public int getProductMinQty() {
		return ProductMinQty;
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
	
	public void setProductMinQty(int ProductMinQty) {
		this.ProductMinQty = ProductMinQty;
	}
	
	//Setting Product Name
	public void setProductName(String ProductName){
		this.ProductName = ProductName;
	}

	@Override
	public String toString() {
		return "Product [ProductID=" + ProductID + ", ProductQty=" + ProductQty
				+ ", ProductName=" + ProductName + ", ProductMinQty" + ProductMinQty + "]";
	}	
}
