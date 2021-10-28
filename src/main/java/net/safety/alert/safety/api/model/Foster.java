package net.safety.alert.safety.api.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Foster {
	private String address;
	List<PersonWithMedicalRecords> personWithMedicalRecords;

}
