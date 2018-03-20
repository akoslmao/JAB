package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import controllerlayer.ProductController;
import controllerlayer.SalesController;
import modellayer.Product;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class ProcessOrders extends JFrame {

	private JPanel contentPane;
	private JTextField TotalTextField;
	private JLabel lblTotal;
	private JButton btnAddCustomer;
	private JTextField CustomerTextField;
	private JLabel lblNewLabel;
	private JButton btnProcessOrder;
	private TablePanel table;
	private TableResultsPanel search;
	private JTextField productNameSearch;
	private JFormattedTextField textField;
	private JButton btnSearch;
	private ProductTransfer transfer;
	private ArrayList<Product>sales = new ArrayList<>();	
	private double runningTotal;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcessOrders frame = new ProcessOrders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ProcessOrders() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		runningTotal = 0.0;
		
		
		btnAddCustomer = new JButton("Add Customer");
		btnProcessOrder = new JButton("Process Order");
		
		
		lblNewLabel = new JLabel("Customer:");
		lblTotal = new JLabel("Total:");
		JLabel lblSearchProductName = new JLabel("Search Product Name:");
		
		
		TotalTextField = new JTextField();
		TotalTextField.setEditable(false);
		TotalTextField.setColumns(10);
		TotalTextField.setText(Double.toString(runningTotal));
		
		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Integer.class); //optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		
		textField = new JFormattedTextField(numberFormatter);
		textField.setColumns(10);		
		
		CustomerTextField = new JTextField();
		CustomerTextField.setEditable(false);
		CustomerTextField.setColumns(10);
		
		productNameSearch = new JTextField();
		productNameSearch.setColumns(10);
		
		table = new TablePanel();
		search = new TableResultsPanel();
		
		table.setDeleteInterface(new DeleteRow() {
			public void delete(int row) {
				double delta = sales.get(row).getQuantity() * sales.get(row).getSalePrice();
				runningTotal -= delta;
				TotalTextField.setText(Double.toString(runningTotal));
				sales.remove(row);
			}
		});
		
		table.setChangeQuantity(new ChangeQuantity() {
			@Override
			public void change(int amount, int row) {
				int original = sales.get(row).getQuantity();
				double price = sales.get(row).getSalePrice();
				
				double newAmount = (amount - original) * price;
				runningTotal += newAmount;
				TotalTextField.setText(Double.toString(runningTotal));
				sales.get(row).setQuantity(amount);				
			}			
		});
		
		search.setInterface(new ProductTransfer() {
			public void emit(Product product, int quantity) {
				
				for(int i = 0; i < sales.size(); i++) {
					Product prod = sales.get(i);
					if(product.getProdName().equals(prod.getProdName())) {
						int original = prod.getQuantity();
						double price = prod.getSalePrice();
						int newQuantity = original + quantity;
						double newAmount = original * price;
						runningTotal += newAmount;
						TotalTextField.setText(Double.toString(runningTotal));
						prod.setQuantity(newQuantity);
						table.refresh();
						return;
					}
				}
				product.setQuantity(quantity);
				double price = product.getSalePrice() * quantity;
				runningTotal += price;
				TotalTextField.setText(Double.toString(runningTotal));
				sales.add(product);
				
				table.setMatch(product);
				table.refresh();		
			}
		});
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<Product>matches = new ArrayList<>();

				
				String name = productNameSearch.getText();
				String id = textField.getText();
				
				if(name.length() == 0 && id.length() > 0) {
								
					ProductController prodCntr = new ProductController();
					
//					int index = Integer.parseInt(id);
//
//					Product product = prodCntr.searchProductID(index);
//					if(product == null)
//					{
//						JOptionPane.showMessageDialog(search, "No matches found");
//						return;
//
//					}
//					search.setSingleMatch(product);
//					search.refresh();
				}
				
				if(name.length() > 0 && id.length() == 0) {
					ProductController prodCntr = new ProductController();
					matches = prodCntr.searchProductName(name);
					if(matches.size() == 0)
					{
						JOptionPane.showMessageDialog(search, "No matches found");
						return;

					}
					search.setMatches(matches);
					search.refresh();
				}				
				
				if(name.length() > 0 && id.length() > 0 ||
				   name.length() == 0 && id.length() == 0) {
					JOptionPane.showMessageDialog(search, "Select either ProductName or Product ID");
				}	
			}
		});
		
		btnProcessOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SalesController saleCntr = new SalesController();

				saleCntr.makeSale(sales);
				table.clear();
				search.clear();
				JOptionPane.showMessageDialog(table, "Sale Complete");
			}			
		});
		
		
		JLabel lblSearchProductId = new JLabel("Search Product ID:");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSearchProductName)
								.addComponent(lblSearchProductId, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(productNameSearch, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
							.addGap(199)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAddCustomer, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNewLabel))
								.addComponent(lblTotal))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(CustomerTextField)
								.addComponent(TotalTextField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addComponent(search, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(table, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
								.addComponent(btnProcessOrder, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(search, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
						.addComponent(table, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(TotalTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotal)
						.addComponent(lblSearchProductName)
						.addComponent(productNameSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(CustomerTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(btnAddCustomer)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSearchProductId))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnProcessOrder, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}