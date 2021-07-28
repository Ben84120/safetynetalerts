package net.safety.alert.safety.api.service;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.repository.FirestationsRepository;

@Service
public class FireStationsService {

	@Autowired
	private FirestationsRepository firestationsRepository;

	public Optional<FireStations> getFireStations(final Long id) {
		return firestationsRepository.findById(id);
	}

	public Iterable<FireStations> getFireStations() {
		return firestationsRepository.findAll();
	}

	public void deleteFireStations(final FireStations station) {
		firestationsRepository.delete(station);
	}

	public FireStations saveFireStations(FireStations fireStations) {
		FireStations savedFireStations = firestationsRepository.save(fireStations);
		return savedFireStations;
	}

	public void deleteFireStations(final Long id) {
		firestationsRepository.deleteById(id);
		;
	}

	@SuppressWarnings("unused")
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
				entityFireStations.setStation(Integer.parseInt(firestation.get("stations").toString()));
				firestationsRepository.save(entityFireStations);
				/* System.out.println(iterator.next()); */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
