package net.baz1.osteo.manager.domain.repository;

import net.baz1.osteo.manager.domain.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Clément Bazin on 14/12/14.
 * <p>
 * Simple Patient repository with Spring Data MongoDB
 */
public interface PatientRepository extends MongoRepository<Patient, String> {

    public List<Patient> findByFirstNameOrderByLastNameAscAllIgnoreCase(String firstName);

    public List<Patient> findByLastNameOrderByFirstNameAscAllIgnoreCase(String lastName);

    public Patient findBySocialSecurityNumberIgnoreCase(String socialSecurityNumber);


}
