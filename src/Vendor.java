
public class Vendor {
	private String name;
	private Address address;
	
	public Vendor(String name, Address address) {
		this.name=name;
		this.address=address;
	}
	
	@Override
	public String toString() {
		return (" Vendor name: "+ this.name + " Address: "+ address.toString());
	}

}
