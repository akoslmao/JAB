package modellayer;

public class Customer {
	
	private int custID;
	private String phone;
	private String name;
	private boolean club;
	private String street;
	private int addressID;
	
	public Customer()
	{
		
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public boolean isClub() {
		return club;
	}

	public void setClub(boolean club) {
		this.club = club;
	}
	

}