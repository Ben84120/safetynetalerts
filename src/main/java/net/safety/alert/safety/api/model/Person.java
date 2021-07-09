package net.safety.alert.safety.api.model;
import lombok.Data;

public class PersonModel {
	
	

	@Data
	public class Person {

	    public class PersonBuilder {

		}

		public String firstName;

	    public String lastName;

	    public String email;

	    public String city;
	    
	    public Integer zip;
	    
	    public String address;
	    
	    public Integer phone;

	}

}
