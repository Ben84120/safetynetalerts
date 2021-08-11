package net.safety.alert.safety.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.service.PersonService;

@SpringBootTest
public class PersonControllerTest {

	@Autowired
	PersonService personService;

	@Test
	public void getPersonsControllerTest() {
		// GIVEN
		// WHEN
		Optional<Person> persons = personService.getPersonById(2L);
		// THEN
		assertThat(persons.get()).isNotNull();
		assertThat(persons.get().getFirstName()).isEqualTo("Jacob");
		

	}
	
}
