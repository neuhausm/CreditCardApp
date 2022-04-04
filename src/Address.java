
public class Address {
	private String street;
	private String city;
	private USState state;
	private String zip;
	
	
	public Address(String street, String city, USState state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public Address(String location) {
		//just for default 
		this.street=location;
		this.city="Brooklyn";
		this.state= USState.NY;
		this.zip= "11219";
	}
	
	@Override
	public String toString() {
		return (this.street + " " + this.city + ", " + this.state + " " + this.zip);
	}
}
