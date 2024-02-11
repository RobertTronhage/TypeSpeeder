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
//                RoleType playerRole = foundPlayer.getRole(); //kontrollerar rolltyp på player, olika färger i meny beroende på vad för sort spelare.
                mainMenu(foundPlayer);
            }

        }while(runProgram);
    }

    public void mainMenu(Player foundPlayer){
        int menuOption=0;

        do {
            menu.displayMenu();

            menuOption = io.getValidIntegerInput(0,3);

            switch (menuOption){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    gameMenu(foundPlayer);
                }
                case 2 -> {
                    leaderBoardMenu(foundPlayer);
                }
                case 3 -> {
                    editPlayerMenu(foundPlayer);
                }
            }

        }while(menuOption != 0);
    }

    public void gameMenu(Player foundPlayer){
        int menuOption=0;
        do {
            io.addString("""
                    Choose option below:
                    0 - Exit to main menu
                    1 - Start game in English
                    2 - Start game in Swedish
                    """);

            io.getValidIntegerInput(0,3);

            switch (menuOption){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println("english");
                }
                case 2 -> {
                    System.out.println("swenska");
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

    }

    public void editPlayerMenu(Player foundPlayer){

    }



}
