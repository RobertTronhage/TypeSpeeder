package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Match;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.GameMode;
import se.ju23.typespeeder.io.ColorHandler;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.menu.ChallangeMenu;
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
                    startSpecialCharacterGame(foundPlayer);
                }
                case 4 -> {
                    startHighlightedCharacterGame(foundPlayer);
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
    public double[] checkAccuracy(String goalWords, String playerWords, GameMode gameMode) {

        int correctCount = 0;
        int mistakeCount = 0;
        int maxLengthStreak = 0;
        int currentStreak = 0;
        int minLength = Math.min(goalWords.length(), playerWords.length());
        double accuracy = 0;

        for (int i = 0; i < minLength; i++) {
            char goalChar = goalWords.charAt(i);
            char playerChar = playerWords.charAt(i);

            if (gameMode == GameMode.easy) {
                if (Character.toLowerCase(goalChar) == Character.toLowerCase(playerChar)) {
                    correctCount++;
                    currentStreak++;
                } else {
                    mistakeCount++;
                    maxLengthStreak = Math.max(maxLengthStreak, currentStreak);
                    currentStreak = 0;
                }
            } else if (gameMode == GameMode.hard) {
                if (goalChar == playerChar) {
                    correctCount++;
                    currentStreak++;
                } else {
                    mistakeCount++;
                    maxLengthStreak = Math.max(maxLengthStreak, currentStreak);
                    currentStreak = 0;
                }
            }
            accuracy = (double) correctCount / Math.max(goalWords.length(), playerWords.length()) * 100;
        }

        maxLengthStreak = Math.max(maxLengthStreak, currentStreak);

        return new double[]{accuracy, correctCount, mistakeCount, maxLengthStreak};
    }


    public void startStandardGameInSwedish(Player foundPlayer) {
        Match match = new Match();
        match.setGameMode(setDifficultyForStandardGame());
        getStandardGameInstructions();

        String wait = io.getAnyString();

        double startTime = System.currentTimeMillis();

        String goalWords = lettersToType(Data.getRandomWordsForGame(Data.swedishWords));
        io.addString(goalWords);
        String playerWords = io.getString();

        double endTime = System.currentTimeMillis();
        double durationInSeconds = (endTime - startTime) / 1000 ;

        double [] accuracy = checkAccuracy(goalWords,playerWords,match.getGameMode());

        double accuracyPercentage = accuracy[0];
        String formattedAccuracy = String.format("%.2f", accuracyPercentage);

        io.addString("Det tog " + durationInSeconds + "Sekunder att skriva klart!\n" +
                "du hade en precision på " + formattedAccuracy + "%");
    }

    public void startStandardGameInEnglish(Player foundPlayer) {
        Match match = new Match();
        match.setGameMode(setDifficultyForStandardGame());

        getStandardGameInstructions();
        String wait = io.getAnyString();
        String goalWords = lettersToType(Data.getRandomWordsForGame(Data.englishWords));
        double startTime = System.currentTimeMillis();

        io.addString(goalWords);

        String playerWords = io.getString();

        double endTime = System.currentTimeMillis();
        double durationInSeconds = (endTime - startTime) / 1000 ;

        checkAccuracy(goalWords,playerWords,match.getGameMode());

        io.addString("it took " + durationInSeconds);
    }

    public void startSpecialCharacterGame(Player foundPlayer) {
        Match match = new Match();
        match.setGameMode(GameMode.special_characters);
        getSpecialCharGameInstructions();
        String goalWords = lettersToType(Data.getRandomWordsForGame(Data.specialCharacters));
        String wait = (io.getAnyString());
        double startTime = System.currentTimeMillis();

        io.addString(goalWords);
        String playerWords = io.getAnyString();

        double endTime = System.currentTimeMillis();
        double durationInSeconds = (endTime - startTime) / 1000 ;

        double [] accuracy = checkAccuracy(goalWords,playerWords,match.getGameMode());

        double accuracyPercentage = accuracy[0];
        String formattedAccuracy = String.format("%.2f", accuracyPercentage);
    }

    public void startHighlightedCharacterGame(Player foundPlayer) {
        Match match = new Match();
        getHighlightedGameInstructions();
        String goalWords = lettersToType(Data.getRandomWordsForGame(Data.englishWords));
        String highlightedGoalWords = Data.generateHighlightedWords(goalWords);
        String wait = (io.getAnyString());
        double startTime = System.currentTimeMillis();

        io.addString(highlightedGoalWords);
        String playerWords = io.getString();

        double endTime = System.currentTimeMillis();
        double durationInSeconds = (endTime - startTime) / 1000 ;

        double [] accuracy = checkAccuracy(goalWords,playerWords,match.getGameMode());

        double accuracyPercentage = accuracy[0];
        String formattedAccuracy = String.format("%.2f", accuracyPercentage);

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
                    A number of words will be displayed on the screen. Type the highlighted words with one space between each word
                    When you're finished typing, press Enter.
                    Press Enter when ready to start!

                    """);
        }
    }
    public void getSpecialCharGameInstructions() {
        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            io.addString("""
                    Ett antal specialtecken (t ex: '@!"#½') kommer visas på skärmen,
                    Skriv in de tecken som visas med mellanrum där det visas.
                    När du skrivit klart. Tryck Enter
                    
                    Tryck på Enter när du är redo att starta!

                    """);
        } else {
            io.addString("""
                    A number of special characters (eg. '@!"#¤') will be displayed.
                    Type the Special characters with space where you can see a space.
                    when you're finished typing, press Enter.
                    
                    Press Enter when ready to start!

                    """);
        }
    }
}
