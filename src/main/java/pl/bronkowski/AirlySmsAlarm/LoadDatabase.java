package pl.bronkowski.AirlySmsAlarm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import pl.bronkowski.AirlySmsAlarm.model.User;
import pl.bronkowski.AirlySmsAlarm.repository.UserRepository;


@Configuration
@Slf4j
public class LoadDatabase {
	
	@Bean
	CommandLineRunner initialDatabase(UserRepository repository) {
		System.out.println("Loging Started!");
		return args -> {
			log.info("Preloading"+ repository.save(new User("Tymek Bronkowski", "lbronkowski@gmail.com", "Rząska Jodlowa 3")));
			log.info("Preloading"+repository.save(new User("Szymek Bronkowski", "justocz@gmail.com", "Krakow Na Blonie 13")));
//			log.info("Preloading"+repository.save(new User("Lukasz Bronkowski", "+48661304714", "Zamosc Kilinskiego 29")));
//			log.info("Preloading"+repository.save(new User("Justyna Bronkowska", "+48665143726", "Lubin Sosnowa 15")));
		};
	}

}