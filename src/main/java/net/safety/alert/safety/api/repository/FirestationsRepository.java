package net.safety.alert.safety.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.safety.alert.safety.api.model.FireStations;

@Repository
public interface FirestationsRepository extends CrudRepository<FireStations, Long> {

	@Query(value = "SELECT * FROM FIRESTATIONS F WHERE STATION = :station", nativeQuery = true)
	FireStations getStationsByNumber(@Param("station") Integer station);

	List<FireStations> findByStation(int number);
	
	List<FireStations> findByAddress(String address);

}
