/** ManagePlayersMenu.java
 * This class creates and displays menu options for CRUD operations on players, implements MenuService interface
 * Author:Robert Tronhage, robert.tronhage@iths.se
 * Date 2024-02-12
 */

package se.ju23.typespeeder.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.RoleType;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManagePlayersMenu implements MenuService{

    @Autowired
    Menu menu;

    public List getMenuOptionsInSwedish() {
        List<String>options = new ArrayList<>();

        options.add("0 - Till huvudmenyn");
        options.add("1 - Lägg till ny spelare");
        options.add("2 - Visa spelare");
        options.add("3 - Redigera spelare");
        options.add("4 - Ta bort spelare");

        return options;
    }
    @Override
    public List getMenuOptions() {
        List<String>options = new ArrayList<>();

        options.add("0 - Exit");
        options.add("1 - Add new player");
        options.add("2 - View players");
        options.add("3 - Edit Players");
        options.add("4 - Delete Player");

        return options;
    }

    @Override
    public void displayMenu(Player foundPlayer) {
        IO io = new ConsoleIO();

        if (foundPlayer.getRole().equals(RoleType.player)){
            io.addString("Only Admin can manage players\nEndast Admin kan redigare spelare");
            return;
        }
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
