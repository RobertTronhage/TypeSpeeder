package se.ju23.typespeeder.logic;

import se.ju23.typespeeder.entity.Player;

public interface Challengeable {
    void startChallenge(Player foundPlayer);
    void lettersToType();
    void displayString(String words);
    void checkAccuracy();
}
