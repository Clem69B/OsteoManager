package net.baz1.osteo.manager.domain.repository;

import net.baz1.osteo.manager.domain.AppTest;
import net.baz1.osteo.manager.domain.model.Patient;
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
@SpringApplicationConfiguration(classes = AppTest.class)
public class PatientRepositoryTest {

    @Autowired
    PatientRepository patientRepository;

    @Before
    public void before() {
        patientRepository.deleteAll();

        /* Populate the patient database */
        patientRepository.save(new Patient("James", "Stevenson"));
        patientRepository.save(new Patient("Thomas", "Stevenson"));
        patientRepository.save(new Patient("James", "O'Malley"));
        patientRepository.save(new Patient("John", "Doe"));
    }

    @After
    public void after() {
        patientRepository.deleteAll();
    }

    @Test
    public void testFindAllPatient() {
        List<Patient> findPatients = patientRepository.findAll();

        assertEquals("patient list size", 4, findPatients.size());
    }

    @Test
    public void testFindByFirstNameOrderByLastNameAscAllIgnoreCase() throws Exception {
        /* Test first name */
        List<Patient> findPatients = patientRepository.findByFirstNameOrderByLastNameAscAllIgnoreCase("James");
        assertEquals("patient list size", 2, findPatients.size());
        assertEquals("1st patient last name", "O'Malley", findPatients.get(0).getLastName());
    }

    @Test
    public void testFindByLastNameOrderByFirstNameAscAllIgnoreCase() throws Exception {
        /* Test last name with wrong case */
        List<Patient> findPatients = patientRepository.findByLastNameOrderByFirstNameAscAllIgnoreCase("StEveNson");
        assertEquals("patient list size", 2, findPatients.size());
        assertEquals("1st patient first name", "James", findPatients.get(0).getFirstName());
    }
}
