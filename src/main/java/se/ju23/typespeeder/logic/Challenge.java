package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Match;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.GameMode;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.menu.ChallangeMenu;
import se.ju23.typespeeder.menu.Menu;

@Component
public class Challenge implements Challengeable {

    @Autowired
    Menu menu;
    @Autowired
    ChallangeMenu challangeMenu;
    @Autowired
    IO io;

    @Override
    public void startChallenge(Player foundPlayer) {
        int choice = 0;
        do {
            challangeMenu.displayMenu(foundPlayer);
            choice = io.getValidIntegerInput(0, 4);

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    startStandardGameInSwedish(foundPlayer);
                }
                case 2 -> {
                    startStandardGameInEnglish(foundPlayer);
                }
                case 3 -> {
                    startHighlightedCharacterGame(foundPlayer);
                }
                case 4 -> {
                    startSpecialCharacterGame(foundPlayer);
                }
            }
        } while (choice != 0);
    }

    @Override
    public void lettersToType() {

    }

    @Override
    public void checkAccuracy() {

    }

    @Override
    public void displayString(String words) {

    }

    public void startStandardGameInSwedish(Player foundPlayer) {
        Match match = new Match();
        match.setGameMode(setDifficultyForStandardGame());

        getStandardGameInstructions();
    }

    public void startStandardGameInEnglish(Player foundPlayer) {
        Match match = new Match();
        match.setGameMode(setDifficultyForStandardGame());

        getStandardGameInstructions();
    }

    public void startSpecialCharacterGame(Player foundPlayer) {
        //instruktioner för spel, därefter spellogik
    }

    public void startHighlightedCharacterGame(Player foundPlayer) {
        //instruktioner för spel, därefter spellogik
    }

    public void getStandardGameInstructions() {
        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            io.addString("Ett antal ord kommer visas på skärmen, "+
                    "skriv av orden, när du skrivit klart. Tryck Enter\n" +
                    "Tryck på Enter när du är redo att starta!\n\n");
        } else {
            io.addString("A number of words will be displayed on the screen. Type the words," +
                    " and when you're finished typing, press Enter.\n" +
                    "Press Enter when ready to start!\n\n");
        }
    }

    public GameMode setDifficultyForStandardGame(){
        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            io.addString("Vänligen välj svårighet:\n" +
                    "0 - Avsluta och åter till meny\n" +
                    "1 - lätt\n" +
                    "2 - Svår (Skiftlägeskänslig)\n");
        } else {
            io.addString("Please choose difficulty:\n" +
                    "0 - Exit and return to menu\n" +
                    "1 - easy\n" +
                    "2 - Hard (Case sensitive)\n");
        }
        int choice = io.getValidIntegerInput(0,2);

        if (choice == 0){
            return null;
        } else if (choice == 1) {
            return GameMode.easy;
        }else {
            return GameMode.hard;
        }
    }
}
