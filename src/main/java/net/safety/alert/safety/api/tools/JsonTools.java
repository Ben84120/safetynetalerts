package net.safety.alert.safety.api.tools;

import java.io.FileReader;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.service.FireStationsService;
import net.safety.alert.safety.api.service.MedicalRecordsService;
import net.safety.alert.safety.api.service.PersonService;

@Service
@Transactional
public class JsonTools  {
	
	@Autowired
	private PersonService personService;
	
	
	
	@PostConstruct
	public void parsePerson() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\bvach\\git\\safetynetalerts\\src\\main\\resources\\data.json"));
 
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
				/*entityPerson.setId(Long.parseLong(person.get("id").toString()));*/  
				entityPerson.setFirstName(person.get("firstName").toString());
				entityPerson.setZip(Integer.parseInt(person.get("zip").toString()));
				entityPerson.setLastName(person.get("lastName").toString());
				entityPerson.setEmail(person.get("email").toString());
				entityPerson.setAddress(person.get("address").toString());
				entityPerson.setCity(person.get("city").toString());
				entityPerson.setPhone(person.get("phone").toString());
				personService.savePerson(entityPerson);
				/*System.out.println(iterator.next());*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Autowired
	private FireStationsService fireStationsService;
	
	@PostConstruct
	private void parseFireStations() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\bvach\\git\\safetynetalerts\\src\\main\\resources\\data.json"));

			// A JSON object. Key value pairs are unordered. JSONObject supports
			// java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;

			// A JSON array. JSONObject supports java.util.List interface.
			JSONArray firestations = (JSONArray) jsonObject.get("firestations");

			// An iterator over a collection. Iterator takes the place of Enumeration in the
			// Java Collections Framework.
			// Iterators differ from enumerations in two ways:
			// 1. Iterators allow the caller to remove elements from the underlying
			// collection during the iteration with well-defined semantics.
			// 2. Method names have been improved.
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = firestations.iterator();
			while (iterator.hasNext()) {
				JSONObject firestation = iterator.next();
				FireStations entityFireStations = new FireStations();
				entityFireStations.setId(Long.parseLong(firestation.get("id").toString()));
				entityFireStations.setAddress(firestation.get("address").toString());
				entityFireStations.setStation(Integer.parseInt(firestation.get("station").toString()));
				fireStationsService.saveFireStations(entityFireStations);
				/* System.out.println(iterator.next()); */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Autowired
	private MedicalRecordsService medicalRecordsService;
	
	@PostConstruct
	private void parseMedicalRecords() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\bvach\\git\\safetynetalerts\\src\\main\\resources\\data.json"));

			// A JSON object. Key value pairs are unordered. JSONObject supports
			// java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;

			// A JSON array. JSONObject supports java.util.List interface.
			JSONArray medicalrecords = (JSONArray) jsonObject.get("medicalrecords");

			// An iterator over a collection. Iterator takes the place of Enumeration in the
			// Java Collections Framework.
			// Iterators differ from enumerations in two ways:
			// 1. Iterators allow the caller to remove elements from the underlying
			// collection during the iteration with well-defined semantics.
			// 2. Method names have been improved.
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = medicalrecords.iterator();
			while (iterator.hasNext()) {
				JSONObject medicalrecord = iterator.next();
				MedicalRecords entityMedicalRecords = new MedicalRecords();
				entityMedicalRecords.setId(Long.parseLong(medicalrecord.get("id").toString()));
				entityMedicalRecords.setFirstName(medicalrecord.get("firstName").toString());
				entityMedicalRecords.setLastName(medicalrecord.get("lastName").toString());
				entityMedicalRecords.setBirthdate(medicalrecord.get("birthdate").toString());
				entityMedicalRecords.setAllergies(medicalrecord.get("allergies").toString());
				entityMedicalRecords.setMedications(medicalrecord.get("medications").toString());
				medicalRecordsService.saveMedicalRecords(entityMedicalRecords);
				
				/* System.out.println(iterator.next()); */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
