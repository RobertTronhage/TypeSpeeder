package se.ju23.typespeeder.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;

import java.util.ArrayList;
import java.util.List;



@Component
public class LeaderboardMenu implements MenuService{
    @Autowired
    Menu menu;

    public List getMenuOptionsInSwedish() {
        List<String>options = new ArrayList<>();

        options.add("0 - Till huvudmenyn");
        options.add("1 - Högst precision");
        options.add("2 - Snabbaste");
        options.add("3 - Längsta 'streak'");
        options.add("4 - Högst rank");

        return options;
    }
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
