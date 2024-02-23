package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.ju23.typespeeder.entity.Patch;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.logic.Controller;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class TypeSpeederApplication implements CommandLineRunner {
    @Autowired
    private Controller controller;
    @Autowired
    IO io;
    @Autowired
    Patch patch;
    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String patchVersion = patch.getPatchVersionFromGitTag();
        String releaseDate = patch.getReleaseDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-DD-hh:mm:ss"));

        io.introText();
        io.addString("\npatchVersion: " + patchVersion + "\nreleasedate: " + releaseDate + "\n");
        controller.login();
    }

}
