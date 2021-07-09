package net.safety.alert.safety.api.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;




	@Data
	@Entity
	@Table(name = "firestations")
	public class FireStations {
		
		public String adresse;
		
		public Integer station;
		
	}
	
