package mwatsonIMS;

import hr.ngs.templater.Configuration;
import hr.ngs.templater.ITemplateDocument;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is used to create the GUI. This includes all buttons
 * 
 * @author Matt Watson
 *
 */

public class GUI implements ActionListener {

	private JMenuBar mainMenuBar;
	private JTable ProductList, minTable;
	private JTextField updateText;
	private JTextArea minQtyText;
	private JButton bAdd, bDelete, bStockReport, bProductOrder;
	private ProductTable model, minModel;
	private JMenuItem menuItemNew, menuItemDelete, printStockReport,
			printProductOrder;
	private IMSConnector IMSConnector;
	private JLabel lProductName, lProductId, lPrice, lQty, lMinQty, lMaxQty,
			lPorousware;
	private JTextArea tProductName, tProductId, tPrice, tQty, tMinQty, tMaxQty,
			tPorousware;
	private final String templatePath = "Template\\Template.docx";
	private String programDirectory = System.getProperty("user.dir");
	private String productOrderPath = programDirectory + "\\ProductOrder\\";
	private String addIconLocation = programDirectory + "\\Images\\plus-round.png";
	private String deleteIconLocation = programDirectory + "\\Images\\close-round.png"; 
	private String stockReportLocation = programDirectory + "\\Images\\clipboard.png";
	private String productOrderLocation = programDirectory + "\\Images\\cart.png";
	private String nBGLocation = programDirectory + "\\Images\\NBG.png";
	
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

		GridLayout layout = new GridLayout(7, 2);
		JPanel sideFormPanel = new JPanel();
		sideFormPanel.setLayout(layout);

		JPanel sidePanel = new JPanel();

		lProductName = new JLabel("Product Name");
		lProductId = new JLabel("Product Id");
		lPrice = new JLabel("Price");
		lQty = new JLabel("Quantity");
		lMinQty = new JLabel("Minimum Qty");
		lMaxQty = new JLabel("Maximum Qty");
		lPorousware = new JLabel("Porousware Available");

		tProductName = new JTextArea();
		tProductId = new JTextArea();
		tPrice = new JTextArea();
		tQty = new JTextArea();
		tMinQty = new JTextArea();
		tMaxQty = new JTextArea();
		tPorousware = new JTextArea();

		ImageIcon addIcon = null;
		ImageIcon deleteIcon = null;
		ImageIcon printIcon = null;
		ImageIcon productOrderIcon = null;
		
		addIcon = new ImageIcon(addIconLocation);
		deleteIcon = new ImageIcon(deleteIconLocation);
		printIcon = new ImageIcon(stockReportLocation);
		productOrderIcon = new ImageIcon(productOrderLocation);

		bAdd = new JButton(addIcon);
		bAdd.setBackground(Color.gray);

		bAdd.setToolTipText("Add");
		bAdd.addActionListener(this);

		bDelete = new JButton(deleteIcon);
		bDelete.setBackground(Color.gray);
		bDelete.setToolTipText("Delete");
		bDelete.addActionListener(this);

		bStockReport = new JButton(printIcon);
		bStockReport.setBackground(Color.gray);
		bStockReport.setToolTipText("Print Stock Report");
		bStockReport.addActionListener(this);

		bProductOrder = new JButton(productOrderIcon);
		bProductOrder.setBackground(Color.gray);
		bProductOrder.setToolTipText("Print Product Order");
		bProductOrder.addActionListener(this);

		topPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// Menu Bar
		mainMenuBar = new JMenuBar();

		// Main Menu - File
		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_A);
		menuFile.getAccessibleContext().setAccessibleDescription("File");
		mainMenuBar.add(menuFile);

		// Group of JMenuItems
		// New Product
		menuItemNew = new JMenuItem("New");
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		menuFile.add(menuItemNew);
		menuItemNew.addActionListener(this);
		
		// Print
		menuFile.addSeparator();
		JMenu printSubMenu = new JMenu("Print");
		printSubMenu.setMnemonic(KeyEvent.VK_P);

		printStockReport = new JMenuItem("Stock Report");
		printStockReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		printStockReport.addActionListener(this);
		printSubMenu.add(printStockReport);
		

		printProductOrder = new JMenuItem("Product Order");
		printProductOrder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.SHIFT_MASK));
		printProductOrder.addActionListener(this);
		printSubMenu.add(printProductOrder);
		menuFile.add(printSubMenu);
		
		// Exit
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		menuFile.add(menuItemExit);
		menuItemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedOption = JOptionPane.showConfirmDialog(null,
						"Do you still want to exit?", "Exit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (selectedOption == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					return;
				}

			}

		});

		minQtyText = new JTextArea();
		minQtyText.setEditable(false);
		ProductList = new JTable();
		ProductList.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ProductList.setRowHeight(20);
		minTable = new JTable();
		ProductList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		minTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		updateText = new JTextField();
		JScrollPane jScrlP = new JScrollPane(ProductList);
		JScrollPane minScroll = new JScrollPane(minTable);
		jScrlP.setBackground(Color.DARK_GRAY);
		JScrollPane textScroll = new JScrollPane(minQtyText);

		arrayListupdate();

		ProductList.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println("Table Change");

				int row = ProductList.getSelectedRow();
				int col = ProductList.getSelectedColumn();

				

				if (row < 0) {
					JOptionPane.showMessageDialog(null, GUI.this,
							"You must select a product",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Product product = (Product) ProductList.getValueAt(row,
						ProductTable.Object_Col);

				String text = updateText.getText();
				System.out.println(text);

				int i = product.getProductID();

				switch (col) {
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
					JOptionPane.showMessageDialog(null, GUI.this,
							"You must select a product",
							JOptionPane.ERROR_MESSAGE);
					break;

				}

				ArrayList<Product> allproducts = new ArrayList<Product>();
				ArrayList<Product> minProduct = new ArrayList<Product>();

				try {
					allproducts = IMSConnector.getAllProducts();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				int i2 = 0;
				for (Product p : allproducts) {

					if (p.getProductMinQty() > p.getProductQty()) {
						minProduct.add(i2, p);
						System.out.println(i2);
						System.out.println(p);
						i2++;

					}

				}
				System.out.println("Finish");
				minModel = new ProductTable(minProduct);
				minTable.setModel(minModel);

			}
		});

		minTable.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				ProductList.getSelectionModel().clearSelection();
				ProductList.getCellEditor();
			}
		});

		ProductList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = ProductList.getSelectedRow();
				Product product = (Product) ProductList.getValueAt(row,
						ProductTable.Object_Col);
				String productName = product.getProductName();
				String productID = Integer.toString(product.getProductID());
				String productQty = Integer.toString(product.getProductQty());
				String minQty = Integer.toString(product.getProductMinQty());
				String maxQty = Integer.toString(product.getProductMaxQty());
				String price = Float.toString(product.getProductPrice());
				String porousWare;
				if (product.getPorouswareAvailable() == 1) {
					porousWare = "Yes";
				} else {
					porousWare = "No";
				}

				tProductName.setText(null);
				tProductName.insert(productName, 0);
				tProductId.setText(null);
				tProductId.insert(productID, 0);
				tQty.setText(null);
				tQty.setText(productQty);
				tMinQty.setText(null);
				tMinQty.setText(minQty);
				tMaxQty.setText(null);
				tMaxQty.setText(maxQty);
				tPrice.setText(null);
				tPrice.setText(price);
				tPorousware.setText(null);
				tPorousware.setText(porousWare);

			}

		});

		sideFormPanel.add(lProductName);
		sideFormPanel.add(tProductName);
		sideFormPanel.add(lProductId);
		sideFormPanel.add(tProductId);
		sideFormPanel.add(lPrice);
		sideFormPanel.add(tPrice);
		sideFormPanel.add(lQty);
		sideFormPanel.add(tQty);
		sideFormPanel.add(lMinQty);
		sideFormPanel.add(tMinQty);
		sideFormPanel.add(lMaxQty);
		sideFormPanel.add(tMaxQty);
		sideFormPanel.add(lPorousware);
		sideFormPanel.add(tPorousware);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				sideFormPanel, minScroll);

		topPanel.add(bAdd);
		
		topPanel.add(bStockReport);
		topPanel.add(bProductOrder);
		topPanel.setBackground(Color.DARK_GRAY);
		sidePanel.add(splitPane);
		sidePanel.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 3)));
		;
		outerPanel.add(topPanel, BorderLayout.NORTH);
		outerPanel.add(jScrlP, BorderLayout.CENTER);
		outerPanel.add(bottomPanel, BorderLayout.SOUTH);
		outerPanel.add(sidePanel, BorderLayout.LINE_END);
		outerPanel.setBackground(Color.GRAY);

		ProductList.setGridColor(Color.BLACK);
		ProductList.setIntercellSpacing(new Dimension(1, 1));
		ProductList.setShowGrid(true);
		mainframe.add(outerPanel);

		mainframe.setJMenuBar(mainMenuBar);

		try {
			mainframe.setIconImage(ImageIO.read(new File(nBGLocation)));
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
			// String ProductName = searchText.getText();
			// System.out.println("Searched For" + ProductName);
			System.out.println("Start Array List Update");
			ProductList.getSelectionModel().clearSelection();
			ProductList.getCellEditor();

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
			System.out.println("Finish");
			minModel = new ProductTable(minProduct);
			minTable.setModel(minModel);

			minTable.getSelectionModel().clearSelection();
			minTable.getCellEditor();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		arrayListupdate();

		if (e.getSource() == menuItemNew || e.getSource() == bAdd) {
			String name;
			int Qty = 0;
			int MinQty;
			int MaxQty;
			float Price;
			int Porousware;

			Boolean Check = null;
			try {

				name = JOptionPane.showInputDialog(null,
						"Enter Product Name: ", null);
				if (name != null) {

					Qty = Integer.parseInt(JOptionPane.showInputDialog(null,
							"Enter Product Qty: ", null));
					MinQty = Integer.parseInt(JOptionPane.showInputDialog(null,
							"Enter Minimum Stock Allowed: ", null));
					MaxQty = Integer.parseInt(JOptionPane.showInputDialog(null,
							"Enter Maximum Stock: ", null));
					Price = Float.parseFloat(JOptionPane.showInputDialog(null,
							"Enter Price: ", null));
					Porousware = Integer.parseInt(JOptionPane.showInputDialog(
							null,
							"Enter if Poroursware is available (0 or 1) : ",
							null));

					IMSConnector.addProduct(name, Qty, MinQty, MaxQty, Price,
							Porousware);
					arrayListupdate();
				}

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Input must be a number");

			}
		}

		if (e.getSource() == menuItemDelete || e.getSource() == bDelete) {
			int checkSelected = ProductList.getSelectedRow();
			System.out.println("Selected Row: " + checkSelected);
			// row needs to be selected
			if (checkSelected < 0) {
				JOptionPane.showMessageDialog(null, GUI.this,
						"You must select a product", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int selectedOption = JOptionPane.showConfirmDialog(null,
					"Do you want to delete this?", "Deletion",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (selectedOption == JOptionPane.YES_OPTION) {
				int[] row_index = ProductList.getSelectedRows();

				for (int i : row_index) {
					Product product = (Product) ProductList.getValueAt(i,
							ProductTable.Object_Col);

					int rowId = product.getProductID();

					System.out.println(i + " Delete");
					IMSConnector.deleteProductQty(rowId);
					System.out.println("ID" + rowId);
					// System.out.println(product.getProductQty());

				}

			} else {
				return;
			}
		}

		if (e.getSource() == bProductOrder
				|| e.getSource() == printProductOrder) {
			DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			FileWriter writer;
			ArrayList<Product> allproducts = new ArrayList<Product>();

			int selectedOption = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to print?", "Stock Report",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (selectedOption == JOptionPane.YES_OPTION) {
				try {
					allproducts = IMSConnector.getAllProducts();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					writer = new FileWriter("output.txt");
					writer.write(String.format("%20s %20s %20s %20s \r\n",
							"Product ID", "Product Name", "Product Qty",
							dateformat.format(date)));
					// Print out all products in array list and writes to txt
					// file
					for (Product p : allproducts) {
						System.out.println(p.toString());
						writer.write(String.format("%20s %20s %20s \r\n",
								String.valueOf(p.getProductID()),
								p.getProductName(),
								String.valueOf(p.getProductQty())));
					}
					writer.close();
				} catch (Exception exc) {
					exc.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Product order printed. ");
			} else {
				return;
			}

		}

		if (e.getSource() == printStockReport || e.getSource() == bStockReport) {
			
			FileWriter writer;
			ArrayList<Product> allproducts = new ArrayList<Product>();
			try {
				allproducts = IMSConnector.getAllProducts();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
								
				String Title = "Stock Report";
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				final InputStream inputTemplateStream = new FileInputStream(templatePath);
				ITemplateDocument doc = Configuration.factory().open(inputTemplateStream, "docx", baos);
				
				doc.templater().replace("DATE", dateTime());
				doc.templater().replace("myArr", setTemplateArray(allproducts));
				doc.templater().replace("Title", Title);
				
				doc.flush();
				
				byte[] results = baos.toByteArray();
				String date = dateTime();
				String dateTimeString = date.replace('/', '-');
				String productOrderPathTemp = productOrderPath.replace('\\','/');
				FileOutputStream fos = new FileOutputStream(String.format(productOrderPathTemp + "%s (%s).docx", Title, dateTimeString ));
				fos.write(results);
				fos.close();
				
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Stock Report created. ");
		}

	}
	
	private String dateTime() {
		
		String dateTime;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		dateTime = dateFormat.format(date);
		return dateTime;
		
	}
	
	private String[][] setTemplateArray(ArrayList<Product> Products) {
		
		String[][] arrayReturn = new String[Products.size() + 1][7];
		String Porousware;
		
		//Title of each column 
		arrayReturn[0][0] = "Product Name";
		arrayReturn[0][1] = "ID";
		arrayReturn[0][2] = "Qty";
		arrayReturn[0][3] = "Min Qty";
		arrayReturn[0][4] = "Max Qty";
		arrayReturn[0][5] = "Price";
		arrayReturn[0][6] = "Porousware";
		
		for (int i = 0; i <  Products.size(); i++ ) {
			
			if (Products.get(i).getPorouswareAvailable() == 1) {
				 Porousware = "Yes";
					
			} else {
				Porousware = "No";
			}
			
			arrayReturn[i + 1][0] = Products.get(i).getProductName();
			arrayReturn[i + 1][1] = Integer.toString(Products.get(i).getProductID());
			arrayReturn[i + 1][2] = Integer.toString(Products.get(i).getProductQty());
			arrayReturn[i + 1][3] = Integer.toString(Products.get(i).getProductMinQty());
			arrayReturn[i + 1][4] = Integer.toString(Products.get(i).getProductMaxQty());
			arrayReturn[i + 1][5] = Float.toString(Products.get(i).getProductPrice());
			arrayReturn[i + 1][6] = Porousware;
		}
		
		
		//Array
		return arrayReturn;
		
	}
	

}
