package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.*;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.menu.*;
import se.ju23.typespeeder.repository.*;

import java.util.List;

@Component
public class Controller {

    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    MatchRepo matchRepo;
    @Autowired
    AccuracyLeaderboardRepo accuracyLeaderboardRepo;
    @Autowired
    TopRankLeaderboardRepo topRankLeaderboardRepo;
    @Autowired
    SpeedLeaderboardRepo speedLeaderboardRepo;
    @Autowired
    StreakLeaderboardRepo streakLeaderboardRepo;
    @Autowired
    Menu menu;
    @Autowired
    ChallangeMenu challangeMenu;
    @Autowired
    LeaderboardMenu leaderboardMenu;
    @Autowired
    ManagePlayersMenu managePlayersMenu;
    @Autowired
    Challenge challenge;
    @Autowired
    ChallengeLanguageChoice challengeLanguageChoice;


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
            menu.displayMenu(foundPlayer);

            menuOption = io.getValidIntegerInput(0,5);

            switch (menuOption){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    challenge.startChallenge(foundPlayer);
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

    public void challangeMenu(Player foundPlayer){
        int menuOption=0;

        do {
            challengeLanguageChoice.displayMenu(foundPlayer);

            int challengeLanguageChoice = io.getValidIntegerInput(0,3);

            if (challengeLanguageChoice == 0){
                break;
            }else if (challengeLanguageChoice==1){
                //setGameLanguage(swedish);
            }else {
                //setGameLanguage(english);
            }

            challangeMenu.displayMenu(foundPlayer);

            menuOption = io.getValidIntegerInput(0,4);

            switch (menuOption){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    //playgameeee
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

        }while(menuOption!=0);
    }

    public void leaderBoardMenu(Player foundPlayer){

        leaderboardMenu.displayMenu(foundPlayer);

        int userChoice = io.getValidIntegerInput(0,4);

        do {
            switch (userChoice){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    List<AccuracyLeaderBoard> mostAccuratePlayers = accuracyLeaderboardRepo.findAll();
                    if (!mostAccuratePlayers.isEmpty()) {
                        io.addString("Most Accurate Players:");
                        for (AccuracyLeaderBoard player : mostAccuratePlayers) {
                            io.addString(player.getUsername() + " - Accuracy: " + player.getAccuracy() + " %");
                        }
                    } else {
                        io.addString("No most accurate players found.");
                    }
                    return;
                }
                case 2 -> {
                    List<SpeedLeaderboard> fastestPlayers = speedLeaderboardRepo.findAll();
                    if (!fastestPlayers.isEmpty()) {
                        io.addString("Fastest Players:");
                        for (SpeedLeaderboard player : fastestPlayers) {
                            io.addString(player.getUsername() + " - Speed: " + player.getTimeToCompleteInSec());
                        }
                    } else {
                        io.addString("No fast players found.");
                    }
                    return;
                }
                case 3 -> {
                    List<StreakLeaderboard> streakLeaderboard = streakLeaderboardRepo.findAll();
                    if (!streakLeaderboard.isEmpty()) {
                        io.addString("Streak Leaderboard:");
                        for (StreakLeaderboard player : streakLeaderboard) {
                            io.addString(player.getUsername() + " - Streak: " + player.getAmountOfConsecutiveCorrectWords());
                        }
                    } else {
                        io.addString("No streak leaderboard found.");
                    }
                    return;
                }
                case 4 -> {
                    List<TopRankLeaderboard> topPlayers = topRankLeaderboardRepo.findAll();
                    if (!topPlayers.isEmpty()) {
                        io.addString("Top Players:");
                        for (TopRankLeaderboard player : topPlayers) {
                            io.addString(player.getUsername() + " - Level: " + player.getLevel());
                        }
                    } else {
                        io.addString("No top players found.");
                    }
                    return;
                }
            }
        }while (userChoice != 0);
    }

    public void editPlayerMenu(Player foundPlayer){
        managePlayersMenu.displayMenu(foundPlayer);

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
