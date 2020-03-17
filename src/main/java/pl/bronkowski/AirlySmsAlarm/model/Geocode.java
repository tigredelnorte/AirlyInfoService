package pl.bronkowski.AirlySmsAlarm.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true )
public class Geocode {

	private String longt;
	private String latt;
}
