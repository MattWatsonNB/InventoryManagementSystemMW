package mwatsonIMS;

public class Product {

	//Product Attributes and Stock Quantity 
	private int ProductID;
	private int ProductQty;
	private int ProductMinQty;
	private int ProductMaxQty;
	private String ProductName;
		
	public Product(int ID, int Qty, String Name, int MinQty, int MaxQty) {	
		ProductID = ID;
		ProductQty = Qty;
		ProductName = Name;
		ProductMinQty = MinQty;
		ProductMaxQty = MaxQty;
		
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
	
	//Retrieves Max Product Quantity
	public int getProductMaxQty() {
		return ProductMaxQty;
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
	
	public void setProductMaxQty(int ProductMaxQty) {
		this.ProductMaxQty = ProductMaxQty;
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
