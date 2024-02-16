package se.ju23.typespeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.entity.TopRankLeaderboard;

@Repository
public interface TopRankLeaderboardRepo extends JpaRepository<TopRankLeaderboard,Long> {
}
