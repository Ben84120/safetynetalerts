package net.safety.alert.safety.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonStationCover implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 20000000L;
	private List<PersonInformations> personsList;
	private Integer nombreAdultes;
	private Integer nombreMineurs;
	
	

}
