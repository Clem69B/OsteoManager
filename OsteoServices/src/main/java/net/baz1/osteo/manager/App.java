package net.baz1.osteo.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Logger;

/**
 * Hello world!
 */

@EnableAutoConfiguration
@ComponentScan
public class App { //implements CommandLineRunner {

    private static Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
