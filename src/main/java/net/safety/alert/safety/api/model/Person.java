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

		// public PersonBuilder firstName(String firstName) {

		// this.firstName = firstName;

		// return this;

		// }

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {

	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer Zip) {

	}

	public String getCity() {
		return city;
	}

	public void setCity(String City) {

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String Address) {

	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer Phone) {

	}
}
