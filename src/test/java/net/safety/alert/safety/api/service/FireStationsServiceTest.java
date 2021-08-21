package net.safety.alert.safety.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.model.Person;

@SpringBootTest
public class FireStationsServiceTest {
	@Autowired
	FireStationsService fireStationsService;

	@Test
	public void getFireStationsTest() {
		Iterable<FireStations> fireStations = fireStationsService.getFireStations();
		assertThat(fireStations).isNotNull();
		assertThat(fireStations).hasSizeBetween(10, 30);
	  
	}
	
	@Test
	public void getFireStationsByIdTest() {
		Optional<FireStations> fireStationsById = fireStationsService.getFireStationsById(11L);
		assertThat(fireStationsById.isPresent()).isTrue();
		assertThat(fireStationsById.get().getStation()).isEqualTo(1);
	}
	
	@Test
	public void getFireStationsById_Not_Existing() {
		Optional<FireStations> fireStationsById = fireStationsService.getFireStationsById(51L);
		assertThat(fireStationsById.isPresent()).isFalse();
	}
	
	@Test
	public void deleteFireStationsByIdTest() {
		fireStationsService.deleteFireStationsById(6L);
		Optional<FireStations> fireStationsDeleteById = fireStationsService.getFireStationsById(6L);
		assertThat(fireStationsDeleteById.isPresent()).isFalse();	
	}
	
	@Test
	public void saveFireStationsTest() {
		FireStations saveFireStations = new FireStations();
		saveFireStations.setAddress("Rue des bouchers");
		saveFireStations.setStation(6);
		fireStationsService.saveFireStations(saveFireStations);
	}
	
	
}
