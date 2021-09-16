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

import net.safety.alert.safety.api.model.Person;
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

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
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

	/*@Test
	public void PersonStationCover_Test() throws Exception {
		
		List<Person> personList = new ArrayList<>();
		assertThat(personList).isNotNull();
		
		when(personServiceMock.getPersonStationCover(3)).thenReturn(personList);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/person/42")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}*/

	
	@Test
	public void getPerson_Not_ExistTest() throws Exception {
		// GIVEN
		// WHEN
		Person person = new Person();
		person.setFirstName(null);
		person.setLastName(null);
		// THEN
		
		when(personServiceMock.getPerson()).thenReturn(null);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/person")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
