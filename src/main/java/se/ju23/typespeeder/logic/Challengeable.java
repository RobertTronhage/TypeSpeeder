/**
 * The Challengeable interface defines methods for starting a challenge, generating letters to type,
 * and checking accuracy.
 * @Author: Robert Tronhage
 */
package se.ju23.typespeeder.logic;

import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.GameMode;

import java.util.List;

public interface Challengeable {
    void startChallenge(Player foundPlayer);
    String lettersToType(List<String>list);
    double[] checkAccuracy(String goalWords, String playerWords, GameMode gameMode);
}
