package net.baz1.osteo.manager.service;

import net.baz1.osteo.manager.AppTest;
import net.baz1.osteo.manager.domain.model.Checkup;
import net.baz1.osteo.manager.domain.model.CheckupCategory;
import net.baz1.osteo.manager.domain.model.Consultation;
import net.baz1.osteo.manager.domain.model.Patient;
import net.baz1.osteo.manager.domain.repository.CheckupCategoryRepository;
import net.baz1.osteo.manager.domain.repository.ConsultationRepository;
import net.baz1.osteo.manager.domain.repository.PatientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Clément Bazin on 18/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppTest.class)
public class ConsultationServiceTest {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private CheckupCategoryRepository categoryRepository;

    @Autowired
    private ConsultationService consultationService;

    private Patient patient;
    private CheckupCategory checkupCategory;

    @Before
    public void before() {
        this.patientRepository.deleteAll();
        this.consultationRepository.deleteAll();
        this.categoryRepository.deleteAll();

        /* Populate the patient database */
        this.patient = this.patientRepository.save(new Patient("James", "Stevenson"));
        this.checkupCategory = this.categoryRepository.save(new CheckupCategory("hello"));
    }

    @Test
    public void newConsultationBasic() throws Exception {
        Consultation consultation = new Consultation();

        consultation.setLastOsteopathConsultation(new Date());
        consultation.setReason("Bobo");
        consultation.setOngoingTreatment("Médoc");

        List<Checkup> checkups = new ArrayList<>();
        Checkup checkup = new Checkup();
        checkup.setCheckupCategory(this.checkupCategory);
        checkup.setContents("N'importe quoi");
        checkups.add(checkup);
        consultation.setCheckups(checkups);

        Consultation result = this.consultationService.newConsultation(patient.getId(), consultation);

        Assert.assertNotNull("newConsultation() must return a consultation", result);
        Assert.assertNotNull("created Date update", result.getCreatedDate());
        Assert.assertNotNull("updated date update", result.getLastUpdatedDate());
        Assert.assertNotNull("patient must be added", result.getPatient());
        Assert.assertEquals("patient name", "Stevenson", result.getPatient().getLastName());
    }
}