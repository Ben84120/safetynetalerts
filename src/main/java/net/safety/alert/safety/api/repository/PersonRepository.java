package net.safety.alert.safety.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.safety.alert.safety.api.model.Person;;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
	
	@Query(value="SELECT * FROM PERSON P WHERE LAST_NAME = :LastName", nativeQuery = true)
	List<Person> getPersonByLastName(@Param("LastName") String LastName);
	
	
	

}


