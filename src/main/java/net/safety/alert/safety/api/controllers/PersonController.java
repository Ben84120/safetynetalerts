package net.safety.alert.safety.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.safety.api.model.ChildAlert;
import net.safety.alert.safety.api.model.FirePersons;
import net.safety.alert.safety.api.model.Foster;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonStationCover;
import net.safety.alert.safety.api.model.PersonsInfoWithMedicalRecords;
import net.safety.alert.safety.api.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	@PostMapping("/person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		return ResponseEntity.ok().body(personService.savePerson(person));
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable("id") final Long id) {
		Optional<Person> person = personService.getPersonById(id);
		if (person.isPresent()) {
			return ResponseEntity.ok().body(person.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/person")
	public ResponseEntity<Iterable<Person>>  getPerson() {
		return ResponseEntity.ok().body(personService.getPerson());
	}

	@PutMapping("/person/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable("id") final Long id, @RequestBody Person person) {
		Optional<Person> p = personService.updatePerson(id, person);
		if (p.isPresent()) {
			return ResponseEntity.ok().body(person);
			} else {
				return ResponseEntity.notFound().build();
			}
			
	}

	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable("id") final Long id) {
		personService.deletePerson(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/firestation")
	public ResponseEntity<PersonStationCover> getPersonStationCover(@RequestParam("stationNumber") final Integer stationNumber) {

		return ResponseEntity.ok().body(personService.getPersonStationCover(stationNumber));

	}

	@GetMapping("/communityEmail")
	public ResponseEntity<List<String>> getPersonEmailByCity(@RequestParam("city") final String city) {
		return ResponseEntity.ok().body(personService.getPersonEmailByCity(city));
	}

	@GetMapping("/phoneAlert")
	public ResponseEntity<List<String>> getPersonPhoneCoverByStation(@RequestParam("firestation") final Integer stationNumber) {
		return ResponseEntity.ok().body(personService.getPersonPhoneCoverByStation(stationNumber));
	}

	@GetMapping("/personInfo")
	public ResponseEntity<List<PersonsInfoWithMedicalRecords>> getPersonsInfoWithMedicalRecord(
			@RequestParam("lastName") final String lastName) {

		return ResponseEntity.ok().body(personService.getPersonsInfoWithMedicalRecord(lastName));

	}

	@GetMapping("/flood/station")
	public List<Foster> getPersonInfoByStation(@RequestParam("station") Integer stationNumber) {
		List<Foster> fosters = personService.findPersonAndMedicalRecordsByStation(stationNumber);
		return fosters;
	}

	@GetMapping("/fire")
	public ResponseEntity<List<FirePersons>> getFireAddress(@RequestParam("address") String address) {
		List<FirePersons> fire = personService.getFireAddress(address);
		return ResponseEntity.ok().body(fire);
	}

	@GetMapping("/childAlert")
	public ResponseEntity<List<ChildAlert>> getChildAlert(@RequestParam("address") final String address) {

		return ResponseEntity.ok().body(personService.getChildAlert(address));

	}

}
