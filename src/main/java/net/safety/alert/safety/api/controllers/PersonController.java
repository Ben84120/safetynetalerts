package net.safety.alert.safety.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.safety.api.model.PersonModel.Person;
import net.safety.alerts.safety.api.service.PersonService;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class PersonController {
	@Autowired
    private PersonService personService;

    /**
    * Read - Get all employees
    * @return - An Iterable object of Employee full filled
    */
    @GetMapping("/person")
    public Iterable<Person> getPerson() {
        return personService.getPerson();
    }
}
