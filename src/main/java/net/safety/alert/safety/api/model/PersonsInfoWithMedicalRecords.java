package net.safety.alert.safety.api.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonsInfoWithMedicalRecords implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private List<Person> ListPerson;
	private String nom;
	private String prenom;
	private String adresse;
	private int age;
	private String email;
	private String medication;
	private String allergies;
	
	

}
