package net.safety.alert.safety.api.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.service.FireStationsService;

@WebMvcTest(controllers = FireStationsController.class)
public class FireStationsControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationsService fireStationsServiceMock;

    @Test
    public void getAllFireStationsTest() throws Exception {

        FireStations fireStations = new FireStations();
        fireStations.setAddress("Avenue du bout du monde");
        fireStations.setStation(6);
        List<FireStations> fireStationsList = new ArrayList<>();
        fireStationsList.add(fireStations);

        when(fireStationsServiceMock.getFireStations()).thenReturn(fireStationsList);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/firestations").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
}
