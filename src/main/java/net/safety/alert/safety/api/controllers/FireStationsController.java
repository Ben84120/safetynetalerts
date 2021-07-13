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
import net.safety.alert.safety.api.model.Person;
import net.safety.alerts.safety.api.service.FireStationsService;



	
	@RestController
	public class FireStationsController {
		@Autowired
	    private FireStationsService firestationsService;

		@PostMapping("/firestations")
		public FireStations createFirestations(@RequestBody FireStations firestations) {
			return firestationsService.saveFireStations(firestations);
		}
		
		@GetMapping("/firestations/{id}")
		public FireStations getFirestations(@PathVariable("id") final Long id) {
			Optional<FireStations> firestations = firestationsService.getFireStations(id);
			if (firestations.isPresent()) {
				return firestations.get();
			} else {
				return null;
			}
		}
		
		/**
	    * Read - Get all employees
	    * @return - An Iterable object of Employee full filled
	    */
	    @GetMapping("/firestations")
	    public Iterable<FireStations> getFirestations() {
	        return firestationsService.getFireStations();
	    }
	    
	    /**
		 * Delete - Delete an employee
		 * 
		 * @param id - The id of the employee to delete
		 */
		@DeleteMapping("/firestations/{id}")
		public void deleteFireStations(@PathVariable("id") final Long id) {
			firestationsService.deleteFireStations(id);
		}

	}
