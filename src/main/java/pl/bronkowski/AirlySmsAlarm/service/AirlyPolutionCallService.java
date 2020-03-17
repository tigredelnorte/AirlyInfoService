package pl.bronkowski.AirlySmsAlarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import pl.bronkowski.AirlySmsAlarm.airlyModel.AirlyModel;
import pl.bronkowski.AirlySmsAlarm.controller.UserController;

@Component
public class AirlyPolutionCallService {

	@Autowired
	UserController userController;

//	Dlaczego podpięcie UserRepository wypierdala aplikacje??? CommandLineRunner failed to execute!!!!!


	int counter = 0;
	//@GetMapping("/airly/{userId}")
	public AirlyModel getAirlyData(Long userId) {

		String latt = userController.getById(userId).getContent().getLatt();
		
		String longt = userController.getById(userId).getContent().getLongt();

		String AIRLY_API_URL = "http://airapi.airly.eu/v2/measurements/nearest?indexType=AIRLY_CAQI&lat="+latt+"&lng="+longt+"&maxDistanceKM=3&apikey=Bz1luw9XtxsQ5VVVvYGpSEamcF8uuSEp";

		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AirlyModel> response = restTemplate.getForEntity(AIRLY_API_URL, AirlyModel.class);
		System.out.println("Counter = "+counter+" "+response.getBody());
		counter++;
		return response.getBody();
	}
	
	
	
//	
//	@PostConstruct
//	public void getAirlyData() throws IOException, InterruptedException, NullPointerException {
//		
//		
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create(AIRLY_API_URL))
//				.build();
//		HttpResponse<String>httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println(httpResponse.body());
//
//		AirlyModel airlyModel = new AirlyModel();
//		Current current = new Current();
//		List<?> values = new ArrayList<>();
//		ObjectMapper mapper = new ObjectMapper();
//		Gson gson = new Gson();
//		System.out.println(mapper.readValue(httpResponse.body(), AirlyModel.class).getCurrent().getFromDateTime());
//		airlyModel = gson.fromJson(httpResponse.body(), AirlyModel.class);
//		values = airlyModel.getCurrent().getValues();
//		System.out.println(values);
//		}
}
	