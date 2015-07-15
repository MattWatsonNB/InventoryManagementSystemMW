package mwatsonIMS;

public class Product {

	// Product Attributes and Stock Quantity
	private int ProductID;
	private int ProductQty;
	private int ProductMinQty;
	private int ProductMaxQty;
	private String ProductName;
	private float productPrice;
	private int porouswareAvailable;

	// When the product is created, each instance is passed all of these values
	// in the constructor
	public Product(int productID, int productQty, int productMinQty,
			int productMaxQty, String productName, float productPrice,
			int porouswareAvailable) {

		this.ProductID = productID;
		this.ProductQty = productQty;
		this.ProductMinQty = productMinQty;
		this.ProductMaxQty = productMaxQty;
		this.ProductName = productName;
		this.productPrice = productPrice;
		this.porouswareAvailable = porouswareAvailable;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getPorouswareAvailable() {
		return porouswareAvailable;
	}

	public void setPorouswareAvailable(int porouswareAvailable) {
		this.porouswareAvailable = porouswareAvailable;
	}

	// Retrieves Product ID
	public int getProductID() {
		return ProductID;
	}

	// Retrieves Product Quantity
	public int getProductQty() {
		return ProductQty;
	}

	// Retrieves Minimum Product Quantity
	public int getProductMinQty() {
		return ProductMinQty;
	}

	// Retrieves Max Product Quantity
	public int getProductMaxQty() {
		return ProductMaxQty;
	}

	// Retrieves Product Name
	public String getProductName() {
		return ProductName;
	}

	// Setting Product ID
	public void setProductID(int ProductID) {
		this.ProductID = ProductID;
	}

	// Setting Product Quantity
	public void setProductQty(int ProductQty) {
		System.out.println(ProductQty + " Product Class");
		this.ProductQty = ProductQty;
	}

	// Setting Minimum Quantity of the Product
	public void setProductMinQty(int ProductMinQty) {
		this.ProductMinQty = ProductMinQty;
	}

	// Setting Maximum Quantity of the Product
	public void setProductMaxQty(int ProductMaxQty) {
		this.ProductMaxQty = ProductMaxQty;
	}

	// Setting Product Name
	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	// Overrides the toString method and will convert all the product values to
	// a string
	@Override
	public String toString() {
		return "Product [ProductID=" + ProductID + ", ProductQty=" + ProductQty
				+ ", ProductName=" + ProductName + ", ProductMinQty"
				+ ProductMinQty + ", ProductPrice " + productPrice
				+ ", Porousware Available " + porouswareAvailable + "]";
	}
}
