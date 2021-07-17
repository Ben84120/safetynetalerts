package net.safety.alert.safety.api.service;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.repository.PersonRepository;

@Data
@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Optional<Person> getPerson(final Long id) {
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

	@SuppressWarnings("unused")
	private void parsePerson() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\bvach\\git\\safetynetalerts\\resources\\data.json"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
 
			// A JSON array. JSONObject supports java.util.List interface.
			JSONArray persons = (JSONArray) jsonObject.get("persons");
 
			// An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
			// Iterators differ from enumerations in two ways:
			// 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
			// 2. Method names have been improved.
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = persons.iterator();
			while (iterator.hasNext()) {
				JSONObject person = iterator.next();
				Person entityPerson = new Person();
				entityPerson.setId(Long.parseLong(person.get("id").toString()));
				entityPerson.setFirstName(person.get("firstName").toString());
				entityPerson.setZip(Integer.parseInt(person.get("zip").toString()));
				entityPerson.setLastName(person.get("lastName").toString());
				entityPerson.setEmail(person.get("email").toString());
				entityPerson.setAddress(person.get("address").toString());
				entityPerson.setCity(person.get("city").toString());
				entityPerson.setPhone(Integer.parseInt(person.get("phone").toString()));
				personRepository.save(entityPerson);
				/*System.out.println(iterator.next());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
