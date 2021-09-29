package net.safety.alert.safety.api.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class FloodStations implements Serializable {
	
	private static final long serialVersionUID = 65000L;
	
	private String LAST_NAME;
	private String FIRST_NAME;
	private String ADDRESS;
	private String PHONE;
	private String BIRTHDATE;
	private String MEDICATIONS;
	private String ALLERGIES;

	
	
	}
