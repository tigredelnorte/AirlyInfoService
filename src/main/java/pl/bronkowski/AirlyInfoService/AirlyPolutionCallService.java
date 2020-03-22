package pl.bronkowski.AirlyInfoService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pl.bronkowski.AirlyInfoService.airlyModel.AirlyModel;
import pl.bronkowski.AirlyInfoService.controller.UserController;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AirlyPolutionCallService {

	private static final String AIRLY_API_URL = "http://airapi.airly.eu/v2/measurements/nearest?indexType=AIRLY_CAQI&lat=%s&lng=%s&maxDistanceKM=3&apikey=Bz1luw9XtxsQ5VVVvYGpSEamcF8uuSEp";

	//user controller nie potrzebny - użyć user service który zostanie dodany pomiedzy repo i controller!!!!!!!!!!!!!
	private final UserController userController;

	private final RestTemplate restTemplate;
//	Dlaczego podpięcie UserRepository wypierdala aplikacje??? CommandLineRunner failed to execute!!!!!


	private final AtomicInteger counter = new AtomicInteger();

	public AirlyPolutionCallService(UserController userController, RestTemplate restTemplate) {
		this.userController = userController;
		this.restTemplate = restTemplate;
	}

	//@GetMapping("/airly/{userId}")
	//tu jako argument dostajemy Geocode geocode
	//AIrly service nie musi nic wiedziec o userservice
	public AirlyModel getAirlyData(Long userId) {

		String latt = userController.getById(userId).getContent().getLatt();
		
		String longt = userController.getById(userId).getContent().getLongt();

		ResponseEntity<AirlyModel> response = restTemplate.getForEntity(String.format(AIRLY_API_URL, longt, latt), AirlyModel.class);
		System.out.println("Counter = "+counter.incrementAndGet()+" "+response.getBody());
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
	