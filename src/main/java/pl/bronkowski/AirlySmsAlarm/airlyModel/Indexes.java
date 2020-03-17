package pl.bronkowski.AirlySmsAlarm.airlyModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true )
public class Indexes {

	  private String name; 
	  private double value; 
	  private String level; 
	  private String description; 
	  private String advice; 
	  private String color; 
}
