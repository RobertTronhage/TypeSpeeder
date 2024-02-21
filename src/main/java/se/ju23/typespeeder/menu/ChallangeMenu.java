/** GameMenu.java
 * This class creates and displays menu options for game menu, implements MenuService interface
 * Author:Robert Tronhage, robert.tronhage@iths.se
 * Date 2024-02-12
 */

package se.ju23.typespeeder.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChallangeMenu implements MenuService{

    @Autowired
    Menu menu;

    public List getMenuOptionsInSwedish() {
        List<String>options = new ArrayList<>();

        options.add("0 - Åter till huvudmenyn");
        options.add("1 - Starta ett standardspel på svenska");
        options.add("2 - Starta ett standardspel på engelska");
        options.add("3 - Starta ett spel med specialtecken");
        options.add("4 - Starta ett spel med markerade tecken");

        return options;
    }

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
    public void displayMenu(Player foundPlayer) {
        IO io = new ConsoleIO();
        List<String> options;

        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")){
            options = getMenuOptionsInSwedish();
        }else {
            options = getMenuOptions();
        }

        for (String s : options){
            io.addString(s);
        }
    }
}
