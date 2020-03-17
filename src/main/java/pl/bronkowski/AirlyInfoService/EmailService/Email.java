package pl.bronkowski.AirlyInfoService.EmailService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class Email {
	
	@NotNull
	private String fromName;
	
	@NotNull
	private String toEmail;
	
	@NotNull
	@Min(10)
	private String emailBody;
}
