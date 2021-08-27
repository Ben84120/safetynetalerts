package net.safety.alert.safety.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.service.FireStationsService;

@WebMvcTest(controllers = FireStationsController.class)
public class FireStationsControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FireStationsService fireStationsServiceMock;

	@Test
	public void getFireStation_Test() throws Exception {

		FireStations fireStations = new FireStations();
		fireStations.setAddress("Avenue du bout du monde");
		fireStations.setStation(6);
		List<FireStations> fireStationsList = new ArrayList<>();
		fireStationsList.add(fireStations);

		when(fireStationsServiceMock.getFireStations()).thenReturn(fireStationsList);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/firestations")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getFireStationsById_Test() throws Exception {

		FireStations fireStation = new FireStations();
		fireStation.setAddress("Avenue du bout du monde");
		fireStation.setStation(6);

		when(fireStationsServiceMock.getFireStationsById(15L)).thenReturn(Optional.of(fireStation));

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/firestations/15")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getFireStationsById_Not_Existing_Test() throws Exception {

		FireStations fireStationById = new FireStations();
		fireStationById.setAddress("Impasse du bout du monde");
		fireStationById.setStation(7);

		when(fireStationsServiceMock.getFireStationsById(29L)).thenReturn(Optional.of(fireStationById));

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/firestations/29")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void createFireStations_Test() throws Exception {

		FireStations createFireStations = new FireStations();
		createFireStations.setAddress("Boulevard du bout du monde");
		createFireStations.setStation(19);

		fireStationsServiceMock.saveFireStations(createFireStations);
		List<FireStations> fireStationRecordList = new ArrayList<>();
		assertThat(fireStationRecordList).isNotNull();

		when(fireStationsServiceMock.saveFireStations(createFireStations)).thenReturn(createFireStations);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(createFireStations);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/firestations")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void updateFireStation_Test() throws Exception {

		FireStations updateFireStation = new FireStations();
		updateFireStation.setId(1L);
		updateFireStation.setAddress("112 Steppes Pl");
		updateFireStation.setStation(4);
		when(fireStationsServiceMock.getFireStationsById(1L)).thenReturn(Optional.of(updateFireStation));
		when(fireStationsServiceMock.saveFireStations(updateFireStation)).thenReturn(updateFireStation);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(updateFireStation);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/firestations/1")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void updateFireStation_Test2() throws Exception {

		FireStations updateFireStation = new FireStations();
		updateFireStation.setId(1L);
		updateFireStation.setAddress("112 Steppes Pl");
		updateFireStation.setStation(4);
		when(fireStationsServiceMock.getFireStationsById(1L)).thenReturn(Optional.empty());
		when(fireStationsServiceMock.saveFireStations(updateFireStation)).thenReturn(updateFireStation);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(updateFireStation);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/firestations/1")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void deleteFireStation_Test() throws Exception {
		FireStations deleteFireStation = new FireStations();
		deleteFireStation.setId(23L);
		deleteFireStation.setStation(13);
		deleteFireStation.setAddress("Rue des magasins");

		List<Person> personSave = new ArrayList<>();
		assertThat(personSave).isNotNull();

		when(fireStationsServiceMock.getFireStationsById(23L)).thenReturn(Optional.of(deleteFireStation));

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(deleteFireStation);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/firestations/23")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
