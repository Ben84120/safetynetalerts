package net.safety.alert.safety.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.safety.alert.safety.api.model.Person;
import net.safety.alert.safety.api.model.PersonInformations;;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	//@Query(value = "SELECT * FROM PERSON P WHERE LAST_NAME = :lastName", nativeQuery = true)
	List<Person> getPersonByLastName(@Param("lastName") String lastName);

	@Query(value = "SELECT P.LAST_NAME nom, P.FIRST_NAME prenom, P.ADDRESS adresse, P.PHONE numeroTel FROM FIRESTATIONS FS, PERSON P WHERE  FS.ADDRESS=P.ADDRESS AND FS.STATION= :stationNumber", nativeQuery = true)
	List<PersonInformations> getPersonInformations(@Param("stationNumber") Integer stationNumber);

	List<Person> findByAddress(String address);

	@Query(value = "SELECT P.PHONE FROM FIRESTATIONS FS, PERSON P WHERE FS.ADDRESS=P.ADDRESS AND STATION= :stationNumber", nativeQuery = true)
	List<String> getPhoneByStation(@Param("stationNumber") Integer stationNumber);

	List<Person> findByCity(String city);

	@Query(value = "SELECT P.LAST_NAME, P.FIRST_NAME, P.ADDRESS, P.PHONE, FS.STATION, MR BIRTHDATE, MR.MEDICATIONS, MR.ALLERGIES, FROM FIRESTATIONS FS, PERSON P, MEDICALRECORDS MR WHERE FS.ADDRESS=P.ADDRESS AND FS.ADDRESS= :address", nativeQuery = true)
	List<Person> getPersonsInformationsAndMedicalRecordsByStation(@Param("address") String address);

	List<Person> findByLastName(String lastName);

	List<Person> findAll();

}
