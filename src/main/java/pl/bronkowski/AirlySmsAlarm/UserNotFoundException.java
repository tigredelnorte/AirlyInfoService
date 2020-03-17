package pl.bronkowski.AirlySmsAlarm;

public class UserNotFoundException extends RuntimeException{
	
	UserNotFoundException(Long id){
		super("Could not find user "+id);
	}

}
