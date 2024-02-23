/**
 * Menu.java
 * This class creates and displays main menu options for application, implements MenuService interface
 * Author:Robert Tronhage, robert.tronhage@iths.se
 * Date 2024-02-12
 */

package se.ju23.typespeeder.menu;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.RoleType;
import se.ju23.typespeeder.io.ColorHandler;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class Menu implements MenuService {

    private String languageChoice;

    public String getLanguageChoice() {
        return languageChoice;
    }

    @Override
    public List getMenuOptions() {
        return new ArrayList(Arrays.asList("0 - Exit", "1 - Play game", "2 - View your stats", "3 - View global leaderboard", "4 - Manage Players (Admin)","5 - Newsletter"));
    }

    public List getMenuOptionsInSwedish() {
        return new ArrayList(Arrays.asList("0 - Logga ut", "1 - Spela", "2 - Se din statistik", "3 - Visa global topplista", "4 - Hantera spelare (Admin)" , "5 - Nyhetsbrev"));
    }

    @Override
    public void displayMenu(Player foundPlayer) {
        IO io = new ConsoleIO();

        io.addString("Välj språk (svenska/engelska):");
        io.addString("Choose language (swedish/english):");
        languageChoice = io.getString().toLowerCase();

        List<String> options;

        if (languageChoice.equals("svenska") || languageChoice.equals("swedish")) {
            io.addString("Svenska valt...");
            options = getMenuOptionsInSwedish();
        } else {
            io.addString("English selected");
            options = getMenuOptions();
        }

        if (foundPlayer.getRole() == RoleType.player) {
            options.set(4, ColorHandler.gray(options.get(4)));
        }

        for (String s : options) {
            io.addString(s);
        }
    }
}