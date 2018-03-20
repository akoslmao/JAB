package modellayer;

public class SaleOrder {
	
	private int soID;
	private String deliveryStatus;
	private String deliveryDate;
	private int custID;
	
	public SaleOrder()
	{
		
	}

	public int getSoID() {
		return soID;
	}

	public void setSoID(int soID) {
		this.soID = soID;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

}