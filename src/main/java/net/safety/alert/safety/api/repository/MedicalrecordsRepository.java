package net.safety.alert.safety.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.safety.alert.safety.api.model.MedicalrecordsModel.Medicalrecords;

@Repository
public interface MedicalrecordsRepository extends CrudRepository<Medicalrecords, Long> {

}
