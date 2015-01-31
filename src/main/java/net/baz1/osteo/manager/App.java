package net.baz1.osteo.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.logging.Logger;

/**
 * Hello world!
 */

@EnableAutoConfiguration
public class App { //implements CommandLineRunner {

    private static Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
