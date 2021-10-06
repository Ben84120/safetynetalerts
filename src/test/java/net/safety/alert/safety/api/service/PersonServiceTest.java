package net.safety.alert.safety.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonsInfoWithMedicalRecords;

@SpringBootTest
public class PersonServiceTest {
	@Autowired
	PersonService personService;

	@Test
	public void getPersonByIdTest() {
		// GIVEN
		// WHEN
		Optional<Person> person = personService.getPersonById(10L);
		// THEN
		assertThat(person.isPresent()).isTrue();
		assertThat(person.get().getFirstName()).isEqualTo("Tony");
	}

	@Test
	public void getPersonById_Not_ExistTest() {
		// GIVEN
		// WHEN
		Optional<Person> personById = personService.getPersonById(49L);
		// THEN
		assertThat(personById.isPresent()).isFalse();
	}
	// GIVEN

	public void getPersons() {
		// GIVEN
		// WHEN
		Iterable<Person> persons = personService.getPerson();
		// THEN
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
		// GIVEN
		// WHEN
		Person savePerson = new Person();
		savePerson.setFirstName("Fabrice");
		savePerson.setLastName("Messin");
		savePerson.setAddress("1509 Culver St");
		savePerson.setCity("Culver");
		savePerson.setZip(13700);
		savePerson.setPhone("874-895-7812");
		savePerson.setEmail("f.messin@outlook.fr");
		// THEN
		personService.savePerson(savePerson);
	}
	
	@Test
	public void getPersonsNumberByStation_Test() {
		Person p = new Person();
		p.setFirstName("James");
		p.setLastName("Franco");
		p.setAddress("908 73rd St");
		p.setCity("Culver");
		p.setZip(Integer.parseInt("97451"));
		p.setPhone("841-874-4691");
		List<String> personPhoneByStation = personService.getPersonPhoneCoverByStation(1);
		assertThat(personPhoneByStation).isNotNull();
		
			}
	
	@Test
	public void  getPersonEmailByCity_Test() {
		Person p = new Person();
		p.setFirstName("James");
		p.setLastName("Franco");
		p.setAddress("908 73rd St");
		p.setCity("Culver");
		p.setEmail("j.franco@yahoo.fr");
		List<String> listPersons = personService.getPersonEmailByCity("Culver");
		assertThat(listPersons).isNotNull();
		
	}
	
	@Test
	public void getPersonByLastName_Test() {
		// GIVEN
		// WHEN
		List<Person> personByLastName = personService.getPersonByLastName("Boyd");
		assertThat(personByLastName).isNotNull();
		assertThat(personByLastName.get(0).getLastName()).isEqualTo("Boyd");
		// THEN
		
	}
	
	
	@Test
	public void getPersonsInfoWithMedicalRecord() {
		List<PersonsInfoWithMedicalRecords> pInfos = personService.getPersonsInfoWithMedicalRecord("John", "Boyd");
		assertThat(pInfos).isNotNull();
		assertThat(pInfos.isEmpty()).isFalse();
		
		
		
	}  
	
}
