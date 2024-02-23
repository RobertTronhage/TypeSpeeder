/**
 * Repository interface for managing streak leaderboard data.
 *
 * @author Robert Tronhage
 */
package se.ju23.typespeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.entity.StreakLeaderboard;

@Repository
public interface StreakLeaderboardRepo extends JpaRepository<StreakLeaderboard,Long> {
}
