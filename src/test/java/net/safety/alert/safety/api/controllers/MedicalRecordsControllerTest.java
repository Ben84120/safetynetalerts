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

import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.service.MedicalRecordsService;

@WebMvcTest(controllers = MedicalRecordsController.class)
public class MedicalRecordsControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordsService medicalRecordsServiceMock;

    @Test
    public void getAllMedicalRecordsTest() throws Exception {

        MedicalRecords medicalRecords  = new MedicalRecords();
        medicalRecords.setFirstName("Erik");
        medicalRecords.setLastName("Sorpek");
        medicalRecords.setBirthdate("04/03/1984");
        medicalRecords.setMedications("aznol:350mg, hydrapermazol:100mg");
        medicalRecords.setAllergies("nillacilan");
        List<MedicalRecords> medicalRecordsList = new ArrayList<>();
        medicalRecordsList.add(medicalRecords);

        when(medicalRecordsServiceMock.getMedicalRecords()).thenReturn(medicalRecordsList);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/medicalrecords").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void getMedicalRecordsByIdTest( ) {
    	MedicalRecords medicalRecords  = new MedicalRecords();
    	medicalRecords.setId(40L);
    	medicalRecords.setFirstName("Delphine");
        medicalRecords.setLastName("Manchon");
        medicalRecords.setBirthdate("07/11/1984");
        
        
        
        
        when(medicalRecordsServiceMock.getMedicalRecordsById(40L)).thenReturn(Optional.of(medicalRecords));
        assertThat(medicalRecords.getId()).isNotNull();
		assertThat(medicalRecords.getId()).isEqualTo(40L);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/medicalrecords").
                contentType(MediaType.APPLICATION_JSON);
    	
    }
    
    
}
