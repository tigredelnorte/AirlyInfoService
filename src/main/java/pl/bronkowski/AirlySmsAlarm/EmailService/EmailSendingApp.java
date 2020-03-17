package pl.bronkowski.AirlySmsAlarm.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSendingApp {
	
	//Create a mail sender
	@Autowired
	JavaMailSender mailSender;
	
	public void sendEmail(Email email) {

		
		//Create an email instance
		System.out.println("Email Start");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getToEmail());
		mailMessage.setSubject("New email from " + email.getFromName());
		mailMessage.setText(email.getEmailBody());
		System.out.println("Email Build");
		//Send mail
		mailSender.send(mailMessage);
		System.out.println("Email Sent");
	}
}
