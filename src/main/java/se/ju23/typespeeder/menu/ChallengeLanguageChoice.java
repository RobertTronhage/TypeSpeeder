package se.ju23.typespeeder.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChallengeLanguageChoice implements MenuService{

    @Autowired
    Menu menu;

    public List getMenuOptionsInSwedish() {
        List<String>options = new ArrayList<>();

        options.add("0 - Åter till huvudmenyn");
        options.add("1 - Spela på svenska");
        options.add("2 - Spela på engelska");

        return options;
    }

    @Override
    public List getMenuOptions() {
        List<String>options = new ArrayList<>();

        options.add("0 - Main menu");
        options.add("1 - Play in Swedish");
        options.add("2 - Play in English");

        return options;
    }

    @Override
    public void displayMenu(Player foundPlayer) {
        IO io = new ConsoleIO();
        List<String> options;

        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")){
            options = getMenuOptionsInSwedish();
        }else {
            options = getMenuOptions();
        }

        for (String s : options){
            io.addString(s);
        }
    }
}
