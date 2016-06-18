package net.baz1.osteo.manager.service;

import net.baz1.osteo.manager.domain.model.Consultation;
import net.baz1.osteo.manager.domain.model.Patient;
import net.baz1.osteo.manager.domain.repository.ConsultationRepository;
import net.baz1.osteo.manager.exceptions.BadRequestRepositoryException;
import net.baz1.osteo.manager.exceptions.ConsultationMissingException;
import net.baz1.osteo.manager.exceptions.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Clément Bazin on 15/02/15.
 */

@Component
public class ConsultationService {

    @Autowired
    PatientService patientService;
    @Autowired
    ConsultationRepository consultationRepository;

    /**
     * Create a new consultation for a specified patientId
     *
     * @param patientId    patient to associate to the consultation
     * @param consultation new consultation (patient data will be override)
     * @return saved consultation with updated patient data
     * @throws PatientNotFoundException      wrong patientId
     * @throws BadRequestRepositoryException Error with database
     */
    public Consultation newConsultation(String patientId, Consultation consultation)
            throws PatientNotFoundException, BadRequestRepositoryException {

        // Todo: perform check on consultation

        Patient patient = this.patientService.get(patientId);
        consultation.setPatient(patient);
        Date date = new Date();
        consultation.setCreatedDate(date);
        consultation.setLastUpdatedDate(date);

        return this.consultationRepository.save(consultation);
    }

    /**
     * Return the list of all consultations for a specific patient
     *
     * @param patientId patient Id of researched consultations
     * @return the list of all consultations
     * @throws PatientNotFoundException wrong patientId
     */
    public List<Consultation> getConsultations(String patientId) throws PatientNotFoundException {

        Patient patient = this.patientService.get(patientId);
        return this.consultationRepository.findByPatient(patient);
    }

    public Consultation updateConsultation(Consultation consultation) throws ConsultationMissingException {
        if (this.consultationRepository.findOne(consultation.getId()) == null) {
            throw new ConsultationMissingException("This consultation doesn't exist");
        }
        consultation.setLastUpdatedDate(new Date());
        return this.consultationRepository.save(consultation);
    }

    /**
     * Remove a specific consultation
     *
     * @param consultationId Id of the consultation to remove
     */
    public void removeConsultation(String consultationId) {
        this.consultationRepository.delete(consultationId);
    }

}
