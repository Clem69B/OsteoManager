package net.baz1.osteo.manager.service;

import net.baz1.osteo.manager.AppTest;
import net.baz1.osteo.manager.domain.model.Patient;
import net.baz1.osteo.manager.domain.repository.PatientRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Clément Bazin on 07/02/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppTest.class)
public class PatientServiceTest {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    @Before
    public void before() {
        this.patientRepository.deleteAll();

        /* Populate the patient database */
        this.patientRepository.save(new Patient("James", "Stevenson"));
        this.patientRepository.save(new Patient("Thomas", "Stevenson"));
        this.patientRepository.save(new Patient("James", "O'Malley"));
        this.patientRepository.save(new Patient("John", "Doe"));
    }

    @After
    public void after() {
        this.patientRepository.deleteAll();
    }

    @Test
    public void testAdd() throws Exception {
        Patient newPatient = new Patient("Bob", "Crane");
        Patient registeredPatient = this.patientService.add(newPatient);

        Assert.assertEquals("first name", "Bob", registeredPatient.getFirstName());
        Assert.assertEquals("last name", "Crane", registeredPatient.getLastName());
    }

    @Test
    public void testGet() throws Exception {
        List<Patient> patients = this.patientRepository.findByFirstNameOrderByLastNameAscAllIgnoreCase("James");
        String id = patients.get(0).getId();
        Patient getPatient = this.patientService.get(id);
        Assert.assertEquals("first name", "James", getPatient.getFirstName());
        Assert.assertEquals("last name", "O'Malley", getPatient.getLastName());
    }

    @Test
    public void testGetAll() throws Exception {
        Pageable pageable = new PageRequest(0, 2);

        Page<Patient> patients = this.patientService.getAll(pageable);
        Assert.assertEquals("total elements", 4, patients.getTotalElements());
        Assert.assertEquals("total pages", 2, patients.getTotalPages());
    }

    @Test
    public void testUpdate() throws Exception {

    }
}
