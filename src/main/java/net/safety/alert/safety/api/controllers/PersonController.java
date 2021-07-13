package net.safety.alert.safety.api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.safety.api.model.Person;
import net.safety.alerts.safety.api.service.PersonService;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	@PostMapping("/person")
	public Person createEmployee(@RequestBody Person person) {
		return personService.savePerson(person);
	}

	@GetMapping("/person/{id}")
	public Person getPerson(@PathVariable("id") final Long id) {
		Optional<Person> person = personService.getPerson(id);
		if (person.isPresent()) {
			return person.get();
		} else {
			return null;
		}
	}

	/**
	 * Read - Get all employees
	 * 
	 * @return - An Iterable object of Employee full filled
	 */
	@GetMapping("/person")
	public Iterable<Person> getPerson() {
		return personService.getPerson();
	}

	/**
	 * Delete - Delete an employee
	 * 
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/person/{id}")
	public void deletePerson(@PathVariable("id") final Long id) {
		personService.deletePerson(id);
	}
}
