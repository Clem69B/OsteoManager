package net.baz1.osteo.manager.domain.repository;

import net.baz1.osteo.manager.domain.model.MedicalProfession;
import net.baz1.osteo.manager.domain.model.MedicalProfessional;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Clément Bazin on 14/12/14.
 * <p>
 * Simple Patient repository with Spring Data MongoDB
 */
public interface MedicalProfessionalRepository extends MongoRepository<MedicalProfessional, String> {

    List<MedicalProfessional> findByFirstName(String firstName);

    List<MedicalProfessional> findByLastName(String lastName);

    List<MedicalProfessional> findByProfession(MedicalProfession profession);
}
