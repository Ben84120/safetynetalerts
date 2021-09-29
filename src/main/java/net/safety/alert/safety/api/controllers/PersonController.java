package net.safety.alert.safety.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.safety.api.model.FloodStations;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonStationCover;
import net.safety.alert.safety.api.model.PersonsInfoWithMedicalRecords;
import net.safety.alert.safety.api.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	@PostMapping("/person")
	public Person createPerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}

	@GetMapping("/person/{id}")
	public Person getPersonById(@PathVariable("id") final Long id) {
		Optional<Person> person = personService.getPersonById(id);
		if (person.isPresent()) {
			return person.get();
		} else {
			return null;
		}
	}

	@GetMapping("/person")
	public Iterable<Person> getPerson() {
		return personService.getPerson();
	}

	@PutMapping("/person/{id}")
	public Person updatePerson(@PathVariable("id") final Long id, @RequestBody Person person) {
		Optional<Person> p = personService.getPersonById(id);
		if (p.isPresent()) {
			Person currentPerson = p.get();

			String email = person.getEmail();
			if (email != null) {
				currentPerson.setEmail(email);
			}

			Integer zip = person.getZip();
			if (zip != null) {
				currentPerson.setZip(zip);
			}

			String city = person.getCity();
			if (city != null) {
				currentPerson.setCity(city);
			}

			String address = person.getAddress();
			if (address != null) {
				currentPerson.setAddress(address);
			}

			String phone = person.getPhone();
			if (phone != null) {
				currentPerson.setPhone(phone);
			}

			personService.savePerson(currentPerson);
			return currentPerson;
		} else {
			return null;
		}
	}

	@DeleteMapping("/person/{id}")
	public void deletePerson(@PathVariable("id") final Long id) {
		personService.deletePerson(id);
	}
	
	@GetMapping("/firestation")
	public PersonStationCover getPersonStationCover(@RequestParam("stationNumber") final Integer stationNumber )  {

		return personService.getPersonStationCover(stationNumber);

		}
	@GetMapping("/communityEmail")
	public List<String> getPersonEmailByCity(@RequestParam("city") final String city) {
		return personService.getPersonEmailByCity(city);
		}
	
	@GetMapping("/phoneAlert")
	public List<String> getPersonPhoneCoverByStation(@RequestParam("firestation") final Integer stationNumber){		
		return personService.getPersonPhoneCoverByStation(stationNumber);
			}
	

	
	@GetMapping("/personInfo")
	public List<PersonsInfoWithMedicalRecords> getPersonsInfoWithMedicalRecord(@RequestParam("firstName")final String firstName, @RequestParam("lastName")final String lastName){

		return personService.getPersonsInfoWithMedicalRecord(firstName, lastName);
		
	}
	
	@GetMapping("/flood/station")
	public List<FloodStations> getPersonInfoByStation(@RequestParam("station")Integer stationNumber){
		return personService.findPersonAndMedicalRecordsByStation(stationNumber);
	}
	
	}
	

