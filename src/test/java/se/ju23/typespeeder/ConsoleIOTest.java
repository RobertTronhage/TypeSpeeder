package se.ju23.typespeeder;

import org.junit.jupiter.api.Test;
import se.ju23.typespeeder.io.ConsoleIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleIOTest {
    @Test
    public void testIntroText() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ConsoleIO consoleIO = new ConsoleIO();
        consoleIO.introText();

        String expectedOutput = "Welcome to\n" +
                " ______              ____                __       \n" +
                "/_  __/_ _____  ___ / __/__  ___ ___ ___/ /__ ____\n" +
                " / / / // / _ \\/ -_)\\ \\/ _ \\/ -_) -_) _  / -_) __/\n" +
                "/_/  \\_, / .__/\\__/___/ .__/\\__/\\__/\\_,_/\\__/_/   \n" +
                "    /___/_/          /_/                          \n";
        assertEquals(expectedOutput, outContent.toString());
    }
    @Test
    public void testGetValidIntegerInputWithValidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("5\n".getBytes());
        System.setIn(in);

        ConsoleIO consoleIO = new ConsoleIO();
        int input = consoleIO.getValidIntegerInput(1, 10);

        assertEquals(5, input);
    }
    @Test
    public void testGetValidIntegerInputWithInvalidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("15\n1\n".getBytes());
        System.setIn(in);

        ConsoleIO consoleIO = new ConsoleIO();
        int input = consoleIO.getValidIntegerInput(1, 10);

        assertEquals(1, input);
    }
    @Test
    public void testYesNoWithValidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("yes\n".getBytes());
        System.setIn(in);

        ConsoleIO consoleIO = new ConsoleIO();
        boolean response = consoleIO.yesNo("Do you like programming?");

        assertTrue(response);
    }
    @Test
    public void testGetStringWithValidInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("validInput\n".getBytes());
        System.setIn(in);

        ConsoleIO consoleIO = new ConsoleIO();
        String input = consoleIO.getString();

        assertEquals("validInput", input);
    }
}
