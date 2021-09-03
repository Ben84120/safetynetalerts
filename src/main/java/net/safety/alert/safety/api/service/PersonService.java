package net.safety.alert.safety.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonInformations;
import net.safety.alert.safety.api.repository.PersonRepository;

@Setter
@Getter
@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Optional<Person> getPersonById(final Long id) {
		return personRepository.findById(id);
	}

	public Iterable<Person> getPerson() {
		return personRepository.findAll();
	}

	public void deletePerson(final Long id) {
		personRepository.deleteById(id);
	}

	public Person savePerson(Person person) {
		Person savedPerson = personRepository.save(person);
		return savedPerson;
	}
	
	public List<PersonInformations> getPersonStationCover(final Integer stationNumber) {
		return personRepository.getPersonInformations(stationNumber);
	}

}
