package se.ju23.typespeeder.logic;

public interface Challengeable {
    void startChallenge();
    void lettersToType();
    void endGame();
    void inputString(String word);
    void displayString(String words);
    double calculateCorrectWordsPercentage();
}
