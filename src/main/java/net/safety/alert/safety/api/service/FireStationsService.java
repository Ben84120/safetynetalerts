package net.safety.alert.safety.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.Setter;
import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.repository.FirestationsRepository;
import net.safety.alert.safety.api.repository.PersonRepository;

@Setter
@Getter
@Service
@Transactional
public class FireStationsService {

	@Autowired
	private FirestationsRepository firestationsRepository;
	@Autowired
	private PersonRepository personRepository;

	/**
	*@param Identifiant de la FireStations.
	*
	*@return La station par son identifiant.
	*/
	public Optional<FireStations> getFireStationsById(final Long id) {
		return firestationsRepository.findById(id);
	}
	
	/**
	*@return L'ensemble des FireStations.
	*
	*/
	public Iterable<FireStations> getFireStations() {
		return firestationsRepository.findAll();
	}

	/**
	*@param La FireStations qui va être sauvegardée.
	*
	*@return La FireStation sauvegardée.
	*/
	public FireStations saveFireStations(FireStations fireStations) {
		FireStations savedFireStations = firestationsRepository.save(fireStations);
		return savedFireStations;
	}
	
	/**
	*@param Identifiant de la FireStations.
	*
	*@return La FireStation supprimée par son ID.
	*/
	public void deleteFireStationsById(final Long id) {
		firestationsRepository.deleteById(id);

	}

}
