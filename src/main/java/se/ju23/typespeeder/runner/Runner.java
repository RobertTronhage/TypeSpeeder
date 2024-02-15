package se.ju23.typespeeder.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.logic.Controller;
import se.ju23.typespeeder.repository.MatchRepo;
import se.ju23.typespeeder.repository.PlayerRepo;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    MatchRepo matchRepo;
    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    Controller controller;
    IO console = new ConsoleIO();
    @Override
    public void run(String... args) throws Exception {
        console.introText();
        controller.login();
    }

}
