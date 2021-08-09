package net.safety.alert.safety.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.model.Person;

@SpringBootTest
public class FireStationsRepositoryTest {
	@Autowired
	FirestationsRepository fireStationsRepository;
	@Test
	public void getFireStationsTest() {
		//GIVEN
		//WHEN
		Iterable<FireStations> firestations = fireStationsRepository.findAll() ;
		//THEN
		assertThat(firestations).isNotNull();
		assertThat(firestations).hasSizeBetween(1, 20);
		assertThat(firestations).hasSize(13);
		
	}
	
	@Test
	public void getFireStationsTestByID() {
		//GIVEN
		//WHEN
		Optional<FireStations> firestations = fireStationsRepository.findById(1L) ;
		//THEN
		assertThat(firestations.get()).isNotNull();
		assertThat(firestations.get().getAddress()).isEqualTo("1509 Culver St");
		assertThat(firestations.get().getStation()).isEqualTo("3");

	}
	
	@Test
	public void getFireStationsById_NotExistingTest() {
		//GIVEN
		//WHEN
		Optional<FireStations> firestations = fireStationsRepository.findById(35L);
		//THEN
		assertThat(firestations.isPresent()).isFalse();
		
		
		
	}
	
	
	
	
	
}
