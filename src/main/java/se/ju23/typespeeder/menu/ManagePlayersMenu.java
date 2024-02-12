package se.ju23.typespeeder.menu;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManagePlayersMenu implements MenuService{
    IO io = new ConsoleIO();

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
    public void displayMenu() {
        List<String>options = getMenuOptions();
        for (String s : options){
            io.addString(s);
        }
    }
}
