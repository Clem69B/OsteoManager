package net.baz1.osteo.manager;

import lombok.extern.java.Log;
import net.baz1.osteo.manager.model.MedicalProfession;
import net.baz1.osteo.manager.model.MedicalProfessional;
import net.baz1.osteo.manager.model.Patient;
import net.baz1.osteo.manager.repository.MedicalProfessionRepository;
import net.baz1.osteo.manager.repository.MedicalProfessionalRepository;
import net.baz1.osteo.manager.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */

@EnableAutoConfiguration
public class App implements CommandLineRunner {

    private static Logger LOGGER = Logger.getLogger(App.class.getName());

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicalProfessionRepository medicalProfessionRepository;
    @Autowired
    private MedicalProfessionalRepository medicalProfessionalRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Run the repository test");

        /* Start with solid bases */
        medicalProfessionRepository.deleteAll();
        medicalProfessionalRepository.deleteAll();
        patientRepository.deleteAll();

        /* Populate the medical profession database */
        medicalProfessionRepository.save(new MedicalProfession("doctor"));
        medicalProfessionRepository.save(new MedicalProfession("Nurse"));

        /* Populate the medical professional database */
        medicalProfessionalRepository.save(new MedicalProfessional("Julie",
                "Bernard", medicalProfessionRepository.findByName("nurse").get(0)));

        /* Populate the patient database */
        patientRepository.save(new Patient("James", "Stevenson"));
        patientRepository.save(new Patient("Thomas", "Stevenson"));
        patientRepository.save(new Patient("James", "O'Malley"));
        patientRepository.save(new Patient("John", "Doe"));

        /* Print the all database */
        printPatientList("Print all patient entries", patientRepository.findAll());
        printMedicalProfessionalList("Print all medical professional entries", medicalProfessionalRepository.findAll());

        /* Test first name */
        printPatientList("James research", patientRepository.findByFirstNameOrderByLastNameAscAllIgnoreCase("James"));

        /* Test last name with wrong case */
        printPatientList("Stevenson research", patientRepository.findByLastNameOrderByFirstNameAscAllIgnoreCase("StEveNson"));

        /* Test the profession field if the all profession collections is deleted */
        medicalProfessionRepository.deleteAll();
        printMedicalProfessionalList("Print all medical professional entries", medicalProfessionalRepository.findAll());
    }

    private void printPatientList(String title, List<Patient> patients) {

        System.out.println(title);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<title.length(); i++) {
            stringBuilder.append("-");
        }
        System.out.println(stringBuilder.toString());
        for (Patient patient : patients) {
            System.out.println(patient.toString());
        }
        System.out.println();
    }

    private void printMedicalProfessionalList(String title, List<MedicalProfessional> patients) {
        System.out.println(title);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<title.length(); i++) {
            stringBuilder.append("-");
        }
        System.out.println(stringBuilder.toString());
        for (MedicalProfessional medicalProfessional : patients) {
            System.out.println(medicalProfessional.toString());
        }
        System.out.println();
    }
}
