package net.safety.alert.safety.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
	public void getMedicalRecords_Test() {

		// GIVEN
		// WHEN
		Iterable<MedicalRecords> medicalRecords = medicalRecordsRepository.findAll();
		// THEN
		assertThat(medicalRecords).isNotNull();
		assertThat(medicalRecords).hasSizeBetween(1, 50);
		assertThat(medicalRecords).hasSize(23);

	}

	@Test
	public void getMedicalRecordsByIdTest() {
		// GIVEN
		// WHEN
		Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findById(1L);
		// THEN
		assertThat(medicalRecords.get()).isNotNull();
		assertThat(medicalRecords.get().getFirstName()).isEqualTo("John");
		assertThat(medicalRecords.get().getLastName()).isEqualTo("Boyd");
		assertThat(medicalRecords.get().getBirthdate()).isEqualTo("03/06/1984");
		assertThat(medicalRecords.get().getMedications()).contains("aznol:350mg", "hydrapermazol:100mg");
		assertThat(medicalRecords.get().getAllergies()).contains("nillacilan");

	}

	@Test
	public void getMedicalRecordsByIdAndMedications_Test() {
		// GIVEN
		// WHEN
		Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findById(23L);
		// THEN
		assertThat(medicalRecords.get()).isNotNull();
		assertThat(medicalRecords.get().getBirthdate()).isEqualTo("08/06/1945");
		assertThat(medicalRecords.isPresent()).isTrue();
	}

	@Test
	public void getMedicalRecordsById_NotExisting_Test() {
		// GIVEN
		// WHEN
		Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findById(75L);
		// THEN
		assertThat(medicalRecords.isPresent()).isFalse();

	}

	@Test
	public void deleteMedicalRecordsById_Test() {
		// GIVEN
		// WHEN
		medicalRecordsRepository.deleteById(5L);
		// THEN
		Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findById(5L);
		assertThat(medicalRecords.isPresent()).isFalse();

	}

	@Test
	public void saveMedicalRecords_Test() {
		// GIVEN
		// WHEN
		MedicalRecords saveMedicalRecords = new MedicalRecords();
		saveMedicalRecords.setFirstName("Ben");
		saveMedicalRecords.setLastName("Vacher");
		saveMedicalRecords.setBirthdate("03/02/1985");
		saveMedicalRecords.setMedications("aznol:350mg");
		saveMedicalRecords.setAllergies("shellfish");
		medicalRecordsRepository.save(saveMedicalRecords);
		// THEN
		List<MedicalRecords> medicalRecordsByLastName = medicalRecordsRepository.getMedicalRecordsByLastName("Vacher");
		assertThat(medicalRecordsByLastName).isNotNull();
		assertThat(medicalRecordsByLastName.get(0).getLastName()).isEqualTo("Vacher");

	}

	@Test
	public void getMedicalRecordsByLastName_Test() {
		// GIVEN
		// WHEN
		Iterable<MedicalRecords> medicalRecords = medicalRecordsRepository.findAll();
		// THEN
		assertThat(medicalRecords).isNotNull();
	}
	
	@Test
	public void getMedicalRecordsByFirstAndLastName_Test() {
		// GIVEN
		// WHEN
		MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName("John", "Boyd");
		// THEN
		assertThat(medicalRecords).isNotNull();
		assertThat(medicalRecords.getFirstName()).isEqualTo("John");
		assertThat(medicalRecords.getLastName()).isEqualTo("Boyd");
}
}
