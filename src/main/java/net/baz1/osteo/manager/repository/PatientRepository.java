package net.baz1.osteo.manager.repository;

import net.baz1.osteo.manager.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Clément Bazin on 14/12/14.
 *
 * Simple Patient repository with Spring Data MongoDB
 */
public interface PatientRepository extends MongoRepository<Patient, String> {

    public List<Patient> findByFirstNameOrderByLastNameAscAllIgnoreCase(String firstName);
    public List<Patient> findByLastNameOrderByFirstNameAscAllIgnoreCase(String lastName);
    public Patient findBySocialSecurityNumberIgnoreCase(String socialSecurityNumber);


}
