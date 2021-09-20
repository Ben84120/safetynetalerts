package net.safety.alert.safety.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.safety.alert.safety.api.model.MedicalRecords;

@Repository
public interface MedicalrecordsRepository extends CrudRepository<MedicalRecords, Long> {

	@Query(value = "SELECT * FROM MEDICALRECORDS M WHERE LAST_NAME = :LastName", nativeQuery = true)
	List<MedicalRecords> getMedicalRecordsByLastName(@Param("LastName") String lastName);
	
	List<MedicalRecords> findByFirstNameAndLastName(String firstName, String lastName);
	
	MedicalRecords findByFirstAndLastName(String firstName, String lastName);
}
