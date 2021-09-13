package net.safety.alert.safety.api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.service.FireStationsService;

@RestController
public class FireStationsController {
	@Autowired
	private FireStationsService firestationsService;

	@PostMapping("/firestations")
	public FireStations createFirestations(@RequestBody FireStations firestations) {
		return firestationsService.saveFireStations(firestations);
	}

	@GetMapping("/firestations/{id}")
	public FireStations getFirestationsById(@PathVariable("id") final Long id) {
		Optional<FireStations> firestations = firestationsService.getFireStationsById(id);
		if (firestations.isPresent()) {
			return firestations.get();
		} else {
			return null;
		}
	}

	
	@GetMapping("/firestations")
	public Iterable<FireStations> getFirestations() {
		return firestationsService.getFireStations();
	}

	@PutMapping("/firestations/{id}")
	public FireStations updateFireStations(@PathVariable("id") final Long id, @RequestBody FireStations firestations) {
		Optional<FireStations> f = firestationsService.getFireStationsById(id);
		if (f.isPresent()) {
			FireStations currentFireStations = f.get();

			String address = firestations.getAddress();
			if (address != null) {
				currentFireStations.setAddress(address);
			}

			Integer station = firestations.getStation();
			if (station != null) {
				currentFireStations.setStation(station);
			}

			firestationsService.saveFireStations(currentFireStations);
			return currentFireStations;
		} else {
			return null;
		}
	}

	@DeleteMapping("/firestations/{id}")
	public void deleteFireStations(@PathVariable("id") final Long id) {
		firestationsService.deleteFireStationsById(id);
	}
	

}
