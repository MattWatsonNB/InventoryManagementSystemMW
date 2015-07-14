package mwatsonIMS;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**This class is used to create the GUI. This includes all buttons 
 * 
 * @author mwatson
 *
 */

public class GUI implements ActionListener {

	private JMenuBar mainMenuBar;
	private JTable ProductList, minTable;
	private JTextField updateText;
	private JTextArea minQtyText;
	private JButton bAdd, bDelete, bStockReport, bProductOrder;
	private ProductTable model, minModel;
	private JMenuItem menuItemNew, menuItemDelete, printStockReport, printProductOrder;
	private IMSConnector IMSConnector;
	
	
	public GUI() {
		
		try {
			IMSConnector = new IMSConnector();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FlowLayout flowTopPanel = new FlowLayout();
		flowTopPanel.setAlignment(FlowLayout.LEFT);
		JFrame mainframe = new JFrame("Inventory Management System");
		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(flowTopPanel);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel sideUpdatePanel = new JPanel(new BorderLayout());
		
		GridLayout layout = new GridLayout(2,1);
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(layout);
		
		ImageIcon addIcon = new ImageIcon("Images/plus-round.png");
		bAdd = new JButton(addIcon);
		bAdd.setBackground(Color.gray);
		bAdd = new JButton(addIcon);
		bAdd.setToolTipText("Add");
		bAdd.addActionListener(this);
		
		ImageIcon deleteIcon = new ImageIcon("Images/close-round.png");
		bDelete = new JButton(deleteIcon);
		bDelete.setBackground(Color.gray);
		bDelete.setToolTipText("Delete");
		bDelete.addActionListener(this);
		
		ImageIcon printIcon = new ImageIcon("Images/clipboard.png");
		bStockReport = new JButton(printIcon);
		bStockReport.setBackground(Color.gray);
		bStockReport.setToolTipText("Print Stock Report");
		bStockReport.addActionListener(this);
		
		ImageIcon productOrderIcon = new ImageIcon("Images/cart.png");
		bProductOrder = new JButton(productOrderIcon);
		bProductOrder.setBackground(Color.gray);
		bProductOrder.setToolTipText("Print Product Order");
		bProductOrder.addActionListener(this);
		
		topPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		//Menu Bar
		mainMenuBar = new JMenuBar();
		
		//Main Menu - File
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_A);
		menuFile.getAccessibleContext().setAccessibleDescription("File");
		mainMenuBar.add(menuFile);
		
		//Group of JMenuItems
		//New Product
		menuItemNew = new JMenuItem("New");
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuFile.add(menuItemNew);
		menuItemNew.addActionListener(this);
/*		menuItemNew.addActionListener(new ActionListener () {
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
		
	*/	
		//Print
		menuFile.addSeparator();
		JMenu printSubMenu = new JMenu("Print");
		printSubMenu.setMnemonic(KeyEvent.VK_P);		
		
		printStockReport = new JMenuItem("Stock Report");
		printStockReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		printStockReport.addActionListener(this);
		printSubMenu.add(printStockReport);
		/*
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
		*/
		
		printProductOrder = new JMenuItem("Product Order");
		printProductOrder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.SHIFT_MASK));
		printProductOrder.addActionListener(this);
		printSubMenu.add(printProductOrder);
		menuFile.add(printSubMenu);
	/*	printProductOrder.addActionListener(new ActionListener ()  {
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
		*/
		
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
		menuItemDelete = new JMenuItem("Delete");
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuEdit.add(menuItemDelete);
		menuItemDelete.addActionListener(this);
	/*	menuItemDelete.addActionListener(new ActionListener() {
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
			}
		}
		});
		*/
	
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
		minTable = new JTable();
		
		updateText = new JTextField();
		JScrollPane jScrlP = new JScrollPane(ProductList);
		JScrollPane minScroll = new JScrollPane(minTable);
		jScrlP.setBackground(Color.DARK_GRAY);
		JScrollPane textScroll = new JScrollPane(minQtyText);
		
		
		arrayListupdate();
		
		
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
		
		JButton bdbdf = new JButton("Hello");
		
		topPanel.add(bAdd);
		topPanel.add(bDelete);
		topPanel.add(bStockReport);
		topPanel.add(bProductOrder);
		topPanel.setBackground(Color.DARK_GRAY);
		sidePanel.add(minScroll, BorderLayout.EAST);
		outerPanel.add(topPanel, BorderLayout.NORTH);
		outerPanel.add(jScrlP, BorderLayout.CENTER);
		
		outerPanel.add(bottomPanel, BorderLayout.PAGE_END);
		outerPanel.add(sidePanel, BorderLayout.LINE_END);
		outerPanel.setBackground(Color.GRAY);
		
		ProductList.setGridColor(Color.BLACK);
		ProductList.setIntercellSpacing(new Dimension( 1,1));
		ProductList.setShowGrid(true);
		mainframe.add(outerPanel);
		
		mainframe.setJMenuBar(mainMenuBar);
		
		try {
			mainframe.setIconImage(ImageIO.read(new File("NBG.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.getContentPane().setBackground(Color.BLACK);
		mainframe.pack();
		mainframe.setVisible(true);
		
	}
	
	public void arrayListupdate() {
		try {
			//String ProductName = searchText.getText();
			// System.out.println("Searched For" + ProductName);
			
			ArrayList<Product> product2 = new ArrayList<Product>();
			ArrayList<Product> minProduct = new ArrayList<Product>();
			product2 = IMSConnector.getAllProducts();
			
			model = new ProductTable(product2);
			
			ProductList.setModel(model);
			System.out.println("outside loop");
			int i = 0;
			
			for (Product p : product2) {

				if (p.getProductMinQty() > p.getProductQty()) {	
				minProduct.add(i, p);
				System.out.println(i);
				System.out.println(p);
				i++;
					
				}
			
			}
			minModel = new ProductTable(minProduct);
			minTable.setModel(minModel);
			
			
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == menuItemNew || e.getSource() == bAdd) {
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
		
		
		if(e.getSource() == menuItemDelete || e.getSource() == bDelete) {
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
		}
		}
		
		if(e.getSource() == printStockReport || e.getSource() == bStockReport) {
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
		
		if(e.getSource() == bProductOrder || e.getSource() == printProductOrder) {
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
		
	}
	
}
