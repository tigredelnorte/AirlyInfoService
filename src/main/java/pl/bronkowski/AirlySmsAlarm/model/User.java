package pl.bronkowski.AirlySmsAlarm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import pl.bronkowski.AirlySmsAlarm.AddressToGeocodeConverter;

@Entity
@Data
public class User{

	private @Id @GeneratedValue Long Id;
	private String firstName;

	private String lastName;

	private String email;

	private String userAddress;

	private String longt;

	private String latt;
	
//	@Autowired
//	AddressToGeocodeConverter address;
	
	public User() {
	}
//	public User(AddressToGeocodeConverter address) {
//		this.address = address;
//	}
	public User(String userName, String email, String userAddress) {
		super();
		this.setUserName(userName);
		this.setUserGeocode(userAddress);
		this.email = email;
		this.userAddress = userAddress;
	}
	public String getUserName() {
		return this.firstName+" "+this.lastName;
	}
	public void setUserName(String name) {
		String[]parts = name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}
	
	public void setUserGeocode(String userAddress) {
		AddressToGeocodeConverter address = new AddressToGeocodeConverter();
		this.longt = address.addressToGeocodeConverter(userAddress).getLongt();
		this.latt = address.addressToGeocodeConverter(userAddress).getLatt();
	}
}
