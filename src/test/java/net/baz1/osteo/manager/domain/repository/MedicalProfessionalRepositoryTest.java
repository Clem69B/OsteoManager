package net.baz1.osteo.manager.domain.repository;

import net.baz1.osteo.manager.App;
import net.baz1.osteo.manager.domain.model.MedicalProfession;
import net.baz1.osteo.manager.domain.model.MedicalProfessional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Clément Bazin on 31/01/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class MedicalProfessionalRepositoryTest {

    @Autowired
    private MedicalProfessionRepository medicalProfessionRepository;
    @Autowired
    private MedicalProfessionalRepository medicalProfessionalRepository;

    @Before
    public void setUp() throws Exception {
        medicalProfessionRepository.deleteAll();
        medicalProfessionalRepository.deleteAll();

        medicalProfessionRepository.save(new MedicalProfession("doctor"));
        medicalProfessionRepository.save(new MedicalProfession("Nurse"));
        medicalProfessionalRepository.save(new MedicalProfessional("Julie", "Bernard",
                medicalProfessionRepository.findByName("nurse").get(0)));
    }

    @After
    public void tearDown() throws Exception {
        medicalProfessionRepository.deleteAll();
        medicalProfessionalRepository.deleteAll();

    }

    @Test
    public void testFindByFirstName() throws Exception {
        List<MedicalProfessional> medicalProfessionals = medicalProfessionalRepository.findByFirstName("Julie");
        assertEquals("professional list size", 1, medicalProfessionals.size());
        assertEquals("profession", "nurse", medicalProfessionals.get(0).getProfession().getName());
    }
}
