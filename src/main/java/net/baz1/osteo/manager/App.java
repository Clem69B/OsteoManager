package net.baz1.osteo.manager;

import net.baz1.osteo.manager.model.Patient;
import net.baz1.osteo.manager.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.List;

/**
 * Hello world!
 *
 */

@EnableAutoConfiguration
public class App implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /* Start with solid bases */
        patientRepository.deleteAll();

        /* Populate the database */
        patientRepository.save(new Patient("James", "Stevenson"));
        patientRepository.save(new Patient("Thomas", "Stevenson"));
        patientRepository.save(new Patient("James", "O'Malley"));
        patientRepository.save(new Patient("John", "Doe"));

        /* Print the all database */
        printPatientList("Print all patient entries", patientRepository.findAll());

        /* Test first name */
        printPatientList("James research", patientRepository.findByFirstName("James"));

        /* Test last name */
        printPatientList("Stevenson research", patientRepository.findByLastName("Stevenson"));

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
}
