/**
 * Test class for performance of the Challenge class methods.
 * @Author: Oskar Ray-Frayssinet
 */

package se.ju23.typespeeder;


import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.Service.MatchService;
import se.ju23.typespeeder.Service.PlayerService;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.io.ColorHandler;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.logic.Challenge;
import se.ju23.typespeeder.menu.ChallangeMenu;
import se.ju23.typespeeder.menu.Menu;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChallengePerformanceTest {
    private static final int MAX_EXECUTION_TIME = 200;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;
    @Test
    public void testStartChallengePerformance() {
        Challenge challenge = new Challenge();

        Player p = new Player();
        long startTime = System.nanoTime();

        challenge.startChallenge(p);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        assertTrue(duration <= MAX_EXECUTION_TIME, "Starting a challenge took too long. Execution time: " + duration + " ms.");
    }
    @Test
    public void testLettersToTypePerformance() {

        Challenge challenge = new Challenge();
        List<String>words = new ArrayList<>();
        long startTime = System.nanoTime();

        challenge.lettersToType(words);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        assertTrue(duration <= MAX_EXECUTION_TIME, "Selecting letters to type took too long. Execution time: " + duration + " ms.");
    }
}

