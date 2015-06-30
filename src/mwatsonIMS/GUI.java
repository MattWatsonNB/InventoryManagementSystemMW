package mwatsonIMS;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class GUI implements ActionListener {

	private JTable ProductList;
	private JButton bUpdateQty;
	private JTextField updateText;
	private JTextArea minQtyText;
	private JButton bupdateMinQtyText;
	private JButton bPrintStockReport;
	private JButton bPrintPurchaseOrder; 
	
	
	private IMSConnector IMSConnector;
	
	public GUI(){
		
		try {
			IMSConnector = new IMSConnector();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel sidePanel = new JPanel(new BorderLayout());
		
		minQtyText = new JTextArea();
		minQtyText.setEditable(false);
		ProductList = new JTable();
		updateText = new JTextField();
		JScrollPane jScrlP = new JScrollPane(ProductList);
		JScrollPane textScroll = new JScrollPane(minQtyText);
		
		
		arrayListupdate();
		
		topPanel.setPreferredSize(new Dimension(700, 20));
		//arraytable();
		
		
		bUpdateQty = new JButton("Update Product Quantity");
		bUpdateQty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				
				int ProductQty = 0;
				
				int row = ProductList.getSelectedRow();
				
				//row needs to be selected
				if (row < 0 ) {
					JOptionPane.showMessageDialog(jScrlP, GUI.this, "You must select a product" , JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try{ 
				String text = updateText.getText();
				System.out.println(text);
				ProductQty = Integer.parseInt(text);
				System.out.println(ProductQty);
				
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(jScrlP, GUI.this, "Needs to be an Integer" , JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				System.out.println("5");
				System.out.println("Quantity Entered: " + ProductQty);
				
			
				if (ProductQty <= 100){ 
					
				}else {
					JOptionPane.showMessageDialog(jScrlP, GUI.this, "Number to large. Less or equal than 100" , JOptionPane.ERROR_MESSAGE);
					return;
				}
				Product product = (Product) ProductList.getValueAt(row , ProductTable.Object_Col);
				
				product.setProductQty(ProductQty);
				
				int q = product.getProductQty();
				int i = product.getProductID();
				
				IMSConnector.updateProductQty(q, i);
				System.out.println("Qty" + product.getProductQty());
				//System.out.println(product.getProductQty());
				
				arrayListupdate();
				
			}
		});
		
		
		bPrintStockReport = new JButton("Print Stock Report	");
		bPrintStockReport .addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					FileWriter writer ;
					ArrayList<Product> allproducts = new ArrayList<Product>();
					try {
						allproducts = IMSConnector.getAllProducts();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						writer = new FileWriter("output.txt");
						writer.write(String.format("%20s %20s %20s %20s \r\n", "Product ID", "Product Name","Product Qty", dateformat.format(date)));
					//Print out all products in array list and writes to txt file
					for (Product p : allproducts) {
						System.out.println(p.toString());
						writer.write(String.format("%20s %20s %20s \r\n", String.valueOf(p.getProductID()), p.getProductName(),String.valueOf(p.getProductQty())));  
						}
					writer.close();
					} catch (Exception exc) {
						exc.printStackTrace();
					}
			
			
			}});
		
		bupdateMinQtyText = new JButton("Show products under minimum stock");
		bupdateMinQtyText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ArrayList<Product> allproducts = new ArrayList<Product>();
				try {
					allproducts = IMSConnector.getAllProducts();
				} catch (Exception exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
				
				minQtyText.setText(null);
				int row = -1;
				for (Product p : allproducts) {
					
					row += 1;
					System.out.println("i : " + row);
					
					if (p.getProductMinQty() > p.getProductQty()) {
						System.out.println("Product too low: " + p.getProductName()  + ", Current Quantity: " + p.getProductQty() + ", Minimum Quantity: " + p.getProductMinQty() );
						//System.out.println("New quantity for Product ID: " + id + " Quantity: " + qty);	
						
						
						minQtyText.append("Product: " + p.getProductName() + "\n" + "Current Quantity: " + p.getProductQty() + "\n" + "Minimum Quantity: " + p.getProductMinQty() + "\n" + "\n");
						
						} else {							
						}
				}
			}			
		});
		
		bPrintPurchaseOrder = new JButton("Print Purchase Order");
		bPrintPurchaseOrder.addActionListener(new ActionListener ()  {
			public void actionPerformed(ActionEvent e) {
				
				DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				FileWriter writer ;
				ArrayList<Product> allproducts = new ArrayList<Product>();
				try {
					allproducts = IMSConnector.getAllProducts();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				try {
					writer = new FileWriter("ProductOrder.txt");
					writer.write(String.format("%20s %20s %20s %20s %20s \r\n", "Product ID", "Product Name","Product Qty", "To Buy", dateformat.format(date)));
				//Print out all products in array list and writes to txt file
				for (Product p : allproducts) {
					
					if (p.getProductMinQty() > p.getProductQty()) {	
					
					System.out.println(p.toString());
					writer.write(String.format("%20s %20s %20s %20s \r\n", String.valueOf(p.getProductID()), p.getProductName(),String.valueOf(p.getProductQty()), String.valueOf((p.getProductMaxQty() - p.getProductQty()))));
					}
					}
				writer.close();
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				
			}
		});
		
		bottomPanel.add(bUpdateQty,BorderLayout.LINE_END);
		bottomPanel.add(updateText,BorderLayout.CENTER);
		bottomPanel.add(bPrintStockReport, BorderLayout.BEFORE_LINE_BEGINS);
		sidePanel.add(bupdateMinQtyText, BorderLayout.PAGE_START);
		sidePanel.add(textScroll, BorderLayout.CENTER);
		sidePanel.add(bPrintPurchaseOrder, BorderLayout.PAGE_END);
		outerPanel.add(jScrlP, BorderLayout.CENTER);
		outerPanel.add(topPanel, BorderLayout.BEFORE_FIRST_LINE);
		outerPanel.add(bottomPanel, BorderLayout.PAGE_END);
		outerPanel.add(sidePanel, BorderLayout.LINE_END);
		
		JFrame mainframe = new JFrame("Inventory Management System");
		mainframe.add(outerPanel);
		
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setSize(900, 600);
		mainframe.setResizable(false);
		mainframe.setVisible(true);
		
	}
	
	public void arrayListupdate() {
		try {
			//String ProductName = searchText.getText();
			// System.out.println("Searched For" + ProductName);
			
			ArrayList<Product> product2 = null;
			
			product2 = IMSConnector.getAllProducts();
			
			ProductTable model = new ProductTable(product2);
			
			ProductList.setModel(model);
			
			/*for(Product p : product) {
				System.out.println(p);
			}*/
		} catch (Exception exc) {
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
