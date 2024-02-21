package se.ju23.typespeeder.logic;

import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.GameMode;

import java.util.List;

public interface Challengeable {
    void startChallenge(Player foundPlayer);
    String lettersToType(List<String>list);
    void checkAccuracy(String goalWords, String playerWords, GameMode gameMode);
}
