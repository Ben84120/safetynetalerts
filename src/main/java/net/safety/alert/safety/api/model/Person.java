package net.safety.alert.safety.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "person")
public class Person {

	public static class PersonBuilder {
		
		//public PersonBuilder firstName(String firstName) {

		//this.firstName = firstName;

		//return this;

		//}

}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public String firstName;

	public String lastName;

	public String email;

	public String city;

	public Integer zip;

	public String address;

	public Integer phone;

}

