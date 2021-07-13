package net.safety.alert.safety.api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alerts.safety.api.service.MedicalRecordsService;


	@RestController
	public class MedicalRecordsController {
		@Autowired
	    private MedicalRecordsService medicalrecordsService;

		@GetMapping("/medicalrecords/{id}")
		public MedicalRecords getMedicalRecords(@PathVariable("id") final Long id) {
			Optional<MedicalRecords> medicalrecords = medicalrecordsService.getMedicalRecords(id);
			if (medicalrecords.isPresent()) {
				return medicalrecords.get();
			} else {
				return null;
			}
		}
		
		
		/**
	    * Read - Get all employees
	    * @return - An Iterable object of Employee full filled
	    */
	    @GetMapping("/medicalrecords")
	    public Iterable<MedicalRecords> getMedicalRecords() {
	        return medicalrecordsService.getMedicalRecords();
	    }
	    
	    /**
		 * Delete - Delete an employee
		 * 
		 * @param id - The id of the employee to delete
		 */
		@DeleteMapping("/medicalrecords/{id}")
		public void deleteMedicalRecords(@PathVariable("id") final Long id) {
			medicalrecordsService.deleteMedicalRecords(id);
		}
	}


