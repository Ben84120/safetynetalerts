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
public class ChildAlert implements Serializable {
	
	
	private static final long serialVersionUID = 64L;
	
	private String lastName;
	private String firstName;
	private Integer age;
	private List<Person> membreFamille;
	

}
