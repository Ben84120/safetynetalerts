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
public class PersonsWithFireStationNumber implements Serializable {

	private static final long serialVersionUID = 19563L;


	private String firstName;

	private String lastName;

	private int age;

	private String phone;

	private String[] medications;

	private String[] allergies;

}
