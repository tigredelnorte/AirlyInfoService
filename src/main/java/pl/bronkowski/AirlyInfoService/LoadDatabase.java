package pl.bronkowski.AirlyInfoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bronkowski.AirlyInfoService.model.User;
import pl.bronkowski.AirlyInfoService.repository.UserRepository;


@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initialDatabase(UserRepository repository) {
        System.out.println("Loging Started!");
        return args -> {
            log.info("Preloading" + repository.save(new User("Darek Stefanowicz", "lbronkowski@gmail.com", "Kraków Grota Roweckiego 1")));
            Thread.sleep(1000);
            log.info("Preloading" + repository.save(new User("Szymek Bronkowski", "justocz@gmail.com", "Krakow Na Blonie 13")));
            Thread.sleep(1000);
            log.info("Preloading"+repository.save(new User("Lukasz Bronkowski", "+48661304714", "Zamosc Kilinskiego 29")));
            Thread.sleep(1000);
            log.info("Preloading"+repository.save(new User("Justyna Bronkowska", "+48665143726", "Lubin Sosnowa 15")));
        };
    }

}
