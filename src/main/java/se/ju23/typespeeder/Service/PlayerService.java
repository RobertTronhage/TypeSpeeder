package se.ju23.typespeeder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.RoleType;
import se.ju23.typespeeder.io.ConsoleIO;
import se.ju23.typespeeder.io.IO;
import se.ju23.typespeeder.menu.Menu;
import se.ju23.typespeeder.repository.PlayerRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    Menu menu;
    IO io = new ConsoleIO();

    public void addNewPlayer() {
        Player player = new Player();
        player.setLevel(1);
        player.setExperience(1);
        io.addString("set username");
        setUserName(player);
        io.addString("set password");
        setPassword(player);
        io.addString("set email");
        setEmail(player);
        io.addString("set Role");
        setPlayerRole(player);
        playerRepo.save(player);
    }

    public void editPlayer() {
        List<String> options;
        Player player = null;
        int choice = 0;

        do {
            io.addString(playerRepo.findAll().toString());

            if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
                io.addString("Ange id på den spelaren du vill ändra på: ");
                Long id = io.getValidLongInput(0, Long.MAX_VALUE);
                Optional<Player> playerOptional = playerRepo.findById(id);

                if (playerOptional.isEmpty()) {
                    io.addString("Kunde inte hitta en spelare med det ID.");
                } else {
                    player = playerOptional.get();
                }
                options = editPlayerOptionsInSwedish();

            } else {
                io.addString("Enter ID of the player you want to edit: ");
                Long id = io.getValidLongInput(0, Long.MAX_VALUE);
                Optional<Player> playerOptional = playerRepo.findById(id);

                if (playerOptional.isEmpty()) {
                    io.addString("No player with that ID.");
                } else {
                    player = playerOptional.get();
                }

                options = editPlayerOptions();
            }

            for (String s : options) {
                io.addString(s);
            }

            choice = io.getValidIntegerInput(0,4);

            switch (choice){
                case 0 -> {
                    return;
                }
                case 1 ->{
                    io.addString("edit username");
                    setUserName(player);
                }
                case 2 ->{
                    io.addString("edit email");
                    setEmail(player);
                }
                case 3 -> {
                    io.addString("edit password");
                    setPassword(player);

                }
                case 4 -> {
                    io.addString("edit userRole");
                    setPlayerRole(player);
                }
            }
            playerRepo.save(player);
        } while (choice != 0);

    }

    public void setUserName(Player player){
        io.addString(prefixForEditProperties());
        String value = io.getString();
        player.setUserName(value);
    }

    public void setEmail(Player player){
        io.addString(prefixForEditProperties());
        String value = io.getString();

        player.setEmail(value);
    }

    public void setPassword(Player player){
        io.addString(prefixForEditProperties());
        String value = io.getString();
        player.setPassword(value);
    }

    public void setPlayerRole(Player player){
        io.addString(prefixForEditProperties());
        io.addString("""
                0 - Exit
                1 - set player as Admin
                2 - set player as (normal) player
                """);
        int choice = io.getValidIntegerInput(0,2);

        if (choice == 0){
            //nothing updates
        } else if (choice == 1){
            player.setRole(RoleType.admin);
        } else {
            player.setRole(RoleType.player);
        }
        playerRepo.save(player);
    }

    public void updatePlayerExperienceAndLevel(Player foundPlayer, int correctWordCount, int mistakeCount) {
        int xpEarned = calculateExperienceEarned(correctWordCount, mistakeCount);

        int currentExperience = foundPlayer.getExperience();
        int updatedExperience = currentExperience + xpEarned;
        foundPlayer.setExperience(updatedExperience);

        int playerlevel = calculatePlayerLevel(updatedExperience);
        foundPlayer.setLevel(playerlevel);

        playerRepo.save(foundPlayer);
    }

    private int calculatePlayerLevel(int totalXP) {
        return totalXP / 100;
    }

    private int calculateExperienceEarned(int correctWordCount, int mistakeCount) {
        int baseXP = 40;

        int xpPenalty = 3 * mistakeCount;

        int totalXP = baseXP - xpPenalty;

        return Math.max(totalXP, 0);
    }

    private String prefixForEditProperties(){
        String s;

        if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
            s = "Ange nytt värde nedan:\n";
        } else {
            s = "State new value below:\n";
        }
        return s;
    }

    private List editPlayerOptions() {

        List<String> options = new ArrayList<>();

        options.add("0 - Exit");
        options.add("1 - Edit username");
        options.add("2 - Edit Email");
        options.add("3 - Edit password");
        options.add("4 - Edit privileges");

        return options;
    }

    private List editPlayerOptionsInSwedish() {

        List<String> options = new ArrayList<>();

        options.add("0 - Avsluta");
        options.add("1 - Ändra Användarnamn");
        options.add("2 - Ändra Epost");
        options.add("3 - Ändra lösenord");
        options.add("4 - Ändra behörighet");

        return options;
    }

    public void deletePlayer(){
        List<String> options;
        Player player = null;
        int choice = 0;
        Long id = 0L;
        boolean abort = false;

        do {
            io.addString(playerRepo.findAll().toString());

            if (menu.getLanguageChoice().equals("svenska") || menu.getLanguageChoice().equals("swedish")) {
                io.addString("Ange id på den spelaren du vill ta bort\n" +
                        "ange 0 för att avbryta: ");
                id = io.getValidLongInput(0, Long.MAX_VALUE);

                if (id == 0){
                    abort=true;
                }

                Optional<Player> playerOptional = playerRepo.findById(id);

                if (playerOptional.isEmpty()) {
                    io.addString("Kunde inte hitta en spelare med det ID.");
                } else {
                    player = playerOptional.get();
                }
                options = deletePlayerOptionsInSwedish();

            } else {
                io.addString("Enter ID of the player you want to delete\n" +
                        "Enter '0' to abort: ");
                id = io.getValidLongInput(0, Long.MAX_VALUE);

                if (id == 0){
                    abort = true;

                }

                Optional<Player> playerOptional = playerRepo.findById(id);

                if (playerOptional.isEmpty()) {
                    io.addString("No player with that ID.");
                } else {
                    player = playerOptional.get();
                }

                options = deletePlayerOptions();
            }

            for (String s : options) {
                io.addString(s);
            }

            choice = io.getValidIntegerInput(0,1);

            if (choice==0){
                break;
            }else{
                playerRepo.delete(player);
            }

            io.addString("Player was removed");

        } while (!abort);
    }

    private List deletePlayerOptions() {

        List<String> options = new ArrayList<>();

        options.add("0 - Abort and exit");
        options.add("1 - Confirm deletion");


        return options;
    }

    private List deletePlayerOptionsInSwedish() {

        List<String> options = new ArrayList<>();

        options.add("0 - Avbryt och avsluta");
        options.add("1 - Bekräfta radering av spelare från databasen");

        return options;
    }
}
