package net.safety.alert.safety.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.Person;

@SpringBootTest
public class PersonServiceTest {
	@Autowired
	PersonService personService;

	/*@BeforeEach
	private void setUpPerTest() {
		ticket = new Ticket();
	}*/

	@Test
	public void getPersonByIdTest() {
		
		Optional<Person> person = personService.getPersonById(10L);
		assertThat(person.isPresent()).isTrue();
		assertThat(person.get().getFirstName()).isEqualTo("Tony");
	}
	
	@Test
	public void getPersonById_Not_ExistTest() {
		
		Optional<Person> personById = personService.getPersonById(49L);
		assertThat(personById.isPresent()).isFalse();
	}
		@Test
		public void getPersons() {
			Iterable<Person> persons = personService.getPerson();
			assertThat(persons).isNotNull();
			assertThat(persons).hasSizeBetween(20, 30);
			
		}
		
		@Test
		public void deletePersonByIdTest() {
			// GIVEN
			// WHEN
			personService.deletePerson(15L);
			// THEN
			Optional<Person> persons = personService.getPersonById(15L);
			assertThat(persons.isPresent()).isFalse();
		}
		
		@Test
		public void savePersonTest() {
			Person savePerson = new Person();
			savePerson.setFirstName("Fabrice");
			savePerson.setLastName("Messin");
			savePerson.setAddress("Rue des cordonniers");
			savePerson.setCity("Vitrolle");
			savePerson.setZip(13700);
			savePerson.setPhone("874-895-7812");
			savePerson.setEmail("f.messin@outlook.fr");
			personService.savePerson(savePerson);
		}
}
