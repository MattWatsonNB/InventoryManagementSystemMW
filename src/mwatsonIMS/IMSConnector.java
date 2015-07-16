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

/**
 * @author Matthew Watson
 *
 */
public class IMSConnector {

	// Create and Declares the url, user and password needed to connect up to
	// the mySQL database
	String url = "jdbc:mysql://localhost:3306/ims";
	String user = "root";
	String password = "netbuilder1:";

	// Initial connection will be null
	Connection myConn = null;

	// Constructor -
	public IMSConnector() throws Exception {

		try {
			// Connect to database
			myConn = DriverManager.getConnection(url, user, password);

			System.out.println("Connected");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public ArrayList<Product> getAllProducts() throws Exception {
		System.out.println("Begin Get All Products");
		Statement myStmt = null;
		ResultSet myRs = null;
		ArrayList<Product> allproducts = new ArrayList<Product>();

		if (myConn != null) {
			myConn = DriverManager.getConnection(url, user, password);
		}

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from product");

			while (myRs.next()) {

				System.out.println(myRs.getString("ProductName") + ", "
						+ myRs.getString("ProductID") + ", "
						+ myRs.getString("ProductQty") + ", "
						+ myRs.getString("MinQty") + ", "
						+ myRs.getString("MaxQty") + ", "
						+ myRs.getString("Price") + ","
						+ myRs.getString("Porousware"));

				int PID = myRs.getInt("ProductID");
				int PQ = myRs.getInt("ProductQTY");
				int MinQ = myRs.getInt("MinQty");
				int MaxQ = myRs.getInt("MaxQty");
				String PN = myRs.getString("ProductName");
				float price = myRs.getFloat("Price");
				int porousWare = myRs.getInt("Porousware");
				allproducts.add(new Product(PID, PQ, MinQ, MaxQ, PN, price,
						porousWare));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (myStmt != null) {
					myConn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			System.out.println("Goodbye");
		}
		return allproducts;

	}

	public void updateProductName(String Name, int ID) {
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
			// Create Statement

			String sql = "update product" + " set ProductName = ? "
					+ " where ProductID = ?";
			myStmt = myConn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			myStmt.setString(1, Name);
			myStmt.setLong(2, ID);

			myStmt.executeUpdate();

			System.out.println("Qty Update Complete");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (myStmt != null) {
					myConn.close();
				}
			} catch (SQLException se) {
			}

			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		// System.out.println("Goodbye");
	}

	public void updateProductQty(int Qty, int ID) {

		System.out.println("Update Product Qty");
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
			// Create Statement

			String sql = "update product" + " set ProductQty = ? "
					+ " where ProductID = ?";
			myStmt = myConn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			myStmt.setLong(1, Qty);
			myStmt.setLong(2, ID);

			myStmt.executeUpdate();

			System.out.println("Qty Update Complete");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (myStmt != null) {
					myConn.close();
				}
			} catch (SQLException se) {
			}

			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		// System.out.println("Goodbye");
	}

	public void updateProductMinQty(int MinQty, int ID) {
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
			// Create Statement

			System.out.println("create statement");
			String sql = "update product" + " set MinQty = ? "
					+ " where ProductID = ?";
			myStmt = myConn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			myStmt.setLong(1, MinQty);
			myStmt.setLong(2, ID);

			myStmt.executeUpdate();

			System.out.println("Min Qty Update Complete");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (myStmt != null) {
					myConn.close();
				}
			} catch (SQLException se) {
			}

			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		// System.out.println("Goodbye");
	}

	public void updateProductMaxQty(int MaxQty, int ID) {
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
			// Create Statement

			System.out.println("create statement");
			String sql = "update product" + " set MaxQty = ? "
					+ " where ProductID = ?";
			myStmt = myConn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			myStmt.setLong(1, MaxQty);
			myStmt.setLong(2, ID);

			myStmt.executeUpdate();

			System.out.println("Max Qty Update Complete");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (myStmt != null) {
					myConn.close();
				}
			} catch (SQLException se) {
			}

			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		// System.out.println("Goodbye");
	}

	public void deleteProductQty(int ID) {

		PreparedStatement myStmt = null;

		if (myConn != null) {
			try {
				myConn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Before Create Statment Try-Catch");

		try {
			// Create Statement
			System.out.println("Create Statment");
			System.out.println(ID + "IMSConn");
			String sql = "DELETE FROM product" + " where ProductID = ?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setLong(1, ID);

			myStmt.executeUpdate();

			System.out.println("Deletion Complete");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (myStmt != null) {
					myConn.close();
				}
			} catch (SQLException se) {
			}

			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		// System.out.println("Goodbye");
	}

	/**
	 * @param Name
	 * @param Qty
	 * @param MinQty
	 * @param MaxQty
	 * @param productPrice
	 * @param porousware
	 */
	public void addProduct(String Name, int Qty, int MinQty, int MaxQty,
			float productPrice, int porousware) {
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
			// Create Statement
			// System.out.println("Connecting");
			myStmt = myConn
					.prepareStatement("INSERT into product(ProductName, ProductQTY, MinQty, MaxQty, Price, Porousware)"
							+ " VALUES (?, ?, ?, ?, ?, ?)");

			myStmt.setString(1, Name);
			myStmt.setInt(2, Qty);
			myStmt.setInt(3, MinQty);
			myStmt.setInt(4, MaxQty);
			myStmt.setFloat(5, productPrice);
			myStmt.setInt(6, porousware);

			myStmt.executeUpdate();

			System.out.println("Row Added");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (myStmt != null) {
					myConn.close();
				}
			} catch (SQLException se) {
			}

			try {
				if (myConn != null) {
					myConn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		// System.out.println("Goodbye");
	}

	public void printProductList() {

		ArrayList<Product> allproducts = new ArrayList<Product>();

		for (Product p : allproducts) {
			System.out.println(p.toString());
		}

	}

}
