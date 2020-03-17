package pl.bronkowski.AirlyInfoService.airlyModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true )
public class Values {
	private String name; 
	private double value; 
}
