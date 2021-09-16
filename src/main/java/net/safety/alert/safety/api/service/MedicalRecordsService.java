package net.safety.alert.safety.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;
import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.repository.MedicalrecordsRepository;

@Setter
@Getter
@Service
@Transactional
public class MedicalRecordsService {

	@Autowired
	private MedicalrecordsRepository medicalrecordsRepository;

	public Optional<MedicalRecords> getMedicalRecordsById(final Long id) {
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
	
	public List<MedicalRecords> getPersonByLastName(String lastName) {
		return medicalrecordsRepository.getMedicalRecordsByLastName(lastName);
		
	}

}
