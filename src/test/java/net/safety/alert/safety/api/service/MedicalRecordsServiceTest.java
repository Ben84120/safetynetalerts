package net.safety.alert.safety.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.model.MedicalRecords;

@SpringBootTest
public class MedicalRecordsServiceTest {
	@Autowired
	MedicalRecordsService medicalRecordsService;

	@Test
	public void getMedicalRecordsTest() {
		Iterable<MedicalRecords> medicalRecords = medicalRecordsService.getMedicalRecords();
		assertThat(medicalRecords).isNotNull();
		assertThat(medicalRecords).hasSizeBetween(15, 30);
	}
	
	@Test
	public void getMedicalRecordsByIdTest() {
		Optional<MedicalRecords> medicalRecordsById = medicalRecordsService.getMedicalRecordsById(7L);
		assertThat(medicalRecordsById.isPresent()).isTrue();
		assertThat(medicalRecordsById.get().getLastName()).isEqualTo("Carman");
	}
	
	@Test
	public void getMedicalRecordsById_Not_Existing() {
		Optional<MedicalRecords> medicalRecordsById = medicalRecordsService.getMedicalRecordsById(53L);
		assertThat(medicalRecordsById.isPresent()).isFalse();
	}
	
	@Test
	public void saveMedicalRecordsTest() {
		MedicalRecords saveMedicalRecords = new MedicalRecords();
		saveMedicalRecords.setFirstName("Fabien");
		saveMedicalRecords.setLastName("Favoino");
		saveMedicalRecords.setBirthdate("20/02/1986");
		saveMedicalRecords.setMedications("tetracyclaz:650mg");
		saveMedicalRecords.setAllergies("peanut");
		medicalRecordsService.saveMedicalRecords(saveMedicalRecords);
	}
	
	@Test
	public void deleteMedicalRecordsByIdTest() {
		medicalRecordsService.deleteMedicalRecords(18L);
		Optional<MedicalRecords> medicalRecordsDeleteById = medicalRecordsService.getMedicalRecordsById(18L);
		assertThat(medicalRecordsDeleteById.isPresent()).isFalse();	
	}
}
