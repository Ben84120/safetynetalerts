package net.safety.alert.safety.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;
import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.repository.MedicalrecordsRepository;

@Setter
@Getter
@Service
@Transactional
public class MedicalRecordsService {

	@Autowired
	private MedicalrecordsRepository medicalrecordsRepository;

	/**
	*@param Identifiant de la FireStations.
	*
	*@return Le dossier médical par son identifiant.
	*/
	public Optional<MedicalRecords> getMedicalRecordsById(final Long id) {
		return medicalrecordsRepository.findById(id);
	}

	/**
	 * 
	*@return L'ensemble des dossiers médicaux.
	*/
	public List<MedicalRecords> getMedicalRecords() {
		return medicalrecordsRepository.findAll();
	}

	/**
	*@param Identifiant de la FireStations.
	*
	*@return Le dossier médical supprimé.
	*/
	public void deleteMedicalRecords(final Long id) {
		medicalrecordsRepository.deleteById(id);
	}

	/**
	*@param Le dossier médical qui va être sauvegardée.
	*
	*@return Le dossier médical sauvegardé.
	*/
	public MedicalRecords saveMedicalRecords(MedicalRecords medicalrecords) {
		MedicalRecords savedMedicalrecords = medicalrecordsRepository.save(medicalrecords);
		return savedMedicalrecords;
	}
	
	/**
	*@param Le nom de famille.
	*
	*@return La liste des dossiers médicaux pour un nom choisi.
	*/
	public List<MedicalRecords> getPersonByLastName(String lastName) {
		return medicalrecordsRepository.getMedicalRecordsByLastName(lastName);

	}

}
