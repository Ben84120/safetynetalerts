package net.safety.alert.safety.api.model;
import java.util.Date;
import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



	
	@Data
	@Entity
	@Table(name = "medicalrecords")
	public class MedicalRecords {
		
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
