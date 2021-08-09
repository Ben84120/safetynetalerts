package net.safety.alert.safety.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.MedicalRecords;

@SpringBootTest
public class MedicalRecordsRepositoryTest {
	@Autowired
	MedicalrecordsRepository medicalRecordsRepository;
	@Test
	public void getMedicalRecordsTest() {
		
		//GIVEN
				//WHEN
				Iterable<MedicalRecords> medicalRecords = medicalRecordsRepository.findAll();
				//THEN
				assertThat(medicalRecords).isNotNull();
				assertThat(medicalRecords).hasSizeBetween(1, 50);
				assertThat(medicalRecords).hasSize(23);
				
		
	}
	
	@Test
	public void getMedicalRecordsByIdTest() {
		//GIVEN
		//WHEN
		Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findById(1L);
		//THEN
		assertThat(medicalRecords.get()).isNotNull();
		assertThat(medicalRecords.get().getFirstName()).isEqualTo("John");
		
	}
	
	@Test
	public void getMedicalRecordsByIdAndMedications() {
		//GIVEN
		//WHEN
		Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findById(23L);
		//THEN
		assertThat(medicalRecords.get()).isNotNull();
		assertThat(medicalRecords.get().getBirthdate()).isEqualTo("08/06/1945");
		assertThat(medicalRecords.isPresent()).isTrue();
	}
	
	@Test
	public void getMedicalRecordsById_NotExistingTest() {
		//GIVEN
		//WHEN
		Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findById(75L);
		//THEN
		assertThat(medicalRecords.isPresent()).isFalse();	
		
		}
	
}

	
