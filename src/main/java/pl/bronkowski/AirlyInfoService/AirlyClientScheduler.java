package pl.bronkowski.AirlyInfoService;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.bronkowski.AirlyInfoService.EmailService.Email;
import pl.bronkowski.AirlyInfoService.EmailService.EmailSendingApp;
import pl.bronkowski.AirlyInfoService.airlyModel.AirlyModel;
import pl.bronkowski.AirlyInfoService.controller.UserController;
import pl.bronkowski.AirlyInfoService.model.User;
import pl.bronkowski.AirlyInfoService.repository.UserRepository;

@Component
public class AirlyClientScheduler {

	@Autowired
	AirlyPolutionCallService airlyPolutionCallService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailSendingApp emailSendingApp;
	
	@Autowired
	UserController userController;
	
	@Scheduled(cron = "0 * * * * *")
	public void makeACallToAirly() {
		List<User> users = userRepository.findAll();
		for(int i=0; i<users.size(); i++) {
			User currentUser = users.get(i);
			if(currentUser.getLatt()==null || currentUser.getLongt()==null) {
				currentUser.setUserGeocode(currentUser.getUserAddress());
				try {
					userController.updateUser(currentUser, currentUser.getId());
				} catch (URISyntaxException e) {
				}
			}
			
			AirlyModel airlyData = airlyPolutionCallService.getAirlyData(currentUser.getId());
				try {
					Thread.sleep(1000);					
				} catch (InterruptedException e) {
				}
	
			Email email = new Email();
			email.setToEmail(currentUser.getEmail());
			email.setFromName(currentUser.getUserName());
			email.setEmailBody("Dear "+currentUser.getFirstName()+".\n"
					+"Current Airly Air Polution values in your area:.\n"
					+"PM2.5 : "+ airlyData.getCurrent().getValues().get(1).getValue()+" which is "+airlyData.getCurrent().getStandards().get(0).getPercent()+"% of WHO standard.\n"
					+"PM10 : "+ airlyData.getCurrent().getValues().get(3).getValue()+" which is "+airlyData.getCurrent().getStandards().get(0).getPercent()+"% of WHO standard.\n\n"
					+"AIRLY rates pollution level as "+ airlyData.getCurrent().getIndexes().get(0).getLevel()+"\n"
					+"AIRLY recommends: "+airlyData.getCurrent().getIndexes().get(0).getAdvice()+"\n\n"
					+"In one hour we will send you updated Airly pollution data for your location.\n"
					+ "THANK YOU!");
			System.out.println(email.toString());
			emailSendingApp.sendEmail(email);
		}
	}
	
}
