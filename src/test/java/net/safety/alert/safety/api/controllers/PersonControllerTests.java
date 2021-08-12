package net.safety.alert.safety.api.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
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
public class PersonControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private PersonService personServiceMock;

    @Test
    public void getAllPersonsControllerTest() throws Exception {

        Person person = new Person();
        person.setLastName("Morreau");
        person.setFirstName("Evelyne");
        person.setAddress("15 rue Colbert");
        person.setCity("Paris");
        person.setPhone("854-856-6623");
        person.setEmail("e.morreau@gmail.com");
        person.setZip(75016);
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        when(personServiceMock.getPerson()).thenReturn(personList);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/person").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
}
