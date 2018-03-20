package modellayer;

public class Product {
	
	private static int count = 1;
	private int quantity;	
	
	private int ProdID;
	private final String ProdName;
	private final double SalePrice;
	private double SupplierPrice;
	private double RentPrice;
	private int Stock;
	private int MinStock;
	
	public Product(String prodName, double salePrice,double SupplierPrice,double RentPrice,int stock,int minstock) {
		
		this.ProdName = prodName;
		this.SalePrice = salePrice;
		this.SupplierPrice = SupplierPrice;
		this.RentPrice = RentPrice;
		this.Stock = stock;
		this.MinStock = minstock;
		
		
		this.ProdID = count;
		count++;
	}
	
	public Product(int id, String prodName, double salePrice,double SupplierPrice,double RentPrice,int stock,int minstock) {
		
		this(prodName, salePrice, SupplierPrice, RentPrice, stock, minstock);
		this.ProdID = id;
	
	}

	public double getSupplierPrice() {
		return SupplierPrice;
	}

	public void setSupplierPrice(double supplierPrice) {
		SupplierPrice = supplierPrice;
	}

	public double getRentPrice() {
		return RentPrice;
	}

	public void setRentPrice(double rentPrice) {
		RentPrice = rentPrice;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public int getMinStock() {
		return MinStock;
	}

	public void setMinStock(int minStock) {
		MinStock = minStock;
	}

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getProdID() {
		return ProdID;
	}


	public String getProdName() {
		return ProdName;
	}


	public double getSalePrice() {
		return SalePrice;
	}
}