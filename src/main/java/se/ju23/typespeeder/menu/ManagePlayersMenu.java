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
    public List getMenuOption() {
        List<String>options = new ArrayList<>();

        options.add("0 - Exit");
        options.add("1 - Play game");
        options.add("2 - View your stats");
        options.add("3 - View global leaderboard");
        options.add("4 - Manage Players (Admin)");

        return options;
    }

    @Override
    public void displayMenu() {
        List<String>options = getMenuOption();
        for (String s : options){
            io.addString(s);
        }
    }
}
