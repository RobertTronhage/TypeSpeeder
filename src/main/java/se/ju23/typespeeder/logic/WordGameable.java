package se.ju23.typespeeder.logic;

public interface WordGameable {
    void startGame();
    void endGame();
    void inputString(String word);
    void displayString(String words);
    double calculateCorrectWordsPercentage();
}
