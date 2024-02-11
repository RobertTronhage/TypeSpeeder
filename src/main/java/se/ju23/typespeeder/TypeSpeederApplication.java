package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.logic.Controller;

@SpringBootApplication
public class TypeSpeederApplication implements CommandLineRunner {
    @Autowired
    private Controller controller;
    @Autowired
    IO io;
    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        io.introText();
        controller.login();
    }

}
