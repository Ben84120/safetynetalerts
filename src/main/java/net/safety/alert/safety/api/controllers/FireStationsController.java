package net.safety.alert.safety.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alerts.safety.api.service.FireStationsService;



	
	@RestController
	public class FireStationsController {
		@Autowired
	    private FireStationsService firestationsService;

	    /**
	    * Read - Get all employees
	    * @return - An Iterable object of Employee full filled
	    */
	    @GetMapping("/firestations")
	    public Iterable<FireStations> getFirestations() {
	        return firestationsService.getFireStations();
	    }

}

