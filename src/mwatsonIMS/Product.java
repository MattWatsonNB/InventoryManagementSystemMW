package mwatsonIMS;

/**
 * @author Matthew Watson
 *
 */
public class Product {

	// Product Attributes and Stock Quantity
	private int ProductID;
	private int ProductQty;
	private int ProductMinQty;
	private int ProductMaxQty;
	private String ProductName;
	private float productPrice;
	private int porouswareAvailable;

	
	
	/**
	 * 
	 * Constructor which creates an instance of a product. 
	 * These values are stored in the mySQL database
	 * 
	 * @param productID Unique ID is created for each product
	 * @param productQty Stock level for the product
	 * @param productMinQty Stock needs to be ordered if under this level
	 * @param productMaxQty Restocked to this level
	 * @param productName Name assigned to product
	 * @param productPrice Price of the Product
	 * @param porouswareAvailable Shows if Porousware can be applied to the prduct
	 */
	
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

	/**
	 * 
	 * @return Specific product price
	 */
	public float getProductPrice() {
		return productPrice;
	}

	/**
	 * 
	 * @param productPrice Sets the product price
	 */
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * 
	 * @return whether porousware can be applied to the product
	 */
	public int getPorouswareAvailable() {
		return porouswareAvailable;
	}

	/**
	 * 
	 * @param porouswareAvailable
	 */
	public void setPorouswareAvailable(int porouswareAvailable) {
		this.porouswareAvailable = porouswareAvailable;
	}

	/**
	 * 
	 * @return  Retrieves Product ID
	 */
	public int getProductID() {
		return ProductID;
	}

	/**
	 * 
	 * @return Retrieves Product Quantity
	 */
	public int getProductQty() {
		return ProductQty;
	}

	/**
	 * 
	 * @return  Retrieves Minimum Product Quantity
	 */
	public int getProductMinQty() {
		return ProductMinQty;
	}

	/**
	 * 
	 * @return Retrieves Max Product Quantity
	 */
	public int getProductMaxQty() {
		return ProductMaxQty;
	}

	/**
	 * 
	 * @return Retrieves Product Name
	 */
	public String getProductName() {
		return ProductName;
	}

	/**
	 * 
	 * @param ProductID Setting Product ID
	 */
	public void setProductID(int ProductID) {
		this.ProductID = ProductID;
	}

	/**
	 * 
	 * @param ProductQty Setting Product Quantity
	 */
	public void setProductQty(int ProductQty) {
		System.out.println(ProductQty + " Product Class");
		this.ProductQty = ProductQty;
	}

	/**
	 * 
	 * @param ProductMinQty  Setting Minimum Quantity of the Product
	 */
	public void setProductMinQty(int ProductMinQty) {
		this.ProductMinQty = ProductMinQty;
	}

	/**
	 * 
	 * @param ProductMaxQty  Setting Maximum Quantity of the Product
	 */
	public void setProductMaxQty(int ProductMaxQty) {
		this.ProductMaxQty = ProductMaxQty;
	}

	/**
	 * 
	 * @param ProductName  Setting Product Name
	 */
	public void setProductName(String ProductName) {
		this.ProductName = ProductName;
	}

	
	/**
	 * Overrides the toString method and will convert all the product values to
	 *	a string
	 */
	@Override
	public String toString() {
		return "Product [ProductID=" + ProductID + ", ProductQty=" + ProductQty
				+ ", ProductName=" + ProductName + ", ProductMinQty"
				+ ProductMinQty + ", ProductPrice " + productPrice
				+ ", Porousware Available " + porouswareAvailable + "]";
	}
}
