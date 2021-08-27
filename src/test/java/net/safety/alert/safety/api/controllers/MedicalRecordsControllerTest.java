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

import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.service.MedicalRecordsService;

@WebMvcTest(controllers = MedicalRecordsController.class)
public class MedicalRecordsControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordsService medicalRecordsServiceMock;

    @Test
    public void getMedicalRecord_Test() throws Exception {	

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
    public void getMedicalRecordsById_Test() throws Exception {
    	MedicalRecords medicalRecords  = new MedicalRecords();
    	medicalRecords.setId(40L);
    	medicalRecords.setFirstName("Delphine");
        medicalRecords.setLastName("Manchon");
        medicalRecords.setBirthdate("07/11/1984");
        
        
        
        
        when(medicalRecordsServiceMock.getMedicalRecordsById(40L)).thenReturn(Optional.of(medicalRecords));
        

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/medicalrecords/40").
                contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    	
    }
    
    @Test
    public void createMedicalRecord_Test() throws Exception {

        MedicalRecords createMedicalRecord = new MedicalRecords();
        createMedicalRecord.setLastName("Patrick");
        createMedicalRecord.setFirstName("Boon");
        createMedicalRecord.setBirthdate("02/11/1980");
        
        medicalRecordsServiceMock.saveMedicalRecords(createMedicalRecord);
		//THEN
		List<MedicalRecords> medicalRecordList = new ArrayList<>();
		assertThat(medicalRecordList).isNotNull();
		

        when(medicalRecordsServiceMock.saveMedicalRecords(createMedicalRecord)).thenReturn(createMedicalRecord);
        
        ObjectMapper mapper = new ObjectMapper();
    	String writeValueAsString = mapper.writeValueAsString(createMedicalRecord);
        
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/medicalrecords").
                contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void updateMedicalRecord_Test() throws Exception {
    
    	MedicalRecords updateMedicalRecord = new MedicalRecords();
    	updateMedicalRecord.setFirstName("Blandine");
    	updateMedicalRecord.setLastName("Falles");
    	updateMedicalRecord.setBirthdate("10/10/2010");
    	
    	when(medicalRecordsServiceMock.getMedicalRecordsById(1L)).thenReturn(Optional.of(updateMedicalRecord));
    	when(medicalRecordsServiceMock.saveMedicalRecords(updateMedicalRecord)).thenReturn(updateMedicalRecord);
    	
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String writeValueAsString = mapper.writeValueAsString(updateMedicalRecord);
    	
    	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/medicalrecords/1").
                contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void updateMedicalRecord_Test2() throws Exception {
    
    	MedicalRecords updateMedicalRecord = new MedicalRecords();
    	updateMedicalRecord.setFirstName("Blandine");
    	updateMedicalRecord.setLastName("Falles");
    	updateMedicalRecord.setBirthdate("10/10/2010");
    	
    	when(medicalRecordsServiceMock.getMedicalRecordsById(1L)).thenReturn(Optional.empty());
    	when(medicalRecordsServiceMock.saveMedicalRecords(updateMedicalRecord)).thenReturn(updateMedicalRecord);
    	
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String writeValueAsString = mapper.writeValueAsString(updateMedicalRecord);
    	
    	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/medicalrecords/1").
                contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
	public void deleteMedicalRecord_Test() throws Exception {
		MedicalRecords deleteMedicalRecord = new MedicalRecords();
		deleteMedicalRecord.setId(22L);
		deleteMedicalRecord.setFirstName("Joe");
		deleteMedicalRecord.setLastName("Ingles");
		deleteMedicalRecord.setBirthdate("20/02/1985");
		
		
		List<Person> personSave = new ArrayList<>();
		assertThat(personSave).isNotNull();
		
		when(medicalRecordsServiceMock.getMedicalRecordsById(22L)).thenReturn(Optional.of(deleteMedicalRecord));

		
        
        ObjectMapper mapper = new ObjectMapper();
    	String writeValueAsString = mapper.writeValueAsString(deleteMedicalRecord);
        
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/medicalrecords/22").
                contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

        mockMvc.perform(builder).
                andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    
    
    
}
