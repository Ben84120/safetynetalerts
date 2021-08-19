package net.safety.alert.safety.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
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

import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.service.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personServiceMock;

    @Test
    public void getPersonsTest() throws Exception {

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

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/person").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void createPersonsTest() throws Exception {

        Person person = new Person();
        person.setLastName("Paul");
        person.setFirstName("Desfès");
        person.setAddress("Avenue du bout du monde");
        person.setCity("Paris");
        person.setPhone("852-865-4589");
        person.setEmail("p.defes@outlook.fr");
        person.setZip(75016);
        personServiceMock.savePerson(person);
		//THEN
		List<Person> personSave = new ArrayList<>();
		assertThat(personSave).isNotNull();
		

        when(personServiceMock.savePerson(person)).thenReturn(person);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/person").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void getPersonsByIDTest() throws Exception {

        Person person = new Person();
        person.setId(85L);
        person.setLastName("Craig");
        person.setFirstName("Daniel");
        person.setAddress("Avenue du bout du monde");
        person.setCity("Paris");
        person.setPhone("852-865-4589");
        person.setEmail("d.craig@outlook.fr");
        person.setZip(75016);
        
        

        when(personServiceMock.getPersonById(85L)).thenReturn(Optional.of(person));
        
        Optional<Person> actual = personServiceMock.getPersonById(85L);
        assertThat(actual.get()).isNotNull();
		assertTrue(actual.isPresent());
        assertThat(actual.get().getId()).isEqualTo(85L);

       
    
    }  
}
