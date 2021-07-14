package net.safety.alert.safety.api.model;

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

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public String firstName;

	public String lastName;

	public Integer birthdate;

	public String medications;

	public String allergies;

	public Integer getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Integer Birthdate) {

	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String Medications) {

	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String Allergies) {

	}

}
