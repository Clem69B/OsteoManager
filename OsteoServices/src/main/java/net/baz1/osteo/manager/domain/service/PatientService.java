package net.baz1.osteo.manager.domain.service;

import net.baz1.osteo.manager.domain.exceptions.BadRequestRepositoryException;
import net.baz1.osteo.manager.domain.exceptions.ConsultationMissingInformationException;
import net.baz1.osteo.manager.domain.exceptions.PatientMissingInformationException;
import net.baz1.osteo.manager.domain.exceptions.PatientNotFoundException;
import net.baz1.osteo.manager.domain.model.Consultation;
import net.baz1.osteo.manager.domain.model.Patient;
import net.baz1.osteo.manager.domain.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clément Bazin on 03/02/15.
 */

@Component
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient add(Patient patient) throws BadRequestRepositoryException, PatientMissingInformationException {
        if (patient.getLastName() == null || patient.getFirstName() == null) {
            throw new PatientMissingInformationException("last name or first name missing for patient");
        }
        patient.setConsultations(new ArrayList<Consultation>());
        try {
            return this.patientRepository.save(patient);
        } catch (Exception e) {
            throw new BadRequestRepositoryException("Unable to save a new patient");
        }
    }

    public Patient get(String id) throws PatientNotFoundException {

        final Patient patient = this.patientRepository.findOne(id);
        if (patient != null) {
            return patient;
        }
        throw new PatientNotFoundException("No patient associate to this ID");
    }

    public Page<Patient> getAll(Pageable pageable) {

        if (pageable.getPageNumber() < 0 || pageable.getPageSize() < 0) {
            throw new IllegalArgumentException("Missing parameters in Pageable class");
        }
        return this.patientRepository.findAll(pageable);
    }

    public List<Patient> getByFirstName(String firstName) throws PatientNotFoundException {
        List<Patient> patients = this.patientRepository.findByFirstNameOrderByLastNameAscAllIgnoreCase(firstName);
        if (patients != null && patients.size() > 0) {
            return patients;
        }
        throw new PatientNotFoundException("No patient associate to this first name");
    }

    public List<Patient> getByLastName(String lastName) throws PatientNotFoundException {
        List<Patient> patients = this.patientRepository.findByLastNameOrderByFirstNameAscAllIgnoreCase(lastName);
        if (patients != null && patients.size() > 0) {
            return patients;
        }
        throw new PatientNotFoundException("No patient associate to this first name");
    }

    public Patient update(Patient patient) throws BadRequestRepositoryException {
        try {
            return this.patientRepository.save(patient);
        } catch (Exception e) {
            throw new BadRequestRepositoryException("Unable to update the patient " + patient.toString());
        }
    }

    public Patient addConsultation(String id, Consultation consultation)
            throws PatientNotFoundException, ConsultationMissingInformationException, BadRequestRepositoryException {
        if (consultation.getDateConsultation() == null) {
            throw new ConsultationMissingInformationException("Can't add consultation without a date");
        }

        Patient patient = get(id);
        patient.getConsultations().add(consultation);
        return update(patient);
    }
}
