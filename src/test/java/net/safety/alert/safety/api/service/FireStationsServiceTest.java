package net.safety.alert.safety.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.repository.FirestationsRepository;

@Data
@Service
@Transactional
public class FireStationsServiceTest {

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

	public void deleteFireStations(Long id) {
		firestationsRepository.deleteById(id);
		;
	}


}
