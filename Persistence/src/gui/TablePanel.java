package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;

import modellayer.Product;

public class TablePanel extends JPanel implements ActionListener {
	
	private JTable table;
	private TableModel tModel;
	private JPopupMenu popup;
	private JMenuItem editItem;
	private JMenuItem removeItem;
	private ProductTransfer transfer;
	private DeleteRow delete;
	private ChangeQuantity change;
	private ArrayList<Product>matches = new ArrayList<>();
	
	public TablePanel() {
		tModel = new TableModel();
		table = new JTable(tModel);
		popup = new JPopupMenu();
		
		removeItem = new JMenuItem("Delete");
		editItem = new JMenuItem("Edit");
		
		popup.add(removeItem);
		popup.add(editItem);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					popup.show(table, e.getX(), e.getY());
				}
			}
		});
		
		removeItem.addActionListener(this);
		editItem.addActionListener(this); 	
		
		setLayout(new BorderLayout());
			
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		
		int row;
		
		JMenuItem menItem = (JMenuItem)e.getSource();

		if(menItem == removeItem) {
			row = table.getSelectedRow();
			int option = JOptionPane.showOptionDialog(table, "Are You Sure?", null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (option == JOptionPane.CANCEL_OPTION)
			{
	    		  
			} else if (option == JOptionPane.OK_OPTION)
			{
				if(delete != null) {				
					delete.delete(row);
					tModel.fireTableRowsDeleted(row, row);
			}
		}		
	}
		
		if(menItem == editItem) {
		    row = table.getSelectedRow();
			SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, 30, 1);
			JSpinner spinner = new JSpinner(sModel);	        	
			int option = JOptionPane.showOptionDialog(table, spinner, "Enter amount", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (option == JOptionPane.CANCEL_OPTION)
			{
  		  
			} else if (option == JOptionPane.OK_OPTION)
			{
				int quantity = (int)spinner.getValue();
				change.change(quantity, row);
				refresh();
			}
		}
	}
	
	public void refresh() {
		tModel.fireTableDataChanged();
	}
	
	public void clear() {
		matches.clear();
		tModel.clear();
	}
	
	public void setMatch(Product product) {
		matches.add(product);
		tModel.setSaleLines(matches);
	}	
	
	public void setInterface(ProductTransfer transfer) {
		this.transfer = transfer;
	}
	
	public void setDeleteInterface(DeleteRow delete) {
		this.delete = delete;
	}
	
	public void setChangeQuantity(ChangeQuantity change) {
		this.change = change;
	}
}