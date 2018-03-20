package controllerlayer;

import java.util.ArrayList;

import DBLayer.*;
import modellayer.Product;

public class ProductController {
	
	public ArrayList<Product> searchProductName(String name) {
        IFDBProd dbProd = new DbProduct();
        return dbProd.searchProductName(name);
	}
	
	public Product searchProductID(int index) {
        IFDBProd dbProd = new DbProduct();
        return dbProd.searchProductID(index);
        
	}
}
