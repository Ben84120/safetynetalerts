package net.safety.alert.safety.api.service;



import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonStationCover;
import net.safety.alert.safety.api.repository.FirestationsRepository;
import net.safety.alert.safety.api.repository.MedicalrecordsRepository;
import net.safety.alert.safety.api.repository.PersonRepository;
import net.safety.alert.safety.api.service.error.MissingParamException;

@Slf4j
@Setter
@Getter
@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	private MedicalrecordsRepository medicalrecordsRepository;
	private FirestationsRepository firestationsRepository;

	public Optional<Person> getPersonById(final Long id) {
		return personRepository.findById(id);
	}

	public Iterable<Person> getPerson() {
		return personRepository.findAll();
	}

	public void deletePerson(final Long id) {
		personRepository.deleteById(id);
	}

	public Person savePerson(Person person) {
		Person savedPerson = personRepository.save(person);
		return savedPerson;
	}
	
	public PersonStationCover getPersonStationCover(final Integer stationNumber) {
		log.info("Service Firestation : station: "+stationNumber+" getting persons covered.");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        PersonStationCover personStationCover = new PersonStationCover();
        List<Person> personsCovered = new ArrayList<>();
        List<FireStations> firestations = firestationsRepository.findByStation(stationNumber);
        if (CollectionUtils.isEmpty(firestations)) {
            throw new MissingParamException("Aucune station n'existe avec ce numéro "+stationNumber);
        }
        firestations.forEach(firestation -> {
            List<Person> personsByAddress = personRepository.findByAddress(firestation.getAddress());
            personsByAddress.forEach(person -> {
                personsCovered.add(person);
                LocalDate dateNow = LocalDate.now();
                MedicalRecords medicalRecord = medicalrecordsRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), dateTimeFormatter);
                if(Period.between(birthDate, dateNow).getYears() >= 18) {
                    personStationCover.setNombreAdultes(personStationCover.getNombreAdultes()+1);
                }
                if (Period.between(birthDate, dateNow).getYears() <= 18) {
                    personStationCover.setNombreMineurs(personStationCover.getNombreMineurs()+1);
                }
            });
        });
        personStationCover.setPersonsList(personsCovered);
        return personStationCover;
	}

}
