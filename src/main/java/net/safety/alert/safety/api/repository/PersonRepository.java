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

	@Query(value = "SELECT * FROM PERSON P WHERE LAST_NAME = :LastName", nativeQuery = true)
	List<Person> getPersonByLastName(@Param("LastName") String lastName);

	@Query(value = "SELECT P.LAST_NAME nom, P.FIRST_NAME prenom, P.ADDRESS adresse, P.PHONE numeroTel FROM FIRESTATIONS FS, PERSON P WHERE  FS.ADDRESS=P.ADDRESS AND FS.STATION= :stationNumber", nativeQuery = true)
	List<PersonInformations> getPersonInformations(@Param("stationNumber") Integer stationNumber);

	List<Person> findByAddress(String address);
	
	@Query(value = "SELECT FS.STATION, P.PHONE FROM FIRESTATIONS FS, PERSON P WHERE FS.ADDRESS=P.ADDRESS AND STATION= :StationNumber", nativeQuery = true)
	List<PersonInformations> getPersonPhone_By_StationNumber(@Param("stationNumber") Integer stationNumber);
	
	@Query(value = "SELECT P.EMAIL, P.CITY FROM PERSON P WHERE CITY = :City", nativeQuery = true)
	List<PersonInformations> getPersonEmail_By_City(@Param("city") String city);
	
	

}
