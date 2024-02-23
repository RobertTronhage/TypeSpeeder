/**
 * Repository interface for managing accuracy leaderboard data.
 *
 * @author Robert Tronhage
 */
package se.ju23.typespeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.entity.AccuracyLeaderBoard;

@Repository
public interface AccuracyLeaderboardRepo extends JpaRepository<AccuracyLeaderBoard,Long> {
}
