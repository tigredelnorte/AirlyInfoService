package pl.bronkowski.AirlyInfoService.airlyModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true )
public class Current {

	  private String fromDateTime; 
	  private String tillDateTime; 
	  private List<Values> values; 
	  private List<Indexes> indexes; 
	  private List<Standards> standards; 
}
