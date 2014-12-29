package net.baz1.osteo.manager.repository;

import net.baz1.osteo.manager.model.MedicalProfession;
import net.baz1.osteo.manager.model.MedicalProfessional;
import net.baz1.osteo.manager.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Clément Bazin on 14/12/14.
 *
 * Simple Patient repository with Spring Data MongoDB
 */
public interface MedicalProfessionalRepository extends MongoRepository<MedicalProfessional, String> {

    public List<MedicalProfessional> findByFirstName(String firstName);
    public List<MedicalProfessional> findByLastName(String lastName);
    public List<MedicalProfessional> findByProfession(MedicalProfession profession);
}
