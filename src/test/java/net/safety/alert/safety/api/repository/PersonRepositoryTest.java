package net.safety.alert.safety.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonInformations;

@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	PersonRepository personRepository;

	@Test
	public void getPersons_Test() {
		// GIVEN
		// WHEN
		Iterable<Person> persons = personRepository.findAll();
		// THEN
		assertThat(persons).isNotNull();
		assertThat(persons).hasSizeBetween(1, 25);
		List<Person> person = personRepository.getPersonByLastName("Boyd");
		assertThat(person).isNotNull();
		assertThat(person.get(0).getLastName()).isEqualTo("Boyd");

	}

	@Test
	public void getPersonsById_Test() {
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
		List<Person> person = personRepository.getPersonByLastName("Boyd");
		assertThat(person).isNotNull();
		assertThat(person.get(0).getLastName()).isEqualTo("Boyd");

	}

	@Test
	public void getPersonsById_Not_Existing_Test() {
		// GIVEN
		// WHEN
		Optional<Person> persons = personRepository.findById(99L);
		// THEN
		assertThat(persons.isPresent()).isFalse();

	}

	@Test
	public void deletePersonById_Test() {
		// GIVEN
		// WHEN
		personRepository.deleteById(5L);
		// THEN
		Optional<Person> persons = personRepository.findById(5L);
		assertThat(persons.isPresent()).isFalse();

	}
	

	@Test
	public void savePerson_Test() {
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
		// THEN
		List<Person> person = personRepository.getPersonByLastName("Vacher");
		assertThat(person).isNotNull();
		assertThat(person.get(0).getLastName()).isEqualTo("Vacher");

	}

	@Test
	public void getPersonByLastName_Test() {
		// GIVEN
		// WHEN
		List<Person> personByLastName = personRepository.getPersonByLastName("Boyd");
		// THEN
		assertThat(personByLastName).isNotNull();
		assertThat(personByLastName.get(0).getLastName()).isEqualTo("Boyd");
	}

	@Test
	public void getPersonInformations_Test() {
		PersonInformations p = new PersonInformations();
		FireStations f = new FireStations();
		p.setNom("Kent");
		p.setPrenom("Clark");
		p.setAdresse("644 Gershwin Cir");
		p.setNumeroTel("841-874-5873");
		f.setStation(91);
		List<PersonInformations> personInformations = personRepository.getPersonInformations(91);
		assertThat(personInformations).isNotNull();
		assertThat(f.getStation()).isEqualTo(91);

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
		List<String> personPhoneByStation = personRepository.getPhoneByStation(1);
		assertThat(personPhoneByStation).isNotNull();

	}

	@Test
	public void getPersonEmailByCity_Test() {
		Person p = new Person();
		p.setFirstName("James");
		p.setLastName("Franco");
		p.setAddress("908 73rd St");
		p.setCity("Culver");
		List<Person> listPersons = personRepository.findByCity("Culver");
		assertThat(listPersons).isNotNull();

	}

	@Test
	public void findByLastNameAndFirstName() {
		Person p = new Person();
		p.setFirstName("Carl");
		p.setLastName("Hunter");
		p.setAddress("Rue du parc");
		p.setCity("Paris");
		p.setZip(Integer.parseInt("75010"));
		p.setPhone("843-582-6593");
		List<Person> listPersons = personRepository.findByLastName("Hunter");
		listPersons.add(p);
		assertThat(listPersons).isNotNull();
		assertThat(listPersons.get(0)).isEqualTo(p);
		assertThat(p.getFirstName()).isEqualTo("Carl");
		assertThat(p.getLastName()).isEqualTo("Hunter");
		assertThat(p.getAddress()).isEqualTo("Rue du parc");
		assertThat(p.getCity()).isEqualTo("Paris");
		assertThat(p.getZip()).isEqualTo(Integer.parseInt("75010"));
		assertThat(p.getPhone()).isEqualTo("843-582-6593");
	}
	
	
}
