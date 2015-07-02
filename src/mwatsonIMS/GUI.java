package mwatsonIMS;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class GUI implements ActionListener {

	private JTable ProductList;
	private JButton bUpdateQty;
	private JTextField updateText;
	private JTextArea minQtyText;
	//private JButton bupdateMinQtyText;
	private JButton bPrintStockReport;
	private JButton bPrintPurchaseOrder;
	private JButton baddUpdate;
	private ProductTable model;

	private JDialog updateDialog;
	private IMSConnector IMSConnector;
	
	public GUI(){
		
		try {
			IMSConnector = new IMSConnector();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JFrame mainframe = new JFrame("Inventory Management System");
		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel sidePanel = new JPanel(new BorderLayout());
		JPanel sideUpdatePanel = new JPanel(new BorderLayout());
			
		minQtyText = new JTextArea();
		
		minQtyText.setEditable(false);
		ProductList = new JTable();
		ProductList.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ProductList.setRowHeight(20);
		ProductList.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){

            private static final long serialVersionUID = 1L;

            @Override
         public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            	
             super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
             Color lightRed = new Color(250,128,114);
             int Qty = (int) table.getModel().getValueAt(row, 2);
             int MinQty = (int) table.getModel().getValueAt(row, 3);
             if ( Qty < MinQty) {   
            	 for (int i = 0; i < 5; i++  ) { 
            	 super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, i);
                 setBackground(lightRed);    
            	 }
          
             } else {                 
                 setBackground(table.getBackground()); 
             }       
             return this;
         }   
     });
		
		
		updateText = new JTextField();
		JScrollPane jScrlP = new JScrollPane(ProductList);
		JScrollPane textScroll = new JScrollPane(minQtyText);
		
		
		arrayListupdate();
		
		topPanel.setPreferredSize(new Dimension(700, 20));
		//arraytable();
		bUpdateQty = new JButton("Update Product Quantity");
		updateDialog = new JDialog();
		updateDialog.setLayout(new FlowLayout());
		updateDialog.setLocation(550,400);
		updateDialog.setTitle("Update");
		updateDialog.add(bUpdateQty);

		bUpdateQty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				int ProductQty = 0;
				
				int row = ProductList.getSelectedRow();
				
				//row needs to be selected
				if (row < 0 ) {
					JOptionPane.showMessageDialog(null, GUI.this, "You must select a product" , JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try{ 
				String text = updateText.getText();
				System.out.println(text);
				ProductQty = Integer.parseInt(text);
				System.out.println(ProductQty);
				
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, GUI.this, "Needs to be an Integer" , JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				System.out.println("5");
				System.out.println("Quantity Entered: " + ProductQty);
				
			
				if (ProductQty <= 100){ 
					
				}else {
					JOptionPane.showMessageDialog(null, GUI.this, "Number to large. Less or equal than 100" , JOptionPane.ERROR_MESSAGE);
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
		
		baddUpdate = new JButton("Add New Stock");
		baddUpdate.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
				String name;
				int Qty;
				int MinQty;
				int MaxQty;
				
			try {
					
				name = JOptionPane.showInputDialog(null, "Enter Product Name: " , null);

				Qty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Product Qty: " , null));
				MinQty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Minimum Stock Allowed: " , null));
				MaxQty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Maximum Stock: " , null));
				
				IMSConnector.addProduct(name, Qty, MinQty, MaxQty);
				arrayListupdate();
			} catch (Exception exc) {
				return;
			}
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
			
					JOptionPane.showMessageDialog(null, "Stock report printed. ");
			}});
		
		/*bupdateMinQtyText = new JButton("Show products under minimum stock");
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
				minQtyText.append("Product too low: " + "Current Quantity: " + "Minimum Quantity: " + "\n" + "\n" );
				int row = -1;
				for (Product p : allproducts) {
					
					row += 1;
					System.out.println("i : " + row);
					System.out.println("Product:" + p.getProductName()  + ", Current Quantity: " + p.getProductQty() + ", Minimum Quantity: " + p.getProductMinQty() );
					
					
					
					if (p.getProductMinQty() > p.getProductQty()) {
						
						//System.out.println("New quantity for Product ID: " + id + " Quantity: " + qty);	
						
						
						minQtyText.append(p.getProductName() +  p.getProductQty() + p.getProductMinQty()  + "\n");
						
						} else {							
						}
				}
			}			
		});*/
		
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
				JOptionPane.showMessageDialog(null, "Product Order created. " );	
			}
		});
		
		bottomPanel.add(bPrintStockReport,BorderLayout.LINE_END);
		sideUpdatePanel.add(updateText,BorderLayout.PAGE_END);
		sideUpdatePanel.add(bUpdateQty,BorderLayout.CENTER);
		//sidePanel.add(bupdateMinQtyText, BorderLayout.PAGE_START);
		//sidePanel.add(textScroll, BorderLayout.CENTER);
		sideUpdatePanel.add(baddUpdate, BorderLayout.PAGE_START);
		sidePanel.add(bPrintPurchaseOrder, BorderLayout.PAGE_END);
		sidePanel.add(sideUpdatePanel, BorderLayout.PAGE_START);
		outerPanel.add(jScrlP, BorderLayout.CENTER);
		outerPanel.add(topPanel, BorderLayout.BEFORE_FIRST_LINE);
		outerPanel.add(bottomPanel, BorderLayout.PAGE_END);
		outerPanel.add(sidePanel, BorderLayout.LINE_END);
		
		
		mainframe.add(outerPanel);
		
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setSize(900, 600);
		mainframe.setVisible(true);
		
	}
	
	public void arrayListupdate() {
		try {
			//String ProductName = searchText.getText();
			// System.out.println("Searched For" + ProductName);
			
			ArrayList<Product> product2 = null;
			
			product2 = IMSConnector.getAllProducts();
			
			model = new ProductTable(product2);
			
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
