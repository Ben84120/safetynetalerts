package net.safety.alert.safety.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.ChildAlert;
import net.safety.alert.safety.api.model.FirePersons;
import net.safety.alert.safety.api.model.Foster;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonInformations;
import net.safety.alert.safety.api.model.PersonWithMedicalRecords;
import net.safety.alert.safety.api.model.PersonsInfoWithMedicalRecords;
import net.safety.alert.safety.api.model.PersonsWithFireStationNumber;

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
		p.setFirstName("Jonanathan");
		p.setLastName("Marrack");
		p.setAddress("29 15th St");
		p.setCity("Culver");
		p.setZip(Integer.parseInt("97451"));
		p.setPhone("841-874-6513");
		List<String> personPhoneByStation = personService.getPersonPhoneCoverByStation(2);
		assertThat(personPhoneByStation).isNotNull();
		assertThat(p.getFirstName()).isEqualTo("Jonanathan");
		assertThat(p.getLastName()).isEqualTo("Marrack");
		assertThat(p.getAddress()).isEqualTo("29 15th St");
		assertThat(p.getCity()).isEqualTo("Culver");
		assertThat(p.getPhone()).isEqualTo("841-874-6513");
		
	}

	@Test
	public void getPersonEmailByCity_Test() {
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
		Person p = new Person();
		p.setFirstName("Jonanathan");
		p.setLastName("Marrack");
		p.setAddress("29 15th St");
		p.setCity("Culver");
		p.setZip(Integer.parseInt("97451"));
		p.setPhone("841-874-6513");
		List<Person> personByLastName = personService.getPersonByLastName("Marrack");
		assertThat(personByLastName).isNotNull();
		assertThat(personByLastName.get(0).getLastName()).isEqualTo("Marrack");
		// THEN

	}

	@Test
	public void getPersonsInfoWithMedicalRecord() {
		PersonsInfoWithMedicalRecords pInfo = new PersonsInfoWithMedicalRecords();
		pInfo.setNom("Boyd");
		pInfo.setPrenom("Tenley");
		pInfo.setAge(9);
		pInfo.setAllergies("peanut");
		pInfo.setEmail("tenz@email.com");
		pInfo.setAdresse("1509 Culver St");
		pInfo.setMedication("");
		List<PersonsInfoWithMedicalRecords> pInfos = personService.getPersonsInfoWithMedicalRecord("Boyd");
		assertThat(pInfos).isNotNull();
		assertThat(pInfo.getNom()).isEqualTo("Boyd");
		assertThat(pInfo.getPrenom()).isEqualTo("Tenley");
		assertThat(pInfo.getAge()).isEqualTo(9);
		assertThat(pInfo.getEmail()).isEqualTo("tenz@email.com");
		assertThat(pInfo.getAdresse()).isEqualTo("1509 Culver St");
		assertThat(pInfo.getMedication()).isEqualTo("");
		assertThat(pInfo.getAllergies()).isEqualTo("peanut");

	}

	@Test
	public void getChildAlert() {
		ChildAlert c = new ChildAlert();
		c.setFirstName("Brandon");
		c.setLastName("Brenda");
		c.setBirthDate(9);
		List<ChildAlert> membresFamille = personService.getChildAlert("Tatouine");
		assertThat(membresFamille).isNotNull();
		assertThat(c.getFirstName()).isEqualTo("Brandon");
		assertThat(c.getLastName()).isEqualTo("Brenda");
		assertThat(c.getBirthDate()).isEqualTo(9);

	}
@Test
public void getFoster() {
	Foster f = new Foster();
	f.setAddress("112 Steppes Pl");
	f.setPersonWithMedicalRecords(new ArrayList<>());
	List<Foster> fosters = personService.findPersonAndMedicalRecordsByStation(3);
	assertThat(fosters).isNotNull();
	assertThat(fosters).hasSizeBetween(1, 10);
	
}

@Test
public void getPersonWithMedicalRecords() {
	PersonWithMedicalRecords pMd = new  PersonWithMedicalRecords();
	pMd.setFirstName("Peter");
	pMd.setLastName("Duncan");
	pMd.setAge(21);
	pMd.setPhone("841-874-6512");
	String[] allergies = {"shellfish"};
	String[] medications = {};
	assertThat(pMd.getFirstName()).isEqualTo("Peter");
	assertThat(pMd.getLastName()).isEqualTo("Duncan");
	assertThat(pMd.getAge()).isEqualTo(21);
	assertThat(pMd.getPhone()).isEqualTo("841-874-6512");
	assertThat(allergies).contains("shellfish");
	assertThat(medications).isEmpty();
	
}

@Test
public void getFireAddress() {
	FirePersons fP = new FirePersons();
	fP.setStation(3);
	fP.setPersons(new ArrayList<>());
	List<FirePersons> firePersons = personService.getFireAddress("1509 Culver St");
	assertThat(firePersons).isNotNull();
	assertThat(firePersons).hasSizeBetween(1, 15);
	assertThat(fP.getStation()).isEqualTo(3);
	assertThat(firePersons.get(0).getPersons()).isNotNull();
	
}

@Test
public void getPersonsWithFireStationNumber() {
	PersonsWithFireStationNumber pWFN = new PersonsWithFireStationNumber();
	pWFN.setFirstName("Tenley");
	pWFN.setLastName("Boyd");
	pWFN.setAge(9);
	pWFN.setPhone("841-874-6512");
	assertThat(pWFN).isNotNull();
	assertThat(pWFN.getFirstName()).isEqualTo("Tenley");
	assertThat(pWFN.getLastName()).isEqualTo("Boyd");
	assertThat(pWFN.getAge()).isEqualTo(9);
	assertThat(pWFN.getPhone()).isEqualTo("841-874-6512");
}

@Test
public void getPersonInformations() {
	PersonInformations pInfos = new PersonInformations();
	pInfos.setNom("Zemicks");
	pInfos.setPrenom("Zach");
	pInfos.setAdresse("892 Downing Ct");
	pInfos.setNumeroTel("841-874-7512");
	assertThat(pInfos.getNom()).isEqualTo("Zemicks");
	assertThat(pInfos.getPrenom()).isEqualTo("Zach");
	assertThat(pInfos.getAdresse()).isEqualTo("892 Downing Ct");
	assertThat(pInfos.getNumeroTel()).isEqualTo("841-874-7512");
}


}


