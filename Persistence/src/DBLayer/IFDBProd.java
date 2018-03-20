package DBLayer;

import java.util.ArrayList;

import modellayer.Product;

// Interface for the DBProduct	
public interface IFDBProd {
	public ArrayList<Product> searchProductName( String prodName);
    public Product searchProductID( String prodID);
}
