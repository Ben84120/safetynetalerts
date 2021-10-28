package net.safety.alert.safety.api.controllers;

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

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.service.FireStationsService;

@RestController
public class FireStationsController {
	@Autowired
	private FireStationsService firestationsService;

	@PostMapping("/firestations")
	public ResponseEntity<FireStations> createFirestations(@RequestBody FireStations firestations) {
		return ResponseEntity.ok().body(firestationsService.saveFireStations(firestations));
	}

	@GetMapping("/firestations/{id}")
	public ResponseEntity<FireStations> getFirestationsById(@PathVariable("id") final Long id) {
		Optional<FireStations> firestations = firestationsService.getFireStationsById(id);
		if (firestations.isPresent()) {
			return ResponseEntity.ok().body(firestations.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/firestations")
	public ResponseEntity< Iterable<FireStations>> getFirestations() {
		return ResponseEntity.ok().body(firestationsService.getFireStations());
	}

	@PutMapping("/firestations/{id}")
	public ResponseEntity<FireStations> updateFireStations(@PathVariable("id") final Long id, @RequestBody FireStations firestations) {
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
			return ResponseEntity.ok().body(currentFireStations);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/firestations/{id}")
	public ResponseEntity<?> deleteFireStations(@PathVariable("id") final Long id) {
		firestationsService.deleteFireStationsById(id);
		return ResponseEntity.ok().build();
	}

}
