package mwatsonIMS;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**This class is used to create the GUI. This includes all buttons 
 * 
 * @author mwatson
 *
 */

public class GUI {

	private JMenuBar mainMenuBar;
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
	
	
	public GUI() {
		
		try {
			IMSConnector = new IMSConnector();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		JFrame mainframe = new JFrame("Inventory Management System");
		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel sidePanel = new JPanel(new BorderLayout());
		JPanel sideUpdatePanel = new JPanel(new BorderLayout());
		
		//Menu Bar
		mainMenuBar = new JMenuBar();
		
		//Main Menu - File
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_A);
		menuFile.getAccessibleContext().setAccessibleDescription("File");
		mainMenuBar.add(menuFile);
		
		//Group of JMenuItems
		//New Product
		JMenuItem menuItemNew = new JMenuItem("New");
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuFile.add(menuItemNew);
		menuItemNew.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
				String name;
				int Qty = 0;
				int MinQty;
				int MaxQty;
				Boolean Check = null;
			try {
					
				

				name = JOptionPane.showInputDialog(null, "Enter Product Name: " , null);
				if (name != null ) {
					
				
				Qty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Product Qty: " , null));
				MinQty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Minimum Stock Allowed: " , null));
				MaxQty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Maximum Stock: " , null));
				
				IMSConnector.addProduct(name, Qty, MinQty, MaxQty);
				arrayListupdate();
				}
				
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Input must be a number");
			
			} 
			}
		});
		
		
		//Print
		menuFile.addSeparator();
		JMenu printSubMenu = new JMenu("Print");
		printSubMenu.setMnemonic(KeyEvent.VK_P);		
		
		JMenuItem printStockReport = new JMenuItem("Stock Report");
		printStockReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		printSubMenu.add(printStockReport);
		printStockReport.addActionListener(new ActionListener() {
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
		}
			});
		
		
		JMenuItem printProductOrder = new JMenuItem("Product Order");
		printProductOrder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.SHIFT_MASK));
		printSubMenu.add(printProductOrder);
		menuFile.add(printSubMenu);
		printProductOrder.addActionListener(new ActionListener ()  {
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
		
		
		//Exit
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menuFile.add(menuItemExit );
		menuItemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedOption = JOptionPane.showConfirmDialog(null, "Do you still want to exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (selectedOption == JOptionPane.YES_OPTION) {
				System.exit(0);
				}
				else {
					return;
				}
				
			}
			
		});
		
		//MainMenu 
		JMenu menuEdit = new JMenu("Edit");
		menuEdit.setMnemonic(KeyEvent.VK_A);
		menuEdit.getAccessibleContext().setAccessibleDescription("File");
		mainMenuBar.add(menuEdit);
		
		//Group of JMenuItems
		//New Product
		JMenuItem menuItemEdit = new JMenuItem("Update");
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuEdit.add(menuItemEdit);
		
		//Delete
		JMenuItem menuItemDelete = new JMenuItem("Delete");
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuEdit.add(menuItemDelete);
		menuItemDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				int checkSelected = ProductList.getSelectedRow();
				System.out.println("Selected Row: " + checkSelected);
				//row needs to be selected
				if (checkSelected < 0 ) {
					JOptionPane.showMessageDialog(null, GUI.this, "You must select a product" , JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int selectedOption = JOptionPane.showConfirmDialog(null, "Do you want to delete this?", "Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (selectedOption == JOptionPane.YES_OPTION) {
				int[] row_index = ProductList.getSelectedRows();
				
				
				
				for (int i : row_index) {
				Product product = (Product) ProductList.getValueAt(i , ProductTable.Object_Col);
				
				int rowId = product.getProductID();
				
				System.out.println( i + " Delete" );
				IMSConnector.deleteProductQty(rowId);
				System.out.println("ID" + rowId );
				//System.out.println(product.getProductQty());
				
				}
				
				arrayListupdate();
				
			} else {
				return;
			}}
		});
		
	
		//MainMenu  - Help
		JMenu menuHelp = new JMenu("Help");
		menuHelp.setMnemonic(KeyEvent.VK_A);
		menuHelp.getAccessibleContext().setAccessibleDescription("Help");
		mainMenuBar.add(menuHelp);
		
		//Overview
		JMenuItem menuItemOverview = new JMenuItem("Overview");
		menuItemOverview.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuHelp.add(menuItemOverview);	
		
		//Key Assist
		JMenuItem menuItemKeyAssist = new JMenuItem("Key Assist");
		menuItemKeyAssist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
		menuHelp.add(menuItemKeyAssist);		
		
		//About
		JMenuItem menuItemAbout = new JMenuItem("About");
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		menuHelp.add(menuItemAbout);			
		
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
		
		
		
		bUpdateQty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				int row = ProductList.getSelectedRow();
				int col = ProductList.getSelectedColumn();
				
				//row needs to be selected
				if (row < 0 ) {
					JOptionPane.showMessageDialog(null, GUI.this, "You must select a product" , JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Product product = (Product) ProductList.getValueAt(row , ProductTable.Object_Col);
				
				String text = updateText.getText();
				System.out.println(text);
				
				
				int i = product.getProductID();
				
				switch(col) {
				case 0:
					String name = product.getProductName();
					IMSConnector.updateProductName(name, i);
					break;
				case 2: 
					int q = product.getProductQty();
					IMSConnector.updateProductQty(q, i);
					System.out.println("Qty" + product.getProductQty());
					break;
				case 3: 
					int mQ = product.getProductMinQty();
					IMSConnector.updateProductMinQty(mQ, i);
					System.out.println("Qty" + product.getProductMinQty());
					break;
				case 4:
					int maxQ = product.getProductMaxQty();
					IMSConnector.updateProductMaxQty(maxQ, i);
					System.out.println("Qty" + product.getProductMaxQty());
					break;
				
				default: 
					JOptionPane.showMessageDialog(null, GUI.this, "You must select a product" , JOptionPane.ERROR_MESSAGE);
					break;
					
				}
				
				arrayListupdate();
				
			}
		});
		
		ProductList.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = ProductList.getSelectedRow();
				int col = ProductList.getSelectedColumn();
				
				
				//ProductList.putClientProperty("terminateEditOnFocusLost", true);
				//row needs to be selected
				
				if (row < 0 ) {
					JOptionPane.showMessageDialog(null, GUI.this, "You must select a product" , JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Product product = (Product) ProductList.getValueAt(row , ProductTable.Object_Col);
				
				String text = updateText.getText();
				System.out.println(text);
				
				
				int i = product.getProductID();
				
				switch(col) {
				case 0:
					String name = product.getProductName();
					IMSConnector.updateProductName(name, i);
					break;
				case 2: 
					int q = product.getProductQty();
					IMSConnector.updateProductQty(q, i);
					System.out.println("Qty" + product.getProductQty());
					break;
				case 3: 
					int mQ = product.getProductMinQty();
					IMSConnector.updateProductMinQty(mQ, i);
					System.out.println("Qty" + product.getProductMinQty());
					break;
				case 4:
					int maxQ = product.getProductMaxQty();
					IMSConnector.updateProductMaxQty(maxQ, i);
					System.out.println("Qty" + product.getProductMaxQty());
					break;
				
				default: 
					JOptionPane.showMessageDialog(null, GUI.this, "You must select a product" , JOptionPane.ERROR_MESSAGE);
					break;
					
				}
				
				//arrayListupdate();
				
				ProductList.getSelectionModel().clearSelection();
					ProductList.getCellEditor();
				
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
			//Print Stock - Button
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
			}
				});
		
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
		
		mainframe.setJMenuBar(mainMenuBar);
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

	
}
