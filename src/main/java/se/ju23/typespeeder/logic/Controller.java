/**
 * The Controller class orchestrates the flow of the application, handling user input and directing the application's logic.
 * @Author: Robert Tronhage
 */
package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Service.MatchService;
import se.ju23.typespeeder.Service.PlayerService;
import se.ju23.typespeeder.entity.*;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.menu.*;
import se.ju23.typespeeder.repository.*;

import java.time.LocalDateTime;
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
    @Autowired
    PlayerService playerService;
    @Autowired
    MatchService matchService;

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
                    playerService.displayLoggedInPlayerStats(foundPlayer);
                }
                case 3 -> {
                    leaderBoardMenu(foundPlayer);
                }
                case 4 ->{
                    editPlayerMenu(foundPlayer);
                }
                case 5 -> {
                    NewsLetter newsLetter = new NewsLetter(LocalDateTime.now());
                    String content = newsLetter.getContent();
                    io.addString(content);
                }
            }
        }while(menuOption != 0);
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

        int userChoice=0;

        do {
            userChoice = io.getValidIntegerInput(0,4);
            switch (userChoice){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    playerService.addNewPlayer();
                    return;
                }
                case 2 -> {
                    //display players - menu
                    //search for specific name/id & display all?
                }
                case 3 -> {
                    playerService.editPlayer();
                    return;
                }
                case 4 -> {
                    playerService.deletePlayer();
                    return;
                }
            }
        }while (userChoice != 0);
    }
}
