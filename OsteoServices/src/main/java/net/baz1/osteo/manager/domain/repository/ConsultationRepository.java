package net.baz1.osteo.manager.domain.repository;

import net.baz1.osteo.manager.domain.model.Consultation;
import net.baz1.osteo.manager.domain.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Clément Bazin on 14/02/15.
 */
public interface ConsultationRepository extends MongoRepository<Consultation, String> {

    public List<Consultation> findByPatient(Patient patient);

}
