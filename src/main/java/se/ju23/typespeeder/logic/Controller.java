package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.RoleType;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.menu.GameMenu;
import se.ju23.typespeeder.menu.ManagePlayersMenu;
import se.ju23.typespeeder.menu.Menu;
import se.ju23.typespeeder.repository.MatchRepo;
import se.ju23.typespeeder.repository.PlayerRepo;

@Component
public class Controller {

    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    MatchRepo matchRepo;
    @Autowired
    Menu menu;
    @Autowired
    GameMenu gameMenu;
    @Autowired
    ManagePlayersMenu managePlayersMenu;
    @Autowired
    TypingGame typingGame;
    IO io = new ConsoleIO();

    public void login(){
        boolean runProgram = true;
        do {
            io.addString("Please enter username:");
            String username = io.getString();
            io.addString("Please enter password:");
            String password = io.getString();

            Player foundPlayer = playerRepo.getPlayerByUserNameAndPassword(username, password);

            if (foundPlayer == null) {
                io.addString("Incorrect username or password!");
                runProgram=true;
            }else {
                mainMenu(foundPlayer);
            }
        }while(runProgram);
    }

    public void mainMenu(Player foundPlayer){
        int menuOption=0;

        do {
            menu.displayMenu();

            menuOption = io.getValidIntegerInput(0,5);

            switch (menuOption){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    gameMenu(foundPlayer);
                }
                case 2 -> {
                    //playerService.displayFoundPlayersStats(foundPlayer);
                }
                case 3 -> {
                    leaderBoardMenu(foundPlayer);
                }
                case 4 ->{
                    editPlayerMenu(foundPlayer);
                }
            }
        }while(menuOption != 0);
    }

    public void gameMenu(Player foundPlayer){
        int menuOption=0;
        do {

            //selectLanguage-menu

            int languageChoice = io.getValidIntegerInput(0,3);

            if (languageChoice == 0){
                break;
            }else if (languageChoice==1){
                //setGameLanguage(swedish);
            }else {
                //setGameLanguage(english);
            }

            gameMenu.displayMenu();

            io.getValidIntegerInput(0,4);

            switch (menuOption){
                case 0 -> {
                    return;
                }
                case 1 -> {

                }
                case 2 -> {
                    //playerService.displayFoundPlayersStats();
                }
                case 3 -> {
                    leaderBoardMenu(foundPlayer);
                }
                case 4 ->{
                    editPlayerMenu(foundPlayer);
                }
            }

            io.addString("""
                    Choose game mode below:
                    0 - Exit to main menu
                    1 - Type whole sentences
                    2 - Type selected text
                    3 - Type special characters
                    """);

        }while(menuOption!=0);
    }

    public void leaderBoardMenu(Player foundPlayer){

        //leaderboardMenu.displayMenu();

        int userChoice = io.getValidIntegerInput(0,4);

        do {
            switch (userChoice){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    //most accurate
                }
                case 2 -> {
                    //fastest
                }
                case 3 -> {
                    //most words in a row
                }
                case 4 -> {
                    //highest rank
                }
            }
        }while (userChoice != 0);

    }

    public void editPlayerMenu(Player foundPlayer){
        managePlayersMenu.displayMenu();

        int userChoice = io.getValidIntegerInput(0,4);

        do {
            switch (userChoice){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    //create a new player
                }
                case 2 -> {
                    //display players - menu
                    //search for specific name/id & display all?
                }
                case 3 -> {
                    //edit player
                }
                case 4 -> {
                    //delete player
                }
            }
        }while (userChoice != 0);
    }
}
