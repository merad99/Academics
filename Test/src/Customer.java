
public class Customer {
	private String phoneNo;
	private String name;
	
	public Customer(String n, String p) { 
		n = name; 
		p = phoneNo;
		} 

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [phoneNo=" + phoneNo + ", name=" + name + "]";
	}


}
