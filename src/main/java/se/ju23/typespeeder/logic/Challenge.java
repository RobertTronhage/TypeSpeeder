/**
 * Challenge.java represents a challenge session in the TypeSpeeder game.
 * It provides methods to start different types of challenges and handle game logic.
 * This class is responsible for managing challenge sessions, generating random words,
 * calculating accuracy, and updating player statistics.
 * Author: Robert Tronhage, robert.tronhage@iths.se
 * Date: 2024-02-22
 */

package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Service.MatchService;
import se.ju23.typespeeder.Service.PlayerService;
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
    @Autowired
    MatchService matchService;
    @Autowired
    PlayerService playerService;
    ColorHandler c;

    public Challenge() {
    }

    /**
     * Starts a challenge session for the given player.
     * Displays the challenge menu and handles user input to start different types of challenges.
     *
     * @param foundPlayer The player participating in the challenge.
     */
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

    /**
     * Generates a string of letters to be typed based on a list of random words.
     *
     * @param randomWords A list of random words.
     * @return A string containing the letters to be typed.
     */
    @Override
    public String lettersToType(List<String> randomWords) {
        StringBuilder generatedLetters = new StringBuilder();
        for (String word : randomWords) {
            generatedLetters.append(word).append(" ");
        }
        return generatedLetters.toString().trim();
    }

    /**
     * Calculates the accuracy, correct count, mistake count, and streak of a player's typing.
     *
     * @param goalWords   The string of letters to be typed.
     * @param playerWords The string of letters typed by the player.
     * @param gameMode    The game mode determining the accuracy calculation method.
     * @return An array of doubles containing accuracy, correct count, mistake count, and streak.
     */
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

            if (gameMode != GameMode.hard) {
                if (Character.toLowerCase(goalChar) == Character.toLowerCase(playerChar)) {
                    correctCount++;
                    currentStreak++;
                } else {
                    mistakeCount++;
                    maxLengthStreak = Math.max(maxLengthStreak, currentStreak);
                    currentStreak = 0;
                }
            } else {
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

    /**
     * Starts a standard game challenge in Swedish for the given player. This method generates random words for the player to type,
     * calculates the accuracy and time taken by the player to complete the challenge, updates the player's statistics, and creates a new match.
     *
     * @param foundPlayer The player who starts the challenge.
     */
    public void startStandardGameInSwedish(Player foundPlayer) {
        String wait;

        do {
            Match match = new Match();
            match.setGameMode(setDifficultyForStandardGame(foundPlayer));
            getStandardGameInstructions();

            wait = io.getAnyString();
            if (wait.equals("0")) {
                break;
            }

            double startTime = System.currentTimeMillis();

            String goalWords = lettersToType(Data.getRandomWordsForGame(Data.swedishWords));
            io.addString(goalWords);
            String playerWords = io.getString();

            double endTime = System.currentTimeMillis();
            double elapsedTimeInSeconds = (endTime - startTime) / 1000;

            double[] accuracy = checkAccuracy(goalWords, playerWords, match.getGameMode());

            double accuracyPercentage = accuracy[0];
            double accuracyPsc = accuracy[1];
            double misstakes = accuracy[2];
            double streak = accuracy[3];

            String formattedAccuracy = String.format("%.2f", accuracyPercentage);

            io.addString("Det tog " + elapsedTimeInSeconds + "Sekunder att skriva klart!\n" +
                    "du hade en precision på " + formattedAccuracy + "%");

            match.setAmountOfCorrectWordsInPercent(accuracyPercentage);
            match.setAmountOfCorrectWordsInPcs(accuracyPsc);
            match.setTimeToCompleteInSec(elapsedTimeInSeconds);
            match.setAmountOfConsecutiveCorrectWords(streak);

            matchService.createNewMatch(foundPlayer, accuracyPercentage, accuracyPsc, streak, elapsedTimeInSeconds, match.getGameMode());
            playerService.updatePlayerExperienceAndLevel(foundPlayer, accuracyPsc, misstakes, elapsedTimeInSeconds);
        } while (!wait.equals("0"));
    }

    /**
     * Starts a standard game challenge in English for the given player. This method functions similarly to startStandardGameInSwedish, but with English words.
     *
     * @param foundPlayer The player who starts the challenge.
     */
    public void startStandardGameInEnglish(Player foundPlayer) {
        String wait;

        do {
            Match match = new Match();
            match.setGameMode(setDifficultyForStandardGame(foundPlayer));

            if (match.getGameMode().equals(null)) {
                break;
            }

            getStandardGameInstructions();
            wait = io.getAnyString();
            if (wait.equals("0")) {
                break;
            }
            String goalWords = lettersToType(Data.getRandomWordsForGame(Data.englishWords));
            double startTime = System.currentTimeMillis();

            io.addString(goalWords);

            String playerWords = io.getString();

            double endTime = System.currentTimeMillis();
            double elapsedTimeInSeconds = (endTime - startTime) / 1000;

            double[] accuracy = checkAccuracy(goalWords, playerWords, match.getGameMode());

            double accuracyPercentage = accuracy[0];
            double accuracyPsc = accuracy[1];
            double misstakes = accuracy[2];
            double streak = accuracy[3];

            String formattedAccuracy = String.format("%.2f", accuracyPercentage);

            io.addString("It took " + elapsedTimeInSeconds + " seconds to finish!\n" +
                    "you had a precision " + formattedAccuracy + "%");

            match.setAmountOfCorrectWordsInPercent(accuracyPercentage);
            match.setAmountOfCorrectWordsInPcs(accuracyPsc);
            match.setTimeToCompleteInSec(elapsedTimeInSeconds);
            match.setAmountOfConsecutiveCorrectWords(streak);

            matchService.createNewMatch(foundPlayer, accuracyPercentage, accuracyPsc, streak, elapsedTimeInSeconds, match.getGameMode());
            playerService.updatePlayerExperienceAndLevel(foundPlayer, accuracyPsc, misstakes, elapsedTimeInSeconds);
        } while (!wait.equals("0"));
    }

    /**
     * Starts a special character game challenge for the given player. This method generates random special characters for the player to type,
     * processes the challenge similarly to the standard game challenges, and updates the player's statistics.
     *
     * @param foundPlayer The player who starts the challenge.
     */
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
        double elapsedTimeInSeconds = (endTime - startTime) / 1000;

        double[] accuracy = checkAccuracy(goalWords, playerWords, match.getGameMode());

        double accuracyPercentage = accuracy[0];
        double accuracyPsc = accuracy[1];
        double misstakes = accuracy[2];
        double streak = accuracy[3];

        String formattedAccuracy = String.format("%.2f", accuracyPercentage);

        io.addString("It took " + elapsedTimeInSeconds + " seconds to finish!\n" +
                "you had a precision " + formattedAccuracy + "%");

        match.setAmountOfCorrectWordsInPercent(accuracyPercentage);
        match.setAmountOfCorrectWordsInPcs(accuracyPsc);
        match.setTimeToCompleteInSec(elapsedTimeInSeconds);
        match.setAmountOfConsecutiveCorrectWords(streak);

        matchService.createNewMatch(foundPlayer, accuracyPercentage, accuracyPsc, streak, elapsedTimeInSeconds, match.getGameMode());
        playerService.updatePlayerExperienceAndLevel(foundPlayer, accuracyPsc, misstakes, elapsedTimeInSeconds);
    }

    /**
     * Starts a highlighted character game challenge for the given player. This method generates random words with highlighted characters for the player to type,
     * processes the challenge similarly to the standard game challenges, and updates the player's statistics.
     *
     * @param foundPlayer The player who starts the challenge.
     */
    public void startHighlightedCharacterGame(Player foundPlayer) {
        Match match = new Match();
        match.setGameMode(GameMode.highlighted);
        getHighlightedGameInstructions();

        String goalWords = lettersToType(Data.getRandomWordsForGame(Data.englishWords));
        String highlightedGoalWords = Data.generateHighlightedWords(goalWords);
        String wait = (io.getAnyString());
        double startTime = System.currentTimeMillis();

        io.addString(highlightedGoalWords);
        String playerWords = io.getString();

        double endTime = System.currentTimeMillis();
        double elapsedTimeInSeconds = (endTime - startTime) / 1000;

        String onlyHighlightedWords = extractBlueText(highlightedGoalWords);
        double[] accuracy = checkAccuracy(onlyHighlightedWords, playerWords, match.getGameMode());

        double accuracyPercentage = accuracy[0];
        double accuracyPsc = accuracy[1];
        double misstakes = accuracy[2];
        double streak = accuracy[3];

        String formattedAccuracy = String.format("%.2f", accuracyPercentage);
        io.addString(onlyHighlightedWords);
        io.addString("It took " + elapsedTimeInSeconds + " seconds to finish!\n" +
                "you had a precision " + formattedAccuracy + "%");

        match.setAmountOfCorrectWordsInPercent(accuracyPercentage);
        match.setAmountOfCorrectWordsInPcs(accuracyPsc);
        match.setTimeToCompleteInSec(elapsedTimeInSeconds);
        match.setAmountOfConsecutiveCorrectWords(streak);

        matchService.createNewMatch(foundPlayer, accuracyPercentage, accuracyPsc, streak, elapsedTimeInSeconds, match.getGameMode());

        playerService.updatePlayerExperienceAndLevel(foundPlayer, accuracyPsc, misstakes, elapsedTimeInSeconds);
    }

    /**
     * Prepares String for check of accuracy
     *
     * @param highlightedGoalWords the generated String with highlighted words.
     */
    private String extractBlueText(String highlightedGoalWords) {
        String[] parts = highlightedGoalWords.split("\u001B\\[34m"); // Dela upp vid blå färgkod

        StringBuilder blueText = new StringBuilder();

        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            int endIndex = part.indexOf("\u001B[0m"); // Hitta slutet på blå färgkod
            if (endIndex != -1) {
                String bluePart = part.substring(0, endIndex); // Delen efter blå färgkod
                // Lägg till mellanrum efter varje ord (förutom det sista)
                String[] words = bluePart.split("\\s+");
                for (int j = 0; j < words.length; j++) {
                    blueText.append(words[j]);
                    blueText.append(" ");
                }
            } else {
                blueText.append(part); // Om slutet av blå färgkod inte finns, lägg till hela delen
            }
        }

        return blueText.toString();
    }

    /**
     * Provides instructions for the standard game in either Swedish or English, depending on the language choice.
     * Displays a set of words on the screen for the player to type, with prompts to start the game.
     */
    public void getStandardGameInstructions() {
        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            io.addString("""
                    Ett antal ord kommer visas på skärmen, skriv av orden, när du skrivit klart. Tryck Enter
                    Tryck på Enter när du är redo att starta Eller tryck '0' och Enter för att avbryta!

                    """);
        } else {
            io.addString("""
                    A number of words will be displayed on the screen. Type the words, and when you're finished typing, press Enter.
                    Press Enter when ready to start or press '0' and Enter to exit!

                    """);
        }
    }

    /**
     * Sets the difficulty level for the standard game based on player input.
     * Displays instructions for difficulty selection and returns the chosen GameMode.
     *
     * @return The selected difficulty level as a GameMode enum value.
     */
    public GameMode setDifficultyForStandardGame(Player foundPlayer) {
        int choice = 0;

        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            io.addString("""
                    Vänligen välj svårighet:
                    1 - lätt
                    2 - Svår (Skiftlägeskänslig)
                    """);
        } else {
            io.addString("""
                    Please choose difficulty:
                    1 - easy
                    2 - Hard (Case sensitive)
                    """);
        }
        choice = io.getValidIntegerInput(1, 2);


        if (choice == 1) {
            return GameMode.easy;
        } else {
            return GameMode.hard;
        }

    }

    /**
     * Provides instructions for the highlighted character game in either Swedish or English, depending on the language choice.
     * Displays a set of words with highlighted characters on the screen for the player to type, with prompts to start the game.
     */
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

    /**
     * Provides instructions for the special character game in either Swedish or English, depending on the language choice.
     * Displays a set of special characters on the screen for the player to type, with prompts to start the game.
     */
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
