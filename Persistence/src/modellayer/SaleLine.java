package modellayer;

public class SaleLine {

	private int saleLineID;
	private int soID;
	private int prodID;
	private int quantity;
	
	private SaleLine()
	{
		
	}

	public int getSaleLineID() {
		return saleLineID;
	}

	public void setSaleLineID(int saleLineID) {
		this.saleLineID = saleLineID;
	}

	public int getSoID() {
		return soID;
	}

	public void setSoID(int soID) {
		this.soID = soID;
	}

	public int getProdID() {
		return prodID;
	}

	public void setProdID(int prodID) {
		this.prodID = prodID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}