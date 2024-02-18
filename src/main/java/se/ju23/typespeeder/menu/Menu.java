/** Menu.java
 * This class creates and displays main menu options for application, implements MenuService interface
 * Author:Robert Tronhage, robert.tronhage@iths.se
 * Date 2024-02-12
 */

package se.ju23.typespeeder.menu;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class Menu implements MenuService{

    IO io = new ConsoleIO();

    @Override
    public List getMenuOptions() {
        return new ArrayList(Arrays.asList("0- Exit", "1 - Play game", "2 - View your stats", "3 - View global leaderboard", "4 - Manage Players (Admin"));
    }
    public List getMenuOptionsInSwedish() {
        return new ArrayList(Arrays.asList("0- Logga ut", "1 - Spela", "2 - Se din statistik", "3 - Visa global topplista", "4 - Hantera spelare (Admin"));
    }

    @Override
    public void displayMenu() {
        io.addString("Välj språk (svenska/engelska):");
        io.addString("Choose language (swedish/english):");
        String languageChoice = io.getString().toLowerCase();

        if (languageChoice.equals("svenska")||languageChoice.equals("swedish")){
            io.addString("Svenska valt...");
            List<String>options = getMenuOptionsInSwedish();
            for (String s : options){
                io.addString(s);
            }
        }else {
            io.addString("English selected");
            List<String> options = getMenuOptions();
            for (String s : options) {
                io.addString(s);
            }
        }
    }
}