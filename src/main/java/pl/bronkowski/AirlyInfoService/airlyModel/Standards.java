package pl.bronkowski.AirlyInfoService.airlyModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true )
public class Standards {

	  private String name; 
	  private String pollutant; 
	  private double limit; 
	  private double percent; 
	  private String averaging;
}
