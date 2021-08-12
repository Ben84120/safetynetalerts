package net.safety.alert.safety.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.Person;

@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	PersonRepository personRepository;

	@Test
	public void getPersonsTest() {
		// GIVEN
		// WHEN
		Iterable<Person> persons = personRepository.findAll();
		// THEN
		assertThat(persons).isNotNull();
		assertThat(persons).hasSizeBetween(1, 25);
		assertThat(persons).hasSize(24);

	}

	@Test
	public void getPersonsByIdTest() {
		// GIVEN
		// WHEN
		Optional<Person> persons = personRepository.findById(1L);
		// THEN
		assertThat(persons.get()).isNotNull();
		assertThat(persons.get().getFirstName()).isEqualTo("John");
		assertThat(persons.get().getLastName()).isEqualTo("Boyd");
		assertThat(persons.get().getAddress()).isEqualTo("1509 Culver St");
		assertThat(persons.get().getCity()).isEqualTo("Culver");
		assertThat(persons.get().getZip()).isEqualTo(97451);
		assertThat(persons.get().getPhone()).isEqualTo("841-874-6512");
		assertThat(persons.get().getEmail()).isEqualTo("jaboyd@email.com");

	}

	@Test
	public void getPersonsById_NotExistingTest() {
		// GIVEN
		// WHEN
		Optional<Person> persons = personRepository.findById(99L);
		// THEN
		assertThat(persons.isPresent()).isFalse();

	}

	@Test
	public void deletePersonById() {
		// GIVEN
		// WHEN
		personRepository.deleteById(5L);
		// THEN
		Optional<Person> persons = personRepository.findById(5L);
		assertThat(persons.isPresent()).isFalse();

	}

	@Test
	public void savePersonTest() {
		// GIVEN
		// WHEN
		Person savePerson = new Person(); 
		savePerson.setFirstName("Ben");
		savePerson.setLastName("Vacher");
		savePerson.setAddress("Impasse lis aucipres");
		savePerson.setCity("Pertuis");
		savePerson.setZip(Integer.parseInt("84120"));
		savePerson.setEmail("vacher_b@yahoo.fr");
		savePerson.setPhone("841-474-6782");
		personRepository.save(savePerson);
		//THEN
		List<Person> person = personRepository.getPersonByLastName("Vacher");
		assertThat(person).isNotNull();
		assertThat(person.get(0).getLastName()).isEqualTo("Vacher");
		
		
		
	}

	@Test
	public void getPersonByLastName( ) {
		//GIVEN
		//WHEN
		List<Person> personByLastName = personRepository.getPersonByLastName("Boyd");
		//THEN
		assertThat(personByLastName).isNotNull();
		assertThat(personByLastName.get(0).getLastName()).isEqualTo("Boyd");
	}
}
