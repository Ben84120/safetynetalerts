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
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.service.MedicalRecordsService;

@RestController
public class MedicalRecordsController {
	@Autowired
	private MedicalRecordsService medicalrecordsService;

	@PostMapping("/medicalrecords")
	public ResponseEntity<MedicalRecords> createMedicalRecords(@RequestBody MedicalRecords medicalRecords) {
		return ResponseEntity.ok().body(medicalrecordsService.saveMedicalRecords(medicalRecords));
	}

	@GetMapping("/medicalrecords/{id}")
	public ResponseEntity<MedicalRecords> getMedicalRecordsById(@PathVariable("id") final Long id) {
		Optional<MedicalRecords> medicalrecords = medicalrecordsService.getMedicalRecordsById(id);
		if (medicalrecords.isPresent()) {
			return ResponseEntity.ok().body(medicalrecords.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/medicalrecords")
	public ResponseEntity<List<MedicalRecords>> getMedicalRecords() {
		
		return ResponseEntity.ok().body(medicalrecordsService.getMedicalRecords());
	}

	@PutMapping("/medicalrecords/{id}")
	public ResponseEntity<MedicalRecords> updateMedicalRecords(@PathVariable("id") final Long id,
			@RequestBody MedicalRecords medicalrecords) {
		Optional<MedicalRecords> m = medicalrecordsService.getMedicalRecordsById(id);
		if (m.isPresent()) {
			MedicalRecords currentMedicalRecords = m.get();

			String birthdate = medicalrecords.getBirthdate();
			if (birthdate != null) {
				currentMedicalRecords.setBirthdate(birthdate);
			}

			String medications = medicalrecords.getMedications();
			if (medications != null) {
				currentMedicalRecords.setMedications(medications);
			}

			String allergies = medicalrecords.getAllergies();
			if (allergies != null) {
				currentMedicalRecords.setAllergies(allergies);
			}

			medicalrecordsService.saveMedicalRecords(currentMedicalRecords);
			return ResponseEntity.ok().body(currentMedicalRecords);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/medicalrecords/{id}")
	public ResponseEntity<?> deleteMedicalRecords(@PathVariable("id") final Long id) {
		medicalrecordsService.deleteMedicalRecords(id);
		return ResponseEntity.ok().build();
	}
}
