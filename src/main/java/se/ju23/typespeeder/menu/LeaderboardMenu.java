package se.ju23.typespeeder.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;

import java.util.ArrayList;
import java.util.List;



@Component
public class LeaderboardMenu implements MenuService{

    IO io = new ConsoleIO();

    @Override
    public List getMenuOptions() {
        List<String>options = new ArrayList<>();

        options.add("0 - Exit");
        options.add("1 - Most accurate");
        options.add("2 - Fastest");
        options.add("3 - Best streak");
        options.add("4 - Highest rank");

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
