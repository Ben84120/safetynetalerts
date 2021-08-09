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
		//GIVEN
		//WHEN
		Iterable<Person> persons = personRepository.findAll();
		//THEN
		assertThat(persons).isNotNull();
		assertThat(persons).hasSizeBetween(1, 25);
		assertThat(persons).hasSize(23);
		
		
	}
	
	@Test
	public void getPersonsByIdTest() {
		//GIVEN
		//WHEN
		Optional<Person> persons = personRepository.findById(1L);
		//THEN
		assertThat(persons.get()).isNotNull();
		assertThat(persons.get().getFirstName()).isEqualTo("John");
		
		
	}
	
	@Test
	public void getPersonsById_NotExistingTest() {
		//GIVEN
		//WHEN
		Optional<Person> persons = personRepository.findById(99L);
		//THEN
		assertThat(persons.isPresent()).isFalse();
		
		}
	
	
	
	
	
	
	
		
		
		
	}
	
	
	
	


