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

import net.safety.alert.safety.api.model.ChildAlert;
import net.safety.alert.safety.api.model.FirePersons;
import net.safety.alert.safety.api.model.Foster;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonStationCover;
import net.safety.alert.safety.api.service.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personServiceMock;

	@Test
	public void getPerson_Test() throws Exception {

		Person person = new Person();
		person.setLastName("Evelyne");
		person.setFirstName("Morreau");
		person.setAddress("Avenue du bout du monde");
		person.setCity("Paris");
		person.setPhone("852-865-2365");
		person.setEmail("e.morreau@outlook.fr");
		person.setZip(75016);
		List<Person> personList = new ArrayList<>();
		personList.add(person);

		when(personServiceMock.getPerson()).thenReturn(personList);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/person")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void createPersons_Test() throws Exception {

		Person person = new Person();
		person.setLastName("Paul");
		person.setFirstName("Desfès");
		person.setAddress("Avenue du bout du monde");
		person.setCity("Paris");
		person.setPhone("852-865-4589");
		person.setEmail("p.defes@outlook.fr");
		person.setZip(75016);
		personServiceMock.savePerson(person);
		// THEN
		List<Person> personSave = new ArrayList<>();
		assertThat(personSave).isNotNull();

		when(personServiceMock.savePerson(person)).thenReturn(person);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(person);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/person")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void updatePerson_Test() throws Exception {

		Person updatePerson = new Person();
		updatePerson.setAddress("Boulevard du bout de la table");
		updatePerson.setFirstName("Benjamin");
		updatePerson.setLastName("Orqui");
		updatePerson.setAddress("Rue des primeurs fous");
		updatePerson.setCity("Vitrolles");
		updatePerson.setZip(13700);
		updatePerson.setPhone("874-895-5647");
		updatePerson.setEmail("b.orqui@next.fr");
		when(personServiceMock.getPersonById(1L)).thenReturn(Optional.of(updatePerson));
		when(personServiceMock.savePerson(updatePerson)).thenReturn(updatePerson);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(updatePerson);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/person/1")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void updatePerson_Test2() throws Exception {

		Person updatePerson = new Person();
		updatePerson.setAddress("Boulevard du bout de la table");
		updatePerson.setFirstName("Benjamin");
		updatePerson.setLastName("Orqui");
		updatePerson.setAddress("Rue des primeurs fous");
		updatePerson.setCity("Vitrolles");
		updatePerson.setZip(13700);
		updatePerson.setPhone("874-895-5647");
		updatePerson.setEmail("b.orqui@next.fr");
		when(personServiceMock.getPersonById(1L)).thenReturn(Optional.empty());
		when(personServiceMock.savePerson(updatePerson)).thenReturn(updatePerson);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(updatePerson);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/person/1")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void getPersonById_Test() throws Exception {

		Person person = new Person();
		person.setAddress("Boulevard du bout du monde");
		person.setFirstName("Benjamin");
		person.setLastName("Fabre");
		person.setAddress("Rue des primeurs");
		person.setCity("Vitrolles");
		person.setZip(13700);
		person.setPhone("874-895-3125");
		person.setEmail("b.fabre@outlook.fr");

		when(personServiceMock.getPersonById(43L)).thenReturn(Optional.of(person));

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/person/43")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getPersonById_Test2() throws Exception {

		Person person = new Person();
		person.setAddress("Boulevard du bout du monde");
		person.setFirstName("Benjamin");
		person.setLastName("Fabre");
		person.setAddress("Rue des primeurs");
		person.setCity("Vitrolles");
		person.setZip(13700);
		person.setPhone("874-895-3125");
		person.setEmail("b.fabre@outlook.fr");

		when(personServiceMock.getPersonById(43L)).thenReturn(Optional.of(person));

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/person/44")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void deletePerson_Test() throws Exception {
		Person deletePerson = new Person();
		deletePerson.setId(27L);
		deletePerson.setFirstName("Maximilien");
		deletePerson.setLastName("Ennelin");
		deletePerson.setAddress("Rue des pharmaciens");
		deletePerson.setCity("Vitrolle");
		deletePerson.setZip(13700);
		deletePerson.setPhone("874-895-5465");
		deletePerson.setEmail("m.ennelin@outlook.fr");
		personServiceMock.deletePerson(27L);

		List<Person> personSave = new ArrayList<>();
		assertThat(personSave).isNotNull();

		when(personServiceMock.getPersonById(27L)).thenReturn(Optional.of(deletePerson));

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(deletePerson);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/person/27")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getPersonStationCover_Test() throws Exception {
		PersonStationCover personCover = new PersonStationCover();

		personCover.setNombreAdultes(15);
		personCover.setNombreMineurs(6);
		personServiceMock.getPersonStationCover(28);

		when(personServiceMock.getPersonStationCover(28)).thenReturn(personCover);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(personCover);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/firestation?stationNumber=28")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getChildAlert_Test() throws Exception {

		List<ChildAlert> lCA = personServiceMock.getChildAlert("1509 Culver St");

		when(personServiceMock.getChildAlert("1509 Culver St")).thenReturn(lCA);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(lCA);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/childAlert?address=1509 Culver St")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getPersonPhoneCoverByStation_Test() throws Exception {

		List<String> personPhones = personServiceMock.getPersonPhoneCoverByStation(1);

		when(personServiceMock.getPersonPhoneCoverByStation(1)).thenReturn(personPhones);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(personPhones);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/phoneAlert?firestation=1")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getPersonEmailByCity_Test() throws Exception {

		List<String> email = personServiceMock.getPersonEmailByCity("Culver");

		when(personServiceMock.getPersonEmailByCity("Culver")).thenReturn(email);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(email);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/communityEmail?city=Culver")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getPersonInfoByStation_Test() throws Exception {

		List<Foster> fosters = personServiceMock.findPersonAndMedicalRecordsByStation(1);

		when(personServiceMock.findPersonAndMedicalRecordsByStation(1)).thenReturn(fosters);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(fosters);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/flood/station?station=1")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getFireAddress_Test() throws Exception {

		List<FirePersons> firePerson = personServiceMock.getFireAddress("1509 Culver St");

		when(personServiceMock.getFireAddress("1509 Culver St")).thenReturn(firePerson);

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(firePerson);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/fire?address=1509 Culver St")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
