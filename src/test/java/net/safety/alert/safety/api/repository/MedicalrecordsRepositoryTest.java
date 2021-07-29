package net.safety.alert.safety.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.safety.alert.safety.api.model.MedicalRecords;

@Repository
public interface MedicalrecordsRepositoryTest extends CrudRepository<MedicalRecords, Long> {

}
