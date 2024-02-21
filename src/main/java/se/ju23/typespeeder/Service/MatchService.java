package se.ju23.typespeeder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Match;
import se.ju23.typespeeder.entity.Player;
import se.ju23.typespeeder.repository.MatchRepo;

@Service
public class MatchService {

    @Autowired
    MatchRepo matchRepo;

    public void createNewMatch(Player foundPlayer){
        // Match match = new Match(foundPlayer,double correctAmountInPercent,double);

        // matchRepo.save(match);
    }

}
