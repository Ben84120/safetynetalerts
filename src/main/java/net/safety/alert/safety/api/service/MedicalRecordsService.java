package net.safety.alert.safety.api.service;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.repository.MedicalrecordsRepository;


@Service
public class MedicalRecordsService {
	
	@Autowired
	private MedicalrecordsRepository medicalrecordsRepository;
	
	public Optional<MedicalRecords> getMedicalRecords(final Long id) {
        return medicalrecordsRepository.findById(id);
    }
	
	public Iterable<MedicalRecords> getMedicalRecords() {
        return medicalrecordsRepository.findAll(); 
    }

    public void deleteMedicalRecords(final Long id) {
        medicalrecordsRepository.deleteById(id);
    }

    public MedicalRecords saveMedicalRecords(MedicalRecords medicalrecords) {
    	MedicalRecords savedMedicalrecords = medicalrecordsRepository.save(medicalrecords); 
        return savedMedicalrecords;
    }
    
    @SuppressWarnings("unused")
	private void parseMedicalRecords() {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\bvach\\git\\safetynetalerts\\resources\\data.json"));

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
				entityMedicalRecords.setLastName(medicalrecord.get("lasttName").toString());
				entityMedicalRecords.setBirthdate(Integer.parseInt(medicalrecord.get("birthdate").toString()));
				entityMedicalRecords.setAllergies(medicalrecord.get("allergies").toString());
				entityMedicalRecords.setMedications(medicalrecord.get("medications").toString());
				/* System.out.println(iterator.next()); */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

