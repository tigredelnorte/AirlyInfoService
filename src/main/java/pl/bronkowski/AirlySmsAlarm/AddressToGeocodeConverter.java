package pl.bronkowski.AirlySmsAlarm;

import org.springframework.web.client.RestTemplate;

import pl.bronkowski.AirlySmsAlarm.model.Geocode;


public class AddressToGeocodeConverter {

	public Geocode addressToGeocodeConverter(String userAddress) {
		String url = "https://geocode.xyz/"+userAddress+"?json=1";
		RestTemplate restTemplate = new RestTemplate();
		Geocode geocode = restTemplate.getForObject(url, Geocode.class);
		return geocode;
	}
}   
