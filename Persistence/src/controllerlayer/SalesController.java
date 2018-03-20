package controllerlayer;

import java.util.ArrayList;

import DBLayer.DBSales;
import DBLayer.IFDBSales;
import modellayer.Product;

public class SalesController {

	public void makeSale(ArrayList<Product> sale) {
        IFDBSales dbSales = new DBSales();
        dbSales.makeSale(sale);
	}
}
