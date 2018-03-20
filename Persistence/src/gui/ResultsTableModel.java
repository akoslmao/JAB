package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import modellayer.Product;

public class ResultsTableModel extends AbstractTableModel {

	private String[] colNames = {"Name", "Product ID", "Price"};
	private ArrayList<Product>matches = new ArrayList<>();
	
	
	public String getColumnName(int column) {
		return colNames[column];
	}	
	
	
	@Override
	public int getColumnCount() {
		return colNames.length;
	}


	@Override
	public int getRowCount() {
		return matches.size();
	}

	
	@Override
	public Object getValueAt(int row, int col) {
		Product product = matches.get(row);
		
		switch(col) {
		case 0:
			return product.getProdName();
		case 1:
			return product.getProdID();
		case 2:
			return product.getSalePrice();
		}
		return null;
	}	
	
	
	public void setMatches(ArrayList<Product>matches) {
		this.matches = matches;
	}


	public void clear() {
		matches.clear();
		super.fireTableDataChanged();
	}
}