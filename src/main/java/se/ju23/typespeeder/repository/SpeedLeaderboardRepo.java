package se.ju23.typespeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.entity.SpeedLeaderboard;

@Repository
public interface SpeedLeaderboardRepo extends JpaRepository<SpeedLeaderboard,Long> {
}
