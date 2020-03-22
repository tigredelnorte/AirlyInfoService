package pl.bronkowski.AirlyInfoService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.internal.build.AllowPrintStacktrace;
import pl.bronkowski.AirlyInfoService.AddressToGeocodeConverter;

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
		this.setUserAddress(userAddress);
		this.email = email;
		this.userAddress = userAddress;

	}
	public String getUserName() {
		return this.firstName+" "+this.lastName;
	}

	//sprawdzic czy długość tablicy wynosi dwa aby było imie i nazwisko
	public void setUserName(String name) {
		String[]parts = name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}

	//convert this request to a separate servis that will provide geocode to address and store it in separate repo
	//remove from User entirely
	//user Spring Retry dla ponowienia nie pobranego geocodu
	public void setUserAddress(String userAddress) {
		AddressToGeocodeConverter address = new AddressToGeocodeConverter();
		this.longt = address.addressToGeocodeConverter(userAddress).getLongt();
		this.latt = address.addressToGeocodeConverter(userAddress).getLatt();
	}
}
