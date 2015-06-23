package mwatsonIMS;

public class Product {

	private int ProductID;
	private int ProductQty;
	private String ProductName;
	
	public Product() {	
		
	}
		
	public int getProductID(){
		return this.ProductID;
	}
	
	public int getProductQty(){
		return this.ProductQty;
	}
	
	public String getProductName(){
		return this.ProductName;
	}
	
	public void setProductID(int ProductID){
		this.ProductID = ProductID; 
	
	}
	
	public void setProductQty(int ProductQty){
		this.ProductQty = ProductQty; 
	}
	
	public void setProductName(String ProductName){
		this.ProductName = ProductName;
	}
	
	
	
	
}
