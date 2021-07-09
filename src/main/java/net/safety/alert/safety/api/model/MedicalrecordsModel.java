package net.safety.alert.safety.api.model;
import java.util.Date;
import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


public class MedicalrecordsModel {
	
	@Data
	@Entity
	@Table(name = "medicalrecords")
	public class Medicalrecords {
		
		public class MedicalrecordsBuilder {

		}

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    public String firstName;
	
	    public String lastName;
	
	    public Date birthdate;
	
	    public String medications;
	
	    public String allergies;
	
	
	
	

	
}
}