package pl.bronkowski.AirlyInfoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class AirlySmsAlarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlySmsAlarmApplication.class, args);
		System.out.println("Hello World!");
	}

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
