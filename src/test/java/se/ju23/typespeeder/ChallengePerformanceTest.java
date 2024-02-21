package se.ju23.typespeeder;


import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.logic.Challenge;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

