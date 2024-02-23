/**
 * Test class for performance of the Menu class methods.
 * @Author: Oskar Ray-Frayssinet
 */

package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.menu.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuPerformanceTest {

    private static final int MAX_EXECUTION_TIME_MENU = 1;
    private static final int MAX_EXECUTION_TIME_LANGUAGE_SELECTION = 100;
    private static final int MILLISECONDS_CONVERSION = 1_000_000;

    @Test
    public void testGetMenuOptionsExecutionTime() {

        long startTime = System.nanoTime();
        Menu menu = new Menu();
        menu.getMenuOptions();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        assertTrue(duration <= MAX_EXECUTION_TIME_MENU, "Menu display took too long. Execution time: " + duration + " ms.");
    }

    @Test
    public void testUserCanChooseSwedishLanguageAndPerformance() {
        String input = "svenska\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Player p = new Player();
        long startTime = System.nanoTime();

        Menu menu = new Menu();
        menu.displayMenu(p);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / MILLISECONDS_CONVERSION;

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("Välj språk (svenska/engelska):"), "Menu should prompt for language selection.");

        assertTrue(consoleOutput.contains("Svenska valt."), "Menu should confirm Swedish language selection.");

        assertTrue(duration <= MAX_EXECUTION_TIME_LANGUAGE_SELECTION, "Menu display and language selection took too long. Execution time: " + duration + " ms.");
    }

    @Test
    public void testGetChallangeLanguageChoiceExecutionTime() {
        double startTime = System.currentTimeMillis();

        ChallengeLanguageChoice menu = new ChallengeLanguageChoice();
        menu.getMenuOptions();

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);

        assertTrue(duration <= MAX_EXECUTION_TIME_MENU, "Menu display took too long. Execution time: " + duration + " ms.");
    }

    @Test
    public void testGetLeaderboardMenuOptionsExecutionTime() {
        double startTime = System.currentTimeMillis();

        LeaderboardMenu menu = new LeaderboardMenu();
        menu.getMenuOptions();

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);

        assertTrue(duration <= MAX_EXECUTION_TIME_MENU, "Menu display took too long. Execution time: " + duration + " ms.");
    }

    @Test
    public void testGetChallangeMenuOptionsExecutionTime() {

        double startTime = System.currentTimeMillis();

        ChallangeMenu menu = new ChallangeMenu();
        menu.getMenuOptions();

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);

        assertTrue(duration <= MAX_EXECUTION_TIME_MENU, "Menu display took too long. Execution time: " + duration + " ms.");
    }

    @Test
    public void testGetManagePlayersMenuExecutionTime() {

        double startTime = System.currentTimeMillis();

        ManagePlayersMenu menu = new ManagePlayersMenu();
        menu.getMenuOptions();

        double endTime = System.currentTimeMillis();
        double duration = (endTime - startTime);

        assertTrue(duration <= MAX_EXECUTION_TIME_MENU, "Menu display took too long. Execution time: " + duration + " ms.");
    }

}