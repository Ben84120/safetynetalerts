package net.safety.alert.safety.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.safety.alert.safety.api.model.FireStations;


@Repository
public interface FirestationsRepository extends CrudRepository<FireStations, Long> {
	
	@Query(value="SELECT * FROM FIRESTATIONS F WHERE STATION = :Station", nativeQuery = true)
	FireStations getStationsByNumber(@Param("Station") String Station);

}
