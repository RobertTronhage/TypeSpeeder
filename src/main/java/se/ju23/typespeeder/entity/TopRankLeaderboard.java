package se.ju23.typespeeder.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "top_rank_view")
public class TopRankLeaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;
    private String username;
    private int level;

    public TopRankLeaderboard() {
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "TopRankLeaderboard{" +
                "playerId=" + playerId +
                ", username='" + username + '\'' +
                ", level=" + level +
                '}';
    }
}
