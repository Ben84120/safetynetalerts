package net.safety.alert.safety.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.safety.api.service.MedicalRecordsService;

@RestController
public class MedicalRecordsControllerTest {
	@Autowired
	private MedicalRecordsService medicalrecordsService;

	
}
