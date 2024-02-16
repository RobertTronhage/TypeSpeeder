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

    /*IO io = new ConsoleIO();

    @Override
    public List getMenuOptions() {
        return new ArrayList(Arrays.asList("0- Exit", "1 - Play game", "2 - View your stats", "3 - View global leaderboard", "4 - Manage Players (Admin"));
    }
    public List getMenuOptions() {
        List<String>options = new LinkedList<>();

        options.add("0 - Exit");
        options.add("1 - Play game");
        options.add("2 - View your stats");
        options.add("3 - View global leaderboard");
        options.add("4 - Manage Players (Admin)");

        return options;
    }

    @Override
    public void displayMenu() {
        List<String>options = getMenuOptions();
        for (String s : options){
            io.addString(s);
        }
    }*/

//    @Component
//    public class Menu implements MenuService {

        private static final List<String> MENU_OPTIONS = Arrays.asList(
                "0 - Exit",
                "1 - Play game",
                "2 - View your stats",
                "3 - View global leaderboard",
                "4 - Manage Players (Admin)"
        );

        private final IO io = new ConsoleIO();

        @Override
        public List<String> getMenuOptions() {
            return MENU_OPTIONS;
        }

        @Override
        public void displayMenu() {
            StringBuilder menuBuilder = new StringBuilder();
            for (String option : getMenuOptions()) {
                menuBuilder.append(option).append("\n");
            }
            io.addString(menuBuilder.toString());
        }

}