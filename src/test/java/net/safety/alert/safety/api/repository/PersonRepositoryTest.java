package net.safety.alert.safety.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
		assertThat(persons).hasSize(23);

	}

	@Test
	public void getPersonsByIdTest() {
		// GIVEN
		// WHEN
		Optional<Person> persons = personRepository.findById(1L);
		// THEN
		assertThat(persons.get()).isNotNull();
		assertThat(persons.get().getFirstName()).isEqualTo("John");

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
		personRepository.deleteById(1L);
		// THEN
		Optional<Person> persons = personRepository.findById(1L);
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
		Person person = personRepository.getPersonByLastName("Vacher");
		assertThat(person).isNotNull();
		assertThat(person.getLastName()).isEqualTo("Vacher");
		
		
		
	}

	@Test
	public void getPersonByLastName( ) {
		//GIVEN
		//WHEN
		Iterable<Person> person = personRepository.findAll();
		assertThat(person).isNotNull();
		//assertThat(person.getLastName()).isEqualTo("Boyd");
		//THEN
	}
}
