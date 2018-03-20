package DBLayer;

import java.sql.SQLException;

import modellayer.Product;

public class DbConnectionTest {

	private static DbProduct dbp = new DbProduct();
	public static void main(String[] args) {
		
		try {
			DbConnection.getInstance().connect();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		dbp.addProduct(new Product("D",5,5,5,5,1));
		dbp.addProduct(new Product("a",9,9,9,9,1));
		
		try {
			dbp.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dbp.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}