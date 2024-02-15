package se.ju23.typespeeder.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LevelLeaderboard {

    @Id
    private Long id;
    private Long playerId;
    private String username;
    private int level;

    public LevelLeaderboard(Long playerId, String username, int level) {
        this.playerId = playerId;
        this.username = username;
        this.level = level;
    }

    public LevelLeaderboard() {
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "LevelLeaderboard{" +
                "playerId=" + playerId +
                ", username='" + username + '\'' +
                ", level=" + level +
                '}';
    }
}
