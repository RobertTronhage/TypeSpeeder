package se.ju23.typespeeder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Match;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.enums.GameMode;
import se.ju23.typespeeder.repository.MatchRepo;

@Service
public class MatchService {

    @Autowired
    MatchRepo matchRepo;

    public void createNewMatch(Player foundPlayer,double correctAmountInPercent, double correctAmountInPcs, double correctAmountConsecutive, double elapsedTime, GameMode gameMode){

        Match match = new Match(foundPlayer, correctAmountInPercent, correctAmountInPcs, correctAmountConsecutive, elapsedTime, gameMode);

        matchRepo.save(match);
    }
}
