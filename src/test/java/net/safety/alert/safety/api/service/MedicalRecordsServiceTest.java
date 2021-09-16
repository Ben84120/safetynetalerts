package net.safety.alert.safety.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.model.Person;

@SpringBootTest
public class MedicalRecordsServiceTest {
	@Autowired
	MedicalRecordsService medicalRecordsService;

	@Test
	public void getMedicalRecordsTest() {
		// GIVEN
		// WHEN
		Iterable<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalRecords();
		// THEN
		assertThat(medicalRecords).isNotNull();
		assertThat(medicalRecords).hasSizeBetween(15, 30);
	}

	@Test
	public void getMedicalRecordsByIdTest() {
		// GIVEN
		// WHEN
		Optional<MedicalRecords> medicalRecordsById = medicalRecordsService.getMedicalRecordsById(7L);
		// THEN
		assertThat(medicalRecordsById.isPresent()).isTrue();
		assertThat(medicalRecordsById.get().getLastName()).isEqualTo("Carman");
	}

	@Test
	public void getMedicalRecordsById_Not_Existing() {
		// GIVEN
		// WHEN
		Optional<MedicalRecords> medicalRecordsById = medicalRecordsService.getMedicalRecordsById(53L);
		// THEN
		assertThat(medicalRecordsById.isPresent()).isFalse();
	}

	@Test
	public void saveMedicalRecordsTest() {
		// GIVEN
		// WHEN
		MedicalRecords saveMedicalRecords = new MedicalRecords();
		saveMedicalRecords.setFirstName("Fabien");
		saveMedicalRecords.setLastName("Favoino");
		saveMedicalRecords.setBirthdate("20/02/1986");
		saveMedicalRecords.setMedications("tetracyclaz:650mg");
		saveMedicalRecords.setAllergies("peanut");
		// THEN
		medicalRecordsService.saveMedicalRecords(saveMedicalRecords);
	}

	@Test
	public void deleteMedicalRecordsByIdTest() {
		// GIVEN
		// WHEN
		medicalRecordsService.deleteMedicalRecords(18L);
		Optional<MedicalRecords> medicalRecordsDeleteById = medicalRecordsService.getMedicalRecordsById(18L);
		// THEN
		assertThat(medicalRecordsDeleteById.isPresent()).isFalse();
	}
	
	@Test
	public void getMedicalRecordByLastName_Test() {
		// GIVEN
		// WHEN
		List<MedicalRecords> medicalRecordLastName = medicalRecordsService.getPersonByLastName("Boyd");
		assertThat(medicalRecordLastName).isNotNull();
		assertThat(medicalRecordLastName.get(0).getLastName()).isEqualTo("Boyd");
		// THEN
		
	}
}
