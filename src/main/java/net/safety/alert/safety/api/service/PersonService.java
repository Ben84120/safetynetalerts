package net.safety.alert.safety.api.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.safety.alert.safety.api.model.ChildAlert;
import net.safety.alert.safety.api.model.FirePersons;
import net.safety.alert.safety.api.model.FireStations;
import net.safety.alert.safety.api.model.Foster;
import net.safety.alert.safety.api.model.MedicalRecords;
import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonStationCover;
import net.safety.alert.safety.api.model.PersonWithMedicalRecords;
import net.safety.alert.safety.api.model.PersonsInfoWithMedicalRecords;
import net.safety.alert.safety.api.model.PersonsWithFireStationNumber;
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
	@Autowired
	private MedicalrecordsRepository medicalrecordsRepository;
	@Autowired
	private FirestationsRepository firestationsRepository;

	/**
	 * @param Identifiant d'une personne.
	 *
	 * @return La liste des personnes par leurs identifiants.
	 */
	public Optional<Person> getPersonById(final Long id) {
		return personRepository.findById(id);
	}

	/**
	 * @return L'ensemble des personnes.
	 */
	public Iterable<Person> getPerson() {
		return personRepository.findAll();
	}
	
	public Person updatePerson(final Long id, Person person) {
		Optional<Person> p = personRepository.findById(id);
		if (p.isPresent()) {
			Person currentPerson = p.get();

			
			
			String lastName = person.getLastName();
			if (lastName != null) {
				currentPerson.setEmail(lastName);
			}
			
			String firstName = person.getFirstName();
			if (firstName != null) {
				currentPerson.setEmail(firstName);
			}
			
			String email = person.getEmail();
			if (email != null) {
				currentPerson.setEmail(email);
			}

			Integer zip = person.getZip();
			if (zip != null) {
				currentPerson.setZip(zip);
			}

			String city = person.getCity();
			if (city != null) {
				currentPerson.setCity(city);
			}

			String address = person.getAddress();
			if (address != null) {
				currentPerson.setAddress(address);
			}

			String phone = person.getPhone();
			if (phone != null) {
				currentPerson.setPhone(phone);
			}
			
			Long iD = person.getId(); 
			if (iD != null) {
				currentPerson.setId(iD);
			}

			
			return personRepository.save(currentPerson);
		} else {
			return null;
		}
	}
	
	/**
	 * @param Identifiant d'une personne.
	 *
	 * @return La personne supprimée par son ID.
	 */
	public void deletePerson(final Long id) {
		personRepository.deleteById(id);
	}

	/**
	 * @param La personne qui va être sauvegardée.
	 *
	 * @return La personne sauvegardée.
	 */
	public Person savePerson(Person person) {
		Person savedPerson = personRepository.save(person);
		return savedPerson;
	}

	/**
	 * @param Nom de famille de la classe Person
	 *
	 * @return La personne à partir de son nom de famille
	 */
	public List<Person> getPersonByLastName(String lastName) {
		return personRepository.getPersonByLastName(lastName);

	}

	/**
	 * @param numéro de la station demandée.
	 *
	 * @throws MissingParamException si jamais le paramètre n'existe pas.
	 *
	 * @return La liste des personnes couvertes par le numéro de station choisi.
	 */
	public PersonStationCover getPersonStationCover(final Integer stationNumber) {
		log.info("Service Firestation : station: " + stationNumber + " getting persons covered.");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		PersonStationCover personStationCover = new PersonStationCover();
		List<Person> personsCovered = new ArrayList<>();
		List<FireStations> firestations = firestationsRepository.findByStation(stationNumber);
		if (CollectionUtils.isEmpty(firestations)) {
			log.error("Aucune station n'existe avec ce numéro" + stationNumber);
			throw new MissingParamException("Aucune station n'existe avec ce numéro " + stationNumber);
		}
		firestations.forEach(firestation -> {
			List<Person> personsByAddress = personRepository.findByAddress(firestation.getAddress());
			personsByAddress.forEach(person -> {
				personsCovered.add(person);
				LocalDate dateNow = LocalDate.now();
				MedicalRecords medicalRecord = medicalrecordsRepository
						.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
				LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), dateTimeFormatter);
				if (Period.between(birthDate, dateNow).getYears() >= 18) {
					personStationCover.setNombreAdultes(personStationCover.getNombreAdultes() + 1);
				}
				if (Period.between(birthDate, dateNow).getYears() <= 18) {
					personStationCover.setNombreMineurs(personStationCover.getNombreMineurs() + 1);
				}
			});
		});
		personStationCover.setPersonsList(personsCovered);
		return personStationCover;
	}

	/**
	 * @param ville des personne recherchées.
	 *
	 * @return La liste des adresse e-mails des personnes d'une ville choisie.
	 */
	public List<String> getPersonEmailByCity(final String city) {
		List<Person> persons = personRepository.findByCity(city);
		List<String> emails = new ArrayList<>();
		for (Person person : persons) {
			emails.add(person.getEmail());
		}

		return emails;
	}

	/**
	 * @param numéro de la station demandée.
	 *
	 * @return Les numéros de téléphones des personnes couvertes par la station
	 *         choisie.
	 */
	public List<String> getPersonPhoneCoverByStation(final Integer stationNumber) {
		List<String> phones = personRepository.getPhoneByStation(stationNumber);

		return phones;
	}

	/**
	 * @param Noms de famille de la classe Person
	 *
	 * @throws MissingParamException si jamais le paramètre n'existe pas.
	 *
	 * @return La liste des PersonsInfoWithMedicalRecords par le nom de famille
	 *         ainsi que leurs homonymes.
	 */
	public List<PersonsInfoWithMedicalRecords> getPersonsInfoWithMedicalRecord(String lastName) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		List<PersonsInfoWithMedicalRecords> resultat = new ArrayList<>();
		List<Person> personsByLastName = personRepository.findByLastName(lastName);

		if (CollectionUtils.isEmpty(personsByLastName)) {
			log.error("Person inconnue");
			throw new MissingParamException("Personne inconnue ");
		}
		personsByLastName.forEach(person -> {
			List<MedicalRecords> mListe = medicalrecordsRepository.findByLastName(lastName);
			mListe.forEach(m -> {

				if (m.getLastName() == person.getLastName() && m.getFirstName() == person.getFirstName()) {
					PersonsInfoWithMedicalRecords pInformation = new PersonsInfoWithMedicalRecords();

					pInformation.setNom(m.getLastName());
					pInformation.setPrenom(m.getFirstName());
					pInformation.setAdresse(person.getAddress());
					pInformation.setEmail(person.getEmail());
					pInformation.setMedication(m.getMedications());
					pInformation.setAllergies(m.getAllergies());
					LocalDate dateNow = LocalDate.now();
					LocalDate birthDate = LocalDate.parse(m.getBirthdate(), dateTimeFormatter);
					pInformation.setAge(Period.between(birthDate, dateNow).getYears());
					resultat.add(pInformation);
				}

			});
		});

		return resultat;
	}

	/**
	 * @param numéro de la station demandée.
	 *
	 *
	 * @return La liste des Fosters desservis par le numéro de station.
	 * 
	 */
	public List<Foster> findPersonAndMedicalRecordsByStation(Integer stationNumber) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		List<FireStations> stations = firestationsRepository.findByStation(stationNumber);
		List<Person> persons = personRepository.findAll();
		List<MedicalRecords> medicalRecords = medicalrecordsRepository.findAll();

		List<Foster> fosters = new ArrayList<>();
		stations.forEach(station -> {
			List<PersonWithMedicalRecords> p = new ArrayList<>();
			persons.forEach(person -> {
				if (station.getAddress().equals(person.getAddress())) {
					medicalRecords.forEach(medicalRecord -> {
						if (medicalRecord.getFirstName().equals(person.getFirstName())
								&& medicalRecord.getLastName().equals(person.getLastName())) {

							String myMedications[] = medicalRecord.getMedications().split(",");
							String myAllergies[] = medicalRecord.getAllergies().split(",");
							PersonWithMedicalRecords pWithMedicalRecords = new PersonWithMedicalRecords();
							pWithMedicalRecords.setLastName(medicalRecord.getLastName());
							pWithMedicalRecords.setFirstName(medicalRecord.getFirstName());
							pWithMedicalRecords.setPhone(person.getPhone());
							pWithMedicalRecords.setMedications(myMedications);
							pWithMedicalRecords.setAllergies(myAllergies);
							LocalDate dateNow = LocalDate.now();
							LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), dateTimeFormatter);
							pWithMedicalRecords.setAge(Period.between(birthDate, dateNow).getYears());
							p.add(pWithMedicalRecords);
						}
					});
				}
			});
			fosters.add(new Foster(station.getAddress(), p));
		});
		return fosters;
	}

	/**
	 * @param adresse des personnes choisie.
	 *
	 *
	 * @return La liste des FirePersons ainsi que le numéro de la Firestation.
	 * 
	 */
	public List<FirePersons> getFireAddress(String adress) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		List<FireStations> stations = firestationsRepository.findByAddress(adress);
		List<Person> persons = personRepository.findAll();
		List<MedicalRecords> medicalRecords = medicalrecordsRepository.findAll();

		List<FirePersons> pWithFire = new ArrayList<>();
		stations.forEach(station -> {
			List<PersonsWithFireStationNumber> p = new ArrayList<>();
			persons.forEach(person -> {
				if (station.getAddress().equals(person.getAddress())) {
					medicalRecords.forEach(medicalRecord -> {
						if (medicalRecord.getFirstName().equals(person.getFirstName())
								&& medicalRecord.getLastName().equals(person.getLastName())) {

							String myMedications[] = medicalRecord.getMedications().split(",");
							String myAllergies[] = medicalRecord.getAllergies().split(",");
							PersonsWithFireStationNumber pWithFireStationNumber = new PersonsWithFireStationNumber();
							pWithFireStationNumber.setLastName(medicalRecord.getLastName());
							pWithFireStationNumber.setFirstName(medicalRecord.getFirstName());
							pWithFireStationNumber.setPhone(person.getPhone());
							pWithFireStationNumber.setMedications(myMedications);
							pWithFireStationNumber.setAllergies(myAllergies);
							LocalDate dateNow = LocalDate.now();
							LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), dateTimeFormatter);
							pWithFireStationNumber.setAge(Period.between(birthDate, dateNow).getYears());
							p.add(pWithFireStationNumber);
						}
					});
				}
			});
			pWithFire.add(new FirePersons(station.getStation(), p));
		});
		return pWithFire;
	}
	/**
	 * @param adresse des personnes choisie.
	 *
	 *
	 * @return La liste des ChildAlert de 18 ans ou moins.
	 * 
	 */
	public List<ChildAlert> getChildAlert(String address) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		List<ChildAlert> resultat = new ArrayList<>();
		List<Person> persons = personRepository.findByAddress(address);
		for (Person person : persons) {
			LocalDate dateNow = LocalDate.now();
			MedicalRecords findByFirstNameAndLastName = medicalrecordsRepository
					.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
			LocalDate birthDate = LocalDate.parse(findByFirstNameAndLastName.getBirthdate(), dateTimeFormatter);
			if (Period.between(birthDate, dateNow).getYears() <= 18) {
				ChildAlert child = new ChildAlert();
				child.setFirstName(person.getFirstName());
				child.setLastName(person.getLastName());
				child.setBirthDate(Period.between(birthDate, dateNow).getYears());
				List<Person> membresfamille = persons.stream()
						.filter(p -> p.getAddress().equals(person.getAddress())
								&& p.getLastName().equals(person.getLastName())
								&& !p.getFirstName().equals(person.getFirstName()))
						.collect(Collectors.toList());

				child.setMembreFamille(membresfamille);
				resultat.add(child);
			}
		}
		return resultat;
	}

}
