package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Match;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.GameMode;
import se.ju23.typespeeder.io.ColorHandler;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.menu.ChallangeMenu;
import se.ju23.typespeeder.menu.ManagePlayersMenu;
import se.ju23.typespeeder.menu.Menu;

import java.util.List;

@Component
public class Challenge implements Challengeable {

    @Autowired
    Menu menu;
    @Autowired
    ChallangeMenu challangeMenu;
    @Autowired
    IO io;
    ColorHandler c;

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
    public String lettersToType(List<String>randomWords) {
        StringBuilder generatedLetters = new StringBuilder();
        for (String word : randomWords){
            generatedLetters.append(word).append(" ");
        }
        return generatedLetters.toString().trim();
    }

    @Override
    public void checkAccuracy(String goalWords, String playerWords, GameMode gameMode) {

        int correctCount = 0;
        int minLength = Math.min(goalWords.length(), playerWords.length());
        double accuracy = 0;

        for (int i = 0; i < minLength; i++) {
            char goalChar = goalWords.charAt(i);
            char playerChar = playerWords.charAt(i);

            if (gameMode == GameMode.easy) {
                if (Character.toLowerCase(goalChar) == Character.toLowerCase(playerChar)) {
                    correctCount++;
                }
            } else if (gameMode == GameMode.hard) {
                // Om spelet är på svår nivå, jämför tecken inklusive skillnader i stor bokstav
                if (goalChar == playerChar) {
                    correctCount++;
                }
            }
            accuracy = (double) correctCount / Math.max(goalWords.length(), playerWords.length()) * 100;
        }
        io.addString(accuracy + "% was correct");
    }

    public void startStandardGameInSwedish(Player foundPlayer) {
        Match match = new Match();
        match.setGameMode(setDifficultyForStandardGame());
        getStandardGameInstructions();

        String wait = (io.getEmptyString());

        double startTime = System.currentTimeMillis();

        String goalWords = lettersToType(Data.getRandomWordsForStandardGame(Data.swedishWords));
        io.addString(goalWords);
        String playerWords = io.getString();

        double endTime = System.currentTimeMillis();
        double durationInSeconds = (endTime - startTime) / 1000 ;

        io.addString("it took " + durationInSeconds + "seconds to finish");
    }

    public void startStandardGameInEnglish(Player foundPlayer) {
        Match match = new Match();
        match.setGameMode(setDifficultyForStandardGame());

        getStandardGameInstructions();
        String wait = (io.getEmptyString());

        double startTime = System.currentTimeMillis();

        String goalWords = lettersToType(Data.getRandomWordsForStandardGame(Data.englishWords));
        io.addString(goalWords);

        String playerWords = io.getString();

        double endTime = System.currentTimeMillis();
        double durationInSeconds = (endTime - startTime) / 1000 ;

        checkAccuracy(goalWords,playerWords,match.getGameMode());

        io.addString("it took " + durationInSeconds);
    }

    public void startSpecialCharacterGame(Player foundPlayer) {
        //instruktioner för spel, därefter spellogik
    }

    public void startHighlightedCharacterGame(Player foundPlayer) {
        //instruktioner för spel, därefter spellogik
    }

    public void getStandardGameInstructions() {
        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            io.addString("""
                    Ett antal ord kommer visas på skärmen, skriv av orden, när du skrivit klart. Tryck Enter
                    Tryck på Enter när du är redo att starta!

                    """);
        } else {
            io.addString("""
                    A number of words will be displayed on the screen. Type the words, and when you're finished typing, press Enter.
                    Press Enter when ready to start!

                    """);
        }
    }

    public GameMode setDifficultyForStandardGame(){
        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            io.addString("""
                    Vänligen välj svårighet:
                    0 - Avsluta och åter till meny
                    1 - lätt
                    2 - Svår (Skiftlägeskänslig)
                    """);
        } else {
            io.addString("""
                    Please choose difficulty:
                    0 - Exit and return to menu
                    1 - easy
                    2 - Hard (Case sensitive)
                    """);
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
    public void getHighlightedGameInstructions() {
        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            io.addString("""
                    Ett antal ord kommer visas på skärmen, skriv av orden som är markerade med mellanslag mellan varje ord,
                    när du skrivit klart. Tryck Enter
                    Tryck på Enter när du är redo att starta!

                    """);
        } else {
            io.addString("""
                    A number of words will be displayed on the screen. Type the words with one space between each word
                    When you're finished typing, press Enter.
                    Press Enter when ready to start!

                    """);
        }
    }
    public void getSpecialCharGameInstructions() {
        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            io.addString("""
                    Ett antal ord kommer visas på skärmen, Det kommer även visas specialtecken (t ex: '@!"#¤')
                    Skriv enbart av Specialtecknen med mellanrum mellan varje tecken.
                    När du skrivit klart. Tryck Enter
                    
                    Tryck på Enter när du är redo att starta!

                    """);
        } else {
            io.addString("""
                    A number of words will be displayed on the screen aswell as special characters (eg. '@!"#¤')
                    Type only the Special characters
                    when you're finished typing, press Enter.
                    
                    Press Enter when ready to start!

                    """);
        }
    }
}
