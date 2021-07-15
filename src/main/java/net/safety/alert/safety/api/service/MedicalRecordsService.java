package net.safety.alert.safety.api.service;

import java.util.Optional;

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

}

