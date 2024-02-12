/** GameMenu.java
 * This class creates and displays menu options for game menu, implements MenuService interface
 * Author:Robert Tronhage, robert.tronhage@iths.se
 * Date 2024-02-12
 */

package se.ju23.typespeeder.menu;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameMenu implements MenuService{
    IO io = new ConsoleIO();

    @Override
    public List getMenuOptions() {
        List<String>options = new ArrayList<>();

        options.add("0 - Exit");
        options.add("1 - Start 'Swedish' standard game");
        options.add("2 - Start 'English' standard game");
        options.add("3 - Start 'Special-character' game");
        options.add("4 - Start 'highlight' game");

        return options;
    }

    @Override
    public void displayMenu() {
        List<String>options = getMenuOptions();
        for (String s : options){
            io.addString(s);
        }
    }
}
